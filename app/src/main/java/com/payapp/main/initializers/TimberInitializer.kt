/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.payapp.main.initializers

import android.app.Application
import app.tivi.BuildConfig
import app.tivi.util.TiviLogger
import com.payapp.main.BuildConfig
import javax.inject.Inject

class TimberInitializer @Inject constructor(
    private val tiviLogger: TiviLogger
) : AppInitializer {
    override fun init(application: Application) = tiviLogger.setup(BuildConfig.DEBUG)
}
