package me.camillebc.basics.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.camillebc.basics.R

/**
 * A simple [Fragment] subclass.
 */
class DogListFragment : Fragment() {

    /**
     * Inflate the fragment_dog_list.xml layout in the container, which id is given when setting the transaction in
     * the activity's FragmentManager.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dog_list, container, false)
    }

}
