package com.example.testing

import kotlin.random.Random

data class Cat(
    var id: Int = getAutoId(),
    var title: String = "",
    var animalNum: String = ""
) {
    companion object {
        fun getAutoId(): Int {
            val random = Random
            return random.nextInt(100) // Replace with your desired range or logic
        }
    }
}