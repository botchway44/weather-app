package com.example.androiddevchallenge.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.WeatherInfo


@Composable
fun WeatherCard(
    weatherInfo :WeatherInfo
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .padding(all = 6.dp)
            .background(MaterialTheme.colors.background)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {  }
                .padding(all = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(all = 5.dp) ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = "${weatherInfo.temperature}°", fontSize = 8.em, color = MaterialTheme.colors.onPrimary)

                Image(
                    painter = painterResource(id = weatherInfo.resource),
                    contentDescription = "description of the image",
                    modifier = Modifier
                        .padding(horizontal = 2.dp).height(50.dp)
                ) }

            Text(text = weatherInfo.state, fontSize = 2.5.em,color = MaterialTheme.colors.onPrimary, modifier = Modifier.padding(vertical = 1.dp))

            Text(text = weatherInfo.country, fontSize = 2.em,color = MaterialTheme.colors.onPrimary, modifier = Modifier.padding(vertical = 1.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(id = R.drawable.ic_drop),
                        contentDescription = "description of the image",
                        modifier = Modifier
                            .padding(horizontal = 2.dp).height(10.dp)
                    )
                    Text(text = "${weatherInfo.drops}%" , fontSize = 2.5.em,color = MaterialTheme.colors.onPrimary,modifier = Modifier.padding(horizontal = 1.dp))
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(id = R.drawable.ic_wind),
                        contentDescription = "description of the image",
                        modifier = Modifier
                            .padding(horizontal = 2.dp).height(15.dp)
                    )

                    Text(text = " ${weatherInfo.windy}km/h", fontSize = 2.5.em,color = MaterialTheme.colors.onPrimary, modifier = Modifier.padding(horizontal = 1.dp))
                }

            }
        }
    }

}

