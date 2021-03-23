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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.androiddevchallenge.components.BottomNavigationScreens
import com.example.androiddevchallenge.components.WeatherAppBottomNavigation
import com.example.androiddevchallenge.components.SearchBar
import com.example.androiddevchallenge.components.VerticalGrid
import com.example.androiddevchallenge.components.WeatherCard
import com.example.androiddevchallenge.data.WeatherInfo
import com.example.androiddevchallenge.data.initalWeather
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
            CurrentWeatherStatus()
        }

        composable(BottomNavigationScreens.DetailedWeatherList.route) {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp, )
            ){
                Spacer(modifier = Modifier.height(30.dp))

                SearchBar(modifier  = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp))
                    .padding(horizontal = 2.dp,)
                    .height(50.dp))

                Spacer(modifier = Modifier.height(30.dp))

                LocationWeatherList(initalWeather)
            }
        }

    }
}

@Preview
@Composable
fun CurrentWeatherStatus(){
   Column(

       modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()) ,
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally

   ) {

       Spacer(modifier = Modifier.height(30.dp))


       Row(
           modifier = Modifier.fillMaxWidth() ,
           horizontalArrangement = Arrangement.Center,
           verticalAlignment = Alignment.CenterVertically
       ){

           Image(
               painter = painterResource(id = R.drawable.ic_navigation),
               contentDescription = "description of the image",
               modifier = Modifier
                   .padding(horizontal = 5.dp)
                   .height(15.dp)
           )

           Text(text = "Your Location Now", fontSize = 3.1.em, color = MaterialTheme.colors.onPrimary)

       }
       Spacer(modifier = Modifier.height(10.dp))

       Row(
           modifier = Modifier.fillMaxWidth() ,
           horizontalArrangement = Arrangement.Center,
           verticalAlignment = Alignment.CenterVertically
       ){

           Text(text = "Kumasi, Kentinkrono, Ghana", fontSize = 3.6.em, color = MaterialTheme.colors.onPrimary)

       }

       Spacer(modifier = Modifier.height(30.dp))

       Canvas(modifier = Modifier
           .fillMaxWidth()
           .height(200.dp),

           onDraw = {
               val canvasWidth = size.width
               val canvasHeight = size.height



//               val gradient = LinearGradient(
//                colors =   listOf(Color.Blue, Color.Black),
//                   start = Offset(size.width / 2f - 64,  size.height / 2 - 64,),
//                   end = Offset(size.width / 2 + 64,  size.height / 2 + 64),
//                   tileMode = TileMode.Clamp
//               )
//               drawCircle(
//                   gradient, 64f,
//               )


           drawCircle(
               color = Color.Blue,
               center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
               radius = size.minDimension / 3.4f
           )

       })

       Spacer(modifier = Modifier.height(10.dp))

       Text(text = "Moonlight", fontSize = 3.2.em, color = MaterialTheme.colors.onPrimary , modifier = Modifier.padding(5.dp).clip(
           RoundedCornerShape(20.dp)).background( Color(0xFF353361)).padding(10.dp) )

       Spacer(modifier = Modifier.height(20.dp))

       Text(text = "20°C", fontSize = 10.1.em, color = MaterialTheme.colors.onPrimary)

       Spacer(modifier = Modifier.height(10.dp))

       Row(modifier = Modifier.fillMaxWidth(0.6f), horizontalArrangement = Arrangement.SpaceBetween){
           Row(
               verticalAlignment = Alignment.CenterVertically
           ){
               Image(
                   painter = painterResource(id = R.drawable.ic_wind),
                   contentDescription = "description of the image",
                   modifier = Modifier
                       .padding(horizontal = 5.dp)
                       .height(17.dp)
               )

               Text(text = "20km/h", fontSize = 3.0.em, modifier = Modifier.padding(horizontal = 1.dp))
           }

           Row(
               verticalAlignment = Alignment.CenterVertically
           ){
               Image(
                   painter = painterResource(id = R.drawable.ic_drop),
                   contentDescription = "description of the image",
                   modifier = Modifier
                       .padding(horizontal = 5.dp)
                       .height(15.dp)
               )

               Text(text = "7%", fontSize = 3.0.em,color = MaterialTheme.colors.onPrimary, modifier = Modifier.padding(horizontal = 1.dp))
           }

           Row(
               verticalAlignment = Alignment.CenterVertically
           ){
               Image(
                   painter = painterResource(id = R.drawable.ic_exclamation),
                   contentDescription = "description of the image",
                   modifier = Modifier
                       .padding(horizontal = 5.dp)
                       .height(15.dp)
               )

               Text(text = "0.533mBar", fontSize = 3.0.em,color = MaterialTheme.colors.onPrimary, modifier = Modifier.padding(horizontal = 1.dp))
           }
       }

       Spacer(modifier = Modifier.height(40.dp))

       //List Measurement Items
       Column(
           modifier = Modifier.fillMaxWidth(0.90f),
       ) {
           WeatherMeasurementListItem("Temperature", "Celcius", R.drawable.ic_right_chevron)
           Spacer(modifier = Modifier.height(30.dp))
           WeatherMeasurementListItem("Wind Speed", "m/s", R.drawable.ic_right_chevron)
           Spacer(modifier = Modifier.height(30.dp))
           WeatherMeasurementListItem("Source", "weather.gov", R.drawable.ic_right_chevron)

       }
   }

}



@Composable
fun WeatherMeasurementListItem(
    measurement : String,
    value : String,
    icon : Int
){

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = "$measurement",fontSize = 3.5.em)

        Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Text(text = "$value", fontSize = 3.0.em)

            Image(
                painter = painterResource(id = icon),
                contentDescription = "description of the image",
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .height(15.dp)
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



