package me.camillebc.basics

import androidx.lifecycle.ViewModel

/**
 * 1- The DogViewModel class extends the [ViewModel]. It acts as data container tied to a lifecycle owner
 */
class DogViewModel: ViewModel() {
    // _dogList is the private mutable list, used to add and edit the data, inaccessible from the outside
    private val _dogList = mutableListOf<Dog>()
    // dogList is the public list, used to display the data
    val dogList = _dogList as List<Dog>

    fun addDog(dog: Dog) {
        _dogList.add(dog)
    }
}