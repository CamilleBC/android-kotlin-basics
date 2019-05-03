package me.camillebc.basics

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 2- Dog data class. A data class implements a series of basic methods, such as get/set, without the need to type them
 *
 * 6.a- The @Parcelize annotation implements standard parcel methods, without the need to type them. It is available in the
 * experimental Android/Kotlin extensions.
 */
@Parcelize
data class Dog(
    val name: String,
    val breed: String,
    val subBreed: String = ""
): Parcelable

/**
 * Extension functions for data validation
 */
fun Dog.isValid(): Boolean {
    if (name.isBlank() || breed.isBlank()) return false
    return true
}
