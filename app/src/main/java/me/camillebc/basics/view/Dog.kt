package me.camillebc.basics.view

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Dog(
    val name: String,
    val breed: String,
    val subBreed: String = ""
): Parcelable

/**
 * Extension functions for data validation
 */
fun Dog.isValid(): Boolean = validation()
fun Dog.isInvalid(): Boolean = ! validation()

private fun Dog.validation(): Boolean {
    if (name.isBlank() || breed.isBlank()) return false
    return true
}
