---
title: Walkthrough
date: 2019-04-20
sections:
 - "0.0 Walkthrough"
 - "0.1.1 Walkthrough"
 - "0.1.2 Walkthrough"
 - "0.1.3 Walkthrough"
 - "0.1.4 Walkthrough"
---

<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#btn-walkthrough" aria-expanded="false" aria-controls="btn-walkthrough">
*&nbsp;*{: .fa .fa-question-circle} Show walkthrough
</button>
<div class="collapse" id="btn-walkthrough">

### 0.0 Empty project solution
{: id="0.0-walkthrough"}
[**Clone on Github**](https://github.com/CamilleBC/android-kotlin-basics/tree/b7aedaebebab286bda00cb2d55df0be104125992)

This is the starting step for our application.
We simply have a _MainActivity_ class that extends _AppCompatActivity_ (I'm using this for compatibility reasons on older phones, use whatever you need for your project).

The activity inflates its XML layout in the [_MainActivity.onCreate_](https://developer.android.com/guide/components/activities/activity-lifecycle#oncreate) function, which will then display the views on screen.

```kotlin
class MainActivity : AppCompatActivity() {

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // Inflate the XML layout
    setContentView(R.layout.activity_main)
    }
}
```

That's it. 

### 0.1.1 Launch a fragment solution
{: id="0.1.1-walkthrough"}
 [**Clone on Github**](https://github.com/CamilleBC/android-kotlin-basics/tree/a3117b27ba05fe1d359fcf3a7251f24a66294381)

We could directly implement our layout in the _MainActivity_. The advantage of using a _Fragment_ instead of an _Activity_ is that we can reuse fragments in different activities if needed, or display multiple fragments on screen depending on the display size, orientation, etc.

 1. We create a _DogListFragment_ with a simple TextView.
 => See [here](https://github.com/CamilleBC/android-kotlin-basics/blob/caaae274a959dba10cbf59d0d78646be1d175713/app/src/main/java/me/camillebc/basics/view/fragment/DogListFragment.kt) for the _DogListFragment_, [here](https://github.com/CamilleBC/android-kotlin-basics/blob/caaae274a959dba10cbf59d0d78646be1d175713/app/src/main/res/layout/fragment_dog_list.xml) for its layout.

 2. We also need to create a container for the _DogListFragment_'s layout in the _MainActivity_'s layout. We choose to use a _ConstraintLayout_ as it's the most versatile, and the preferred way to managed nested layouts.
 => See [here](https://github.com/CamilleBC/android-kotlin-basics/blob/caaae274a959dba10cbf59d0d78646be1d175713/app/src/main/res/layout/activity_main.xml) for the _MainActivity_'s layout.

 3. We use of the [_FragmentManager_](https://developer.android.com/reference/android/app/FragmentManager.html) in the _MainActivity.onCreate_ to add the fragment to the activity: 
  => See [here](https://github.com/CamilleBC/android-kotlin-basics/blob/caaae274a959dba10cbf59d0d78646be1d175713/app/src/main/java/me/camillebc/basics/view/MainActivity.kt) for the _MainActivity_.
	   1. We instantiate a _DogListFragment_ when the _Activity_ is created.
	   2. We get the _FragmentManager_ and start a transaction. Each Android activity has a [_FragmentManager_]. Surprisingly, it allows us to manage fragments. This will allow us to specify the type of the transaction, and, once defined, commit it for execution.
	   3. We add the fragment, and as parameter the ID of an element of the _MainActivity_'s layout in which the _DogListFragment_ instance will be inflated.
	   4. Finally, we commit the transaction. It will be scheduled on the main thread to be done the next time that the thread is ready.

```kotlin
val dogListFragment = DogListFragment()	            		// 1
supportFragmentManager.beginTransaction()         		// 2
   .add(R.id.constraintLayout_main_fragment, dogListFragment)	// 3
   .commit()		                        		// 4
```

### 0.1.2 Communicate between the fragments and the activities solution
{: id="0.1.2-walkthrough"}
[**Clone on Github**](https://github.com/CamilleBC/android-kotlin-basics/tree/15d54a84d8d1a1c4d93657e42ef0800127a43c23)
 1. We first take care of the UI changes. We add a floating button to the [_fragment_dog_list.xml_]() layout, as well as a drawable **+** icon.
 2. To communicate between the fragment and the activity, we need to add:
	1. A listener [**interface**](https://kotlinlang.org/docs/reference/interfaces.html#interfaces) in the fragment. This is an **abstract** class, and its role is only to force the activity that implements the fragment to implement its members/methods. Add the following to the _DogListFragment_:
		```kotlin
		interface OnAddClickListener {  
			    fun onDogListAddClick()  
		}
		```
	2. The implementation of the interface: a callback in the activity. We need to declare that the activity will implement the _DogListFragment_ listener:
		```kotlin
		class MainActivity : 
			DogListFragment.OnAddClickListener,  //activity implements the listener
			AppCompatActivity() {
		}
		```
		Then we override the abstract function of the interface by the actual implementation in _MainActivity_:
		```kotlin
		override fun onDogListAddClick() {  
			toast("Button clicked.")
		}
		```  
	
 3. We need to attach the actual callback to the button's [onClickListener](https://developer.android.com/reference/android/view/View.OnClickListener). 
	1. At this stage, all that we do is attach, in the _DogListFragment_, a reference to the activity that implements the listener. We attach the reference in [Fragment.onAttach](https://developer.android.com/reference/android/support/v4/app/Fragment.html#onattach_1) where we check if the context implements the listener. We cannot yet attach the actual callback, as the parent activity can be referenced, but it may not yet be fully functional. Our callback here relies on the `Toast` function, which requires the activity to be fully functional.
		```kotlin
		override fun onAttach(context: Context) {  
		    super.onAttach(context)  
		    // Attach the parent's activity listener to the fragment  
		  if (context is OnAddClickListener)  
		        onAddClickListener = context  
		    else  
		 throw RuntimeException("$context must implement OnAddClickListener")  
		}
		```
	3. In the second stage, we add the actual activity's callback's implementation to the button through the [`View.OnClickListener`](https://developer.android.com/reference/android/view/View.OnClickListener) method. This method will call the implemented callback on a user's click on the _View_ element. We add the callback in [Fragment.onActivityCreated](https://developer.android.com/reference/android/support/v4/app/Fragment.html#onactivitycreated), where the parent activity is sure to have been created. We use the reference to the parent's listener implementation, and set our  [`View.OnClickListener`](https://developer.android.com/reference/android/view/View.OnClickListener) method to call the callback method:
		```kotlin
		override fun onActivityCreated(savedInstanceState: Bundle?) {  
		    super.onActivityCreated(savedInstanceState)  
		    activity?.let {  
		  button_dogList_add.setOnClickListener{ onAddClickListener?.onDogListAddClick() }  
		 }}
		```

### 0.1.3 Manipulate fragments solution
{: id="0.1.3-walkthrough"}
[**Clone on Github**](https://github.com/CamilleBC/android-kotlin-basics/tree/6f7cbf3039c3a0a180f9bce948f4b9ba03f02cb2)

[Fragment.onCreateView](https://developer.android.com/reference/android/support/v4/app/Fragment.html#oncreateview).

### 0.1.4 Send the data to fragments
{: id="0.1.4-walkthrough"}

[**Clone on Github**](https://github.com/CamilleBC/android-kotlin-basics/tree/9ef782c9dca98ef6fcf3fc5d143b6bea1fd49718)

</div>
