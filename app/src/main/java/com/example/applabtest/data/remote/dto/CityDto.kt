package com.example.applabtest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CitiesResponseDto(
    @SerializedName("Response")
    val response: ResponseWrapperDto
)

data class ResponseWrapperDto(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("result")
    val result: ResultDto
)

data class ResultDto(
    @SerializedName("cities")
    val cities: CitiesDataDto
)

data class CitiesDataDto(
    @SerializedName("qatar")
    val qatar: List<CityDto>?,
    @SerializedName("world")
    val world: List<CityDto>?
)

data class CityDto(
    @SerializedName("city_id")
    val cityId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("country_name")
    val countryName: String,
    @SerializedName("country_name_ar")
    val countryNameAr: String?,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("utc_offset")
    val utcOffset: String?,
    @SerializedName("name_ar")
    val nameAr: String?
)