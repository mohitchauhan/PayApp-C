
package com.payapp.interactors

import com.android.pay.core.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import java.util.concurrent.TimeUnit

abstract class Interactor<in P> {
    protected abstract val scope: CoroutineScope

    operator fun invoke(params: P, timeoutMs: Long = defaultTimeoutMs): Flow<InvokeStatus> {
        val channel = ConflatedBroadcastChannel<InvokeStatus>(InvokeIdle)
        scope.launch {
            try {
                withTimeout(timeoutMs) {
                    channel.send(InvokeStarted)
                    try {
                        doWork(params)
                        channel.send(InvokeSuccess)
                    } catch (t: Throwable) {
                        channel.send(InvokeError(t))
                    }
                }
            } catch (t: TimeoutCancellationException) {
                channel.send(InvokeTimeout)
            }
        }
        return channel.asFlow()
    }

    suspend fun executeSync(params: P) = withContext(scope.coroutineContext) { doWork(params) }

    protected abstract suspend fun doWork(params: P)

    companion object {
        private val defaultTimeoutMs = TimeUnit.MINUTES.toMillis(5)
    }
}

interface ObservableInteractor<T> {
    val dispatcher: CoroutineDispatcher
    fun observe(): Flow<T>
}


abstract class SuspendingWorkInteractor<P : Any, T : Any> : ObservableInteractor<T> {
    private val channel = ConflatedBroadcastChannel<T>()

    suspend operator fun invoke(params: P) = channel.send(doWork(params))

    abstract suspend fun doWork(params: P): T

    override fun observe(): Flow<T> = channel.asFlow()
}


abstract class ResultInteractor<in P, R> {
    abstract val dispatcher: CoroutineDispatcher

    suspend operator fun invoke(params: P): R {
        return withContext(dispatcher) { doWork(params) }
    }

    protected abstract suspend fun doWork(params: P): R
}