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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.androiddevchallenge.components.SearchBar
import com.example.androiddevchallenge.components.VerticalGrid
import com.example.androiddevchallenge.components.WeatherCard
import com.example.androiddevchallenge.data.WeatherInfo
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme(darkTheme = true) {
                MainScreen()
            }
        }
    }
}


// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        Text(text = "Ready... Set... GO!")
    }
}


private val initalWeather = listOf(
    WeatherInfo(temperature = 32, state = "Accra", country = "GHANA", drops = 17, windy = 9, resource = R.drawable.ic_rainy ),
    WeatherInfo(temperature = 12, state = "Kumasi", country = "GHANA", drops = 57, windy = 31, resource = R.drawable.ic_cloudy ),
    WeatherInfo(temperature = 30, state = "Bangkok", country = "THA", drops = 42, windy = 7, resource = R.drawable.ic_snowy ),
    WeatherInfo(temperature = 24, state = "Austin", country = "USA", drops = 34, windy = 16, resource = R.drawable.ic_snowflake ),
    WeatherInfo(temperature = 31, state = "Austin", country = "USA", drops = 25, windy = 9, resource = R.drawable.ic_sun ),
    WeatherInfo(temperature = 15, state = "Austin", country = "USA", drops = 60, windy = 11, resource = R.drawable.ic_rainy ),
    WeatherInfo(temperature = 19, state = "Austin", country = "USA", drops = 35, windy = 7, resource = R.drawable.ic_snowy ),
    WeatherInfo(temperature = 17, state = "Austin", country = "USA", drops = 19, windy = 14, resource = R.drawable.ic_cloudy ),
)


sealed class BottomNavigationScreens(val route: String, @StringRes val resourceId: Int, val icon: Int) {
    object CurrentWeatherPage : BottomNavigationScreens("CurrentWeatherPage", R.string.currentWeather, R.drawable.ic_rainy)
    object DetailedWeatherList : BottomNavigationScreens("DetailedWeatherList", R.string.temperature, R.drawable.ic_thermometer)
   }

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val bottomNavigationItems = listOf(
        BottomNavigationScreens.CurrentWeatherPage,
        BottomNavigationScreens.DetailedWeatherList,
    )
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            backgroundColor = MaterialTheme.colors.surface,
            bottomBar = {
                WeatherAppBottomNavigation(navController, bottomNavigationItems)
            },
        ) {
            MainScreenNavigationConfigurations(navController)
        }
    }
}

@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController
) {
    NavHost(navController, startDestination = BottomNavigationScreens.CurrentWeatherPage.route) {

        composable(BottomNavigationScreens.CurrentWeatherPage.route) {
            Text("Hello Details Route")
        }

        composable(BottomNavigationScreens.DetailedWeatherList.route) {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp, )
            ){

                SearchBar(modifier  = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp))
                    .padding(horizontal = 2.dp, vertical = 20.dp)
                    .height(50.dp))

                LocationWeatherList(initalWeather)
            }
        }

    }
}




@Composable
private fun WeatherAppBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavigationScreens>
) {
    BottomNavigation(
        modifier = Modifier.background(MaterialTheme.colors.surface) ,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Image(
                        painter = painterResource(id = screen.icon),
                        contentDescription = "description of the image",
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .height(20.dp)
                    )
                },
                label = { Text(stringResource(id = screen.resourceId)) },
                selected = currentRoute == screen.route,
                alwaysShowLabel = false, // This hides the title for the unselected items
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}

@Composable
fun LocationWeatherList(
    weatherInfoList : List<WeatherInfo>
){
    VerticalGrid(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        content = {
            weatherInfoList.forEach { screen -> WeatherCard(weatherInfo =  screen) }
        }
    )
}




@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
}