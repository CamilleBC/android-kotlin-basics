package me.camillebc.basics

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.camillebc.basics.fragment.DogEditorFragment
import me.camillebc.basics.fragment.DogListFragment

/**
 * Step 4
 *
 * 1- Add a ListView in the fragment_dog_list.xml layout.
 * 2- Create a data class for the dogs
 * 3- Create a mutable list of Dog items in the main activity
 * 4- Use the user input to create a Dog item
 * 5- Add the Dog item to the list when clicking the Add button in DogEditorFragment
 * 6- Send the data from the [MainActivity] to the [DogListFragment]:
 *  a- Set the Dog class to be parcelable. This will allow the serialization between activities/fragments
 *  b- Create a companion object to the DogListFragment which will instantiate a DogListFragment with the parcelable
 *     data sent as a [Bundle]
 *  c- Send the data to the [DogListFragment] as an argument
 *  d- Get the data from the [Bundle] in [DogListFragment.onCreate]
 *  e- Set the data in the ListView adapter to be displayed
 *
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
    // The parent activity must extend the fragment's listeners
    DogEditorFragment.OnAddClickListener,
    DogListFragment.OnAddClickListener {

    // 3- Mutable list of Dog items
    // _dogList is the private mutable list, used to add and edit the data
    private val _dogList = mutableListOf<Dog>(
    )
    // dogList is the public interface, used to display the data
    private val dogList
        get() = _dogList as ArrayList<Dog>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // inflate the DogListFragment on application start
        val dogListFragment = DogListFragment.newInstance(dogList)
        supportFragmentManager.beginTransaction()
            .add(R.id.constraintLayout_main_fragmentContainer, dogListFragment)
            .commit()
    }

    /**
     * 5- Add the Dog item to the list, using the values sent from the [DogEditorFragment]
     * 6.d- We use the [DogListFragment.newInstance] function to send the data
     *
     * This is the implementation of the [DogEditorFragment.OnAddClickListener].
     */
    override fun onDogEditorAddClick(name: String, breed: String, subBreed: String) {
        // create a Dog item from the user input
        val dog = Dog(name, breed, subBreed)
        // if the data is valid, add the item to the list
        if ( dog.isValid() ) {
            _dogList.add(dog)
            // create a new instance of the DogListFragment with the new data
            val dogListFragment = DogListFragment.newInstance(dogList)
            // replace the DogEditorFragment with DogListFragment ith the new data
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
