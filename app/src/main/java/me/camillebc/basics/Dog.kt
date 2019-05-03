package me.camillebc.basics

/**
 * We changed the way to do data validation.
 * The standard constructor is made private, and a companion object is used to create a new instance.
 * In this function, we throw an illegal exception if the argument is invalid, which is then caught in a try/catch block.
 */
data class Dog private constructor(
    val name: String,
    val breed: String,
    val subBreed: String = ""
) {
    companion object {
        fun newInstance(name: String, breed: String, subBreed: String = ""): Dog {
            if (name.isBlank())
                throw IllegalArgumentException("Dog's name is required.")
            else if (breed.isBlank())
                throw IllegalArgumentException("Dog's breed is required.")
            return Dog(name, breed, subBreed)
        }
    }
}

