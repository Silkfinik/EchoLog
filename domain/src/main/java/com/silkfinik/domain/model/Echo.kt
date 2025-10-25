package com.silkfinik.domain.model

data class Echo(
    val id: String,
    val title: String,
    val text: String,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Long,
    val userId: String? = null
)