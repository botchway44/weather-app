/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.R

val initialWeather = listOf(
    WeatherInfo(temperature = 32, state = "Accra", country = "GHANA", drops = 17, windy = 9, resource = R.drawable.ic_rainy),
    WeatherInfo(temperature = 12, state = "Kumasi", country = "GHANA", drops = 57, windy = 31, resource = R.drawable.ic_cloudy),
    WeatherInfo(temperature = 30, state = "Bangkok", country = "THA", drops = 42, windy = 7, resource = R.drawable.ic_snowy),
    WeatherInfo(temperature = 24, state = "Austin", country = "USA", drops = 34, windy = 16, resource = R.drawable.ic_snowflake),
    WeatherInfo(temperature = 31, state = "Austin", country = "USA", drops = 25, windy = 9, resource = R.drawable.ic_sun),
    WeatherInfo(temperature = 30, state = "Bangkok", country = "THA", drops = 42, windy = 7, resource = R.drawable.ic_snowy)
)
