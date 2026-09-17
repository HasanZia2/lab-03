package com.example.listycity3

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.listycity3.ui.theme.ListyCity3Theme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.FloatingActionButton
import androidx.compose.foundation.layout.fillMaxSize

@Composable
fun CityListScreen(
    cities: List<City>,
    onAddCity: (City) -> Unit,
    onEditCity: (City, City) -> Unit,
    modifier: Modifier = Modifier
)  {
    var newCityName by remember { mutableStateOf("") }
    var newProvinceName by remember { mutableStateOf("") }
    var originalCityName by remember { mutableStateOf("") }
    var originalProvinceName by remember { mutableStateOf("") }
    var editedCityName by remember { mutableStateOf("") }
    var editedProvinceName by remember { mutableStateOf("") }
    var showAddCityFields by remember { mutableStateOf(false) }
    var showEditCityFields by remember { mutableStateOf(false) }
    Column(modifier = modifier.fillMaxSize()) {
        if (!showAddCityFields && !showEditCityFields) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                FloatingActionButton(
                    modifier = Modifier.padding(16.dp),
                    onClick = {
                        if (!showAddCityFields && !showEditCityFields) {
                            showAddCityFields = true
                        }
                    }
                ) {
                    Text("Add")
                }

                FloatingActionButton(
                    modifier = Modifier.padding(16.dp),
                    onClick = {
                        if (!showEditCityFields && !showAddCityFields) {
                            showEditCityFields = true
                        }
                    }
                ) {
                    Text("Edit")
                }
            }
        }

        if (showAddCityFields) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = newCityName,
                    onValueChange = { newCityName = it },
                    label = { Text("City") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    value = newProvinceName,
                    onValueChange = { newProvinceName = it },
                    label = { Text("Province") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    modifier = Modifier.padding(vertical = 12.dp),
                    onClick = {
                        if (newCityName.isBlank() || newProvinceName.isBlank()) {
                            showAddCityFields = false
                        }
                        else {
                            onAddCity(
                                City(
                                    name = newCityName,
                                    province = newProvinceName
                                )
                            )
                            newCityName = ""
                            newProvinceName = ""
                            showAddCityFields = false
                        }
                    }
                ) {
                    Text("Add City")
                }
            }
        }

        if (showEditCityFields) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = originalCityName,
                    onValueChange = { originalCityName = it },
                    label = { Text("City") },
                    supportingText = { Text("City to edit") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    value = originalProvinceName,
                    onValueChange = { originalProvinceName = it },
                    label = { Text("Province") },
                    supportingText = { Text("Province to edit") },
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = editedCityName,
                    onValueChange = { editedCityName = it },
                    label = { Text("City") },
                    supportingText = { Text("New city") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    value = editedProvinceName,
                    onValueChange = { editedProvinceName = it },
                    label = { Text("Province") },
                    supportingText = { Text("New province") },
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Button(
                    modifier = Modifier.padding(vertical = 12.dp),
                    onClick = {
                        if (originalCityName.isBlank() && originalProvinceName.isBlank() && editedCityName.isBlank() && editedProvinceName.isBlank()) {
                            showEditCityFields = false
                        }
                        if (editedCityName.isBlank()) {
                            editedCityName = originalCityName
                        }
                        if (editedProvinceName.isBlank()) {
                            editedProvinceName = originalProvinceName
                        }
                        if (originalCityName.isNotBlank() && originalProvinceName.isNotBlank()) {
                            onEditCity(
                                City(
                                    name = originalCityName,
                                    province = originalProvinceName
                                ),
                                City(
                                    name = editedCityName,
                                    province = editedProvinceName
                                )
                            )
                            originalCityName = ""
                            originalProvinceName = ""
                            editedCityName = ""
                            editedProvinceName = ""
                            showEditCityFields = false
                        }
                    }
                ) {
                    Text("Edit City")
                }
            }
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(cities) { index, city ->
                CityRow(city = city)
                if (index < cities.lastIndex) {
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
fun CityRow(city: City) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = city.name,
            fontSize = 30.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = city.province,
            fontSize = 30.sp,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CityListScreenPreview() {
    ListyCity3Theme {
        CityListScreen(
            cities = listOf(
                City("Edmonton", "AB"),
                City("Vancouver", "BC"),
                City("Calgary", "AB")
            ),
                    onAddCity = {},
                    onEditCity = { city1: City, city2: City -> Unit }
        )
    }
}