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
import com.example.androiddevchallenge.components.VerticalGrid
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



//@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

//@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}


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
    Scaffold(
      backgroundColor = MaterialTheme.colors.surface,
        bottomBar = {
            WeatherAppBottomNavigation(navController, bottomNavigationItems)
        },
    ) {
        MainScreenNavigationConfigurations(navController)
    }
}

@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController
) {
    NavHost(navController, startDestination = BottomNavigationScreens.CurrentWeatherPage.route) {
        composable(BottomNavigationScreens.CurrentWeatherPage.route) {
           Column(){
               LocationWeatherList()
           }
        }
        composable(BottomNavigationScreens.DetailedWeatherList.route) {
            Text("Hello Details Route")
        }

    }
}




@Composable
private fun WeatherAppBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavigationScreens>
) {
    BottomNavigation {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Image(
                        painter = painterResource(id = screen.icon),
                        contentDescription = "description of the image",
                        modifier = Modifier
                            .padding(horizontal = 2.dp).height(20.dp)
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

class LocationWeather{

}

//@Preview("Weather List Items")
@Composable
fun LocationWeatherList(){
    VerticalGrid(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        content = {
            WeatherCard()
            WeatherCard()
            WeatherCard()
            WeatherCard()
            WeatherCard()
            WeatherCard()
            WeatherCard()
            WeatherCard()
            WeatherCard()
            WeatherCard()
            WeatherCard()
            WeatherCard()
            WeatherCard()
            WeatherCard()
            WeatherCard()
        }
    )
}

@Preview("Weather List Card", )
@Composable
fun WeatherCard(){

          Row(
              modifier = Modifier
                  .fillMaxWidth().wrapContentHeight()
                  .clip(RoundedCornerShape(30.dp))
                  .padding(all = 6.dp)
                  .background(MaterialTheme.colors.background)

          ) {
              Column(
                  modifier = Modifier
                      .fillMaxWidth().wrapContentHeight()
                      .clickable {  }
                      .padding(all = 10.dp)
              ) {
                  Row(
                      modifier = Modifier.fillMaxWidth().padding(all = 5.dp) ,
                      horizontalArrangement = Arrangement.SpaceBetween,
                      verticalAlignment = Alignment.CenterVertically
                  ) {

                      Text(text = "22 °", fontSize = 8.em, color = MaterialTheme.colors.onPrimary)

                      Image(
                          painter = painterResource(id = R.drawable.ic_rainy),
                          contentDescription = "description of the image",
                          modifier = Modifier
                              .padding(horizontal = 2.dp).height(50.dp)
                      ) }

                  Text(text = "Austin", fontSize = 2.em,color = MaterialTheme.colors.onPrimary)

                  Text(text = "USA", fontSize = 2.5.em,color = MaterialTheme.colors.onPrimary)

                  Row(
                      modifier = Modifier.fillMaxWidth() ,
                      horizontalArrangement = Arrangement.SpaceBetween,
                      verticalAlignment = Alignment.CenterVertically
                  ) {
                    Row(){

                        Image(
                            painter = painterResource(id = R.drawable.ic_drop),
                            contentDescription = "description of the image",
                            modifier = Modifier
                                .padding(horizontal = 2.dp).height(10.dp)
                        )
                        Text(text = "27", fontSize = 3.em,color = MaterialTheme.colors.onPrimary)

                    }
                      Text(text = "7km/h", fontSize = 3.em,color = MaterialTheme.colors.onPrimary)
                  }
              }
          }

}


@Composable
fun DegreesText(){
    Row (
        modifier = Modifier.fillMaxWidth()
        ){
        Text(text = "20C", fontSize = 9.em)
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
}