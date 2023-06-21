package hr.algebra.fakestoreapp.model

data class Item (
    var _id: Long?,
    val title: String,
    val price: Double,
    val picturePath: String,
    val description: String,
    var rating:Double,
    var bought:Boolean
        )