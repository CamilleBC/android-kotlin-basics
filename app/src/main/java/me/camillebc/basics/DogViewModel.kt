package me.camillebc.basics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 1- We change the type of our data to [LiveData]
 */
class DogViewModel: ViewModel() {
    // _dogList is the private mutable list, used to add and edit the data, inaccessible from the outside
    private val _dogList = MutableLiveData<MutableList<Dog>>(mutableListOf())
    // dogList is the public list, used to display the data
    val dogList = _dogList as LiveData<List<Dog>>

    /**
     * This function gives a public way to update the data.
     * We also need to set the value to itself because of quirk of LiveData.
     * The LiveData subscribers only get notified on data change. Because our type is a [MutableList], when we add an
     * item, the value of the [MutableList] itself does not change (the reference is the same).
     * By setting the value to itself, we force the [LiveData] to trigger an update to the registered [Observer].
     */
    fun addDog(dog: Dog) {
        _dogList.value?.add(dog)
        _dogList.postValue(_dogList.value)
    }
}