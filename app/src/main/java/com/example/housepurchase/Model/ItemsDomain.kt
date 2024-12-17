package com.example.housepurchase.Model

import java.io.Serializable

data class ItemsDomain(
    var type: String,
    var title: String,
    var address: String,
    var pickPath: String,
    var price: Int,
    var bed: Int,
    var bath: Int,
    var size: Int,
    var isGarage: Boolean,
    var score: Double,
    var description: String,
    var isFavourite: Boolean = false                    // Thêm trạng thái yêu thích
) : Serializable
