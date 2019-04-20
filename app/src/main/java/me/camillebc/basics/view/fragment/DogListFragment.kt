package me.camillebc.basics.view.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_dog_list.*
import me.camillebc.basics.R
import me.camillebc.basics.view.Dog

private const val ARG_DOG_LIST = "dog_list"

/**
 * A simple [Fragment] subclass.
 *
 */
class DogListFragment : Fragment() {
    private var onAddClickListener: OnAddClickListener? = null
    private var dogList = listOf<Dog>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.apply {
           dogList = getParcelableArrayList(ARG_DOG_LIST) ?: listOf()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dog_list, container, false)
    }

    /**
     * [onActivityCreated] is called after [onCreateView]. The button's view is already inflated.
     * [onActivityCreated] is called after the parent's activity is fully functional.
     *
     * We can attach the callback safely here, as the activity is fully functional.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            button_dogList_add.setOnClickListener{ onAddClickListener?.onDogListAddClick() }
        }
        context?.let {
            listView_dogList.adapter = ArrayAdapter<Dog>(it, android.R.layout.simple_list_item_1, dogList)
        }
    }

    /**
     * [onAttach] is called when the activity is created.
     *
     * WARNING: [onAttach] holds only a *reference* to the parent's activity. It does *not* mean that the parent's
     * activity is fully functional.
     *
     * We can attach the listener safely, as we only need the reference to check that the parent's activity implements
     * the listener.
     * We CANNOT attach the listener's callback itself, as it the implementation uses [Toast] which relies on the
     * activity to be fully functional.
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Attach the parent's activity listener to the fragment
        if (context is OnAddClickListener)
            onAddClickListener = context
        else
            throw RuntimeException("$context must implement OnAddClickListener")
    }

    /**
     * We detach the listener when the fragment is detached from the parent's activity
     */
    override fun onDetach() {
        super.onDetach()
        onAddClickListener = null
    }
    /**
     * interface for the button click listener. The callback needs to be implemented in the parent's activity
     */
    interface OnAddClickListener {
        fun onDogListAddClick()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param dogList List of [Dog] to pass as arguments
         * @return A new instance of fragment BlankFragment.
         */
        @JvmStatic
        fun newInstance(dogList: ArrayList<Dog>) =
            DogListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_DOG_LIST, dogList)
                }
            }
    }
}
