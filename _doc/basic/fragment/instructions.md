---
title: Instructions
date: 2019-04-20
sections:
  - "0.0"
  - "0.1.1"
  - "0.1.2"
  - "0.1.3"
  - "0.1.4"
pdf: true
---

You will implements two fragments: one called *DogListFragment*, the other *DogEditorFragment*.
You will need to have two screens, one to display the list of *Dog* items, the other to edit or add a *Dog* item.

Download the [pdf]({{ page.pdf_url }})

{{ page.pdf_url }}

{::options auto_ids="false" /}

### 0.0 Prerequisites
{: id="0.0"}
You should have a functioning [Android Studio 3.3](https://developer.android.com/studio) install.
You can clone this [project](https://github.com/CamilleBC/android-kotlin-basics/tree/1dee2ad0bb9143cf2ef9eb81c39977aa59e75fb7).
If you can build it, you're ready to go!

### 0.1.1 | Launch a fragment
{: id="0.1.1"}
Edit the *MainActivity* to display a fragment called *DogListFragment* on application's launch.

<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapse-011-hint1" aria-expanded="false" aria-controls="collapse-011-hint1">
  *&nbsp;*{: .fa .fa-question-circle} Hint 1
</button>
<div class="collapse" id="collapse-011-hint1">

**Hint #1**: See the [FragmentManager](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentManager) and [FragmentTransaction](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentManager) doc to add a fragment.

  <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapse-011-hint2" aria-expanded="false" aria-controls="collapse-011-hint2">
    *&nbsp;*{: .fa .fa-question-circle} Hint 2
  </button>
  <div class="collapse" id="collapse-011-hint2">

  **Hint #2**: You need to inflate the fragment inside a container in the parent activity's layout.

  </div>

[**Clone the solution**](https://github.com/CamilleBC/android-kotlin-basics/tree/caaae274a959dba10cbf59d0d78646be1d175713)

</div>

### 0.1.2 | Communicate between the fragments and the activities
{: id="0.1.2"}
Add a [*FloatingActionButton*](https://developer.android.com/guide/topics/ui/floating-action-button) to the *DogListFragment*, and use it's *OnClickListener* to display a *Toast* on the *MainActivity*.
You will need to learn about Kotlin's [interfaces](https://kotlinlang.org/docs/reference/interfaces.html#interfaces) to create a listener in the *DogListFragment*, and implement it's callback in the *MainActivity*.

<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapse-012-hint1" aria-expanded="false" aria-controls="collapse-012-hint1">
  *&nbsp;*{: .fa .fa-question-circle} Hint 1
</button>
<div class="collapse" id="collapse-012-hint1">

**Hint #1**: Setup an _interface_ called *OnAddClickListener* in the fragment with a function called *onAddClick()*, and implement it in the activity that calls the fragment. You need to add that *onAddClick()* callback to the button's *setOnClickListener*. Check [this StackOverflow question](https://stackoverflow.com/questions/44301301/android-how-to-achieve-setonclicklistener-in-kotlin) to see a button click listener.

  <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapse-012-hint2" aria-expanded="false" aria-controls="collapse-012-hint2">
    *&nbsp;*{: .fa .fa-question-circle} Hint 2
  </button>
  <div class="collapse" id="collapse-012-hint2">

  **Hint #2**: You need to pass a reference to parent's activity's implementation to the fragment. That means you need to have a variable of type _OnAddClickListener_ that will hold the reference to the callback when the activity is attached.

  </div>

[**Clone the solution**](https://github.com/CamilleBC/android-kotlin-basics/tree/6fc9f652e8fd06237e1f37eadab28f5d7fc3c9cc)
</div>

### 0.1.3 | Manipulate fragments
{: id="0.1.3"}
On the *FloatingActionButton* click, replace the existing *DogListFragment* by a new fragment called *DogEditorFragment*.

The *DogListFragment* should provide:

 - an *EditText* for the *Dog*'s name
 - an *EditText* for the *Dog*'s breed
 - an *EditText* for the *Dog*'s sub-breed
 - a *Button* that will go back to the *DogListFragment* on click.

<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapse-013-hint1" aria-expanded="false" aria-controls="collapse-013-hint1">
  *&nbsp;*{: .fa .fa-question-circle} Hint 1
</button>
<div class="collapse" id="collapse-013-hint1">

**Hint #1**: See the [FragmentManager](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentManager) and [FragmentTransaction](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentManager) doc to replace the fragment.

  <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapse-013-hint2" aria-expanded="false" aria-controls="collapse-013-hint2">
    *&nbsp;*{: .fa .fa-question-circle} Hint 2
  </button>
  <div class="collapse" id="collapse-013-hint2">

  **Hint #2**: Just do what you have done for the previous fragment: create a listener, implement a callback, and use it to manage the fragments in the activity.

  </div>

  [**Clone the solution**]()
</div>

### 0.1.4 | Send the data to fragments
{: id="0.1.4"}
Do not use *ViewModel* and *LiveData* at first. Try to implement a way to pass data through the callbacks without it. 
If a **mandatory** field is left empty (*name* or *breed*), do **not** add it to the data. Stay on the *DogListEditor* and warn the user.
If a valid *Dog* is provided, return the user to the *DogListFragment* and update the displayed list.

<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapse-014-hint1" aria-expanded="false" aria-controls="collapse-014-hint1">
  *&nbsp;*{: .fa .fa-question-circle} Hint 1
</button>
<div class="collapse" id="collapse-014-hint1">

**Hint #1:** See the  [*@Parcelize*](https://kotlinlang.org/docs/tutorials/android-plugin.html#parcelable) kotlin implementation, and use it to parcel the *Dog* item list and send it to the fragments.

  <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapse-014-hint2" aria-expanded="false" aria-controls="collapse-014-hint2">
    *&nbsp;*{: .fa .fa-question-circle} Hint 1
  </button>
  <div class="collapse" id="collapse-014-hint2">
  **Hint #2:** See [this](https://stackoverflow.com/questions/46551228/how-to-pass-and-get-value-from-fragment-and-activity) StackOverflow answer if you don't know where to begin when sending data from one fragment to the other.

  </div>

  [**Clone the solution**]()
</div>