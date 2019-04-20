package me.camillebc.basics.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.camillebc.basics.R
import me.camillebc.basics.view.fragment.DogEditorFragment
import me.camillebc.basics.view.fragment.DogListFragment

/**
 * The [MainActivity] implements the necessary callbacks from [DogEditorFragment] and [DogListFragment]
 *
 * It adds the [DogListFragment] on [MainActivity.onCreate] and then just waits until there is a callback from one of
 * the fragments.
 *
 * See the official android documentation for more information:
 * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
 *
 * @property _dogList private mutable list of [Dog]. This property is never exposed to the outside.
 * @property dogList public immutable list of [Dog]. This property is used when exposing [_dogList] to the outside.
 */
class MainActivity :
    AppCompatActivity(),
    DogEditorFragment.OnAddClickListener,
    DogListFragment.OnAddClickListener {

    // dogList is the public interface
    private val _dogList = mutableListOf<Dog>(
        Dog("Pakkun", "Ninken"),
        Dog("Bull", "Ninken"),
        Dog("Urushi", "Ninken")
    )
    private val dogList
        get() = _dogList as ArrayList<Dog>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dogListFragment = DogListFragment.newInstance(dogList)
        supportFragmentManager.beginTransaction()
            .add(R.id.constraintLayout_main_fragmentContainer, dogListFragment)
            .commit()
    }

    /**
     * This is the implementation of the [DogEditorFragment.OnAddClickListener].
     * [FragmentManager.popBackstack] pops the last entry in the backstack and get us back to the last added fragment.
     */
    override fun onDogEditorAddClick(name: String, breed: String, subBreed: String) {
        toast("Not implemented")
        val dog = Dog(name, breed, subBreed)
        if ( dog.isValid() ) {
            _dogList.add(dog)
            toast("$dogList", true)
            val dogListFragment = DogListFragment.newInstance(dogList)
            supportFragmentManager.beginTransaction()
                .replace(R.id.constraintLayout_main_fragmentContainer, dogListFragment)
                .addToBackStack(null)
                .commit()
        } else {
            toast("Invalid dog.\nName and breed cannot be empty.")
        }
    }

    /**
     * This is the implementation of the [DogListFragment.OnAddClickListener].
     */
    override fun onDogListAddClick() {
        val dogEditorFragment = DogEditorFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.constraintLayout_main_fragmentContainer, dogEditorFragment)
            .addToBackStack(null)
            .commit()
    }

}

/**
 * Small extension function for activities. It simply Toast a message
 */
fun AppCompatActivity.toast(message: String, long: Boolean = false) {
    val length = if (long) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    Toast.makeText(this, message, length).show()
}
