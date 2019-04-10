# Android Basics

This project is a tutorial for **Android**, using **Kotlin**.

You can find a companion website for the tutorial here: [camillebc.me](https://camillebc.me/android_tutorial/0.1/basics.html)

## 0.0 - Empty project
Clone [this Android project]() and build it.
This is a necessary step before starting the tutorial. You will need **Android Studio 3.4** and **Kotlin 1.3.31**.

You should be able to install the app and it should display "I am MainActivity" when launched from the phone.

## 0.1 - Fragments

**You can find all the instructions, hints, and the full walk-through [here](https://camillebc.me/android_tutorial/0.1/fragments.html).**

### 0.1.1 - Launch a fragment
Edit the *MainActivity* to display a fragment called *DogListFragment* on application’s launch. It should display "I am DogListFragment".

Clone [this]() to get the solution.

### 0.1.2 - Communicate between fragments and activities
Add a [*FloatingActionButton*](https://developer.android.com/guide/topics/ui/floating-action-button) to the *DogListFragment*, and use it’s *OnClickListener* to display a *Toast* on the *MainActivity*. You will need to learn about Kotlin’s [interfaces](https://kotlinlang.org/docs/reference/interfaces.html#interfaces) to create a listener in the _DogListFragment_, and implement it’s callback in the _MainActivity_.

Clone [this]() to get the solution.

### 0.1.3 - Manipulate fragments
On the *FloatingActionButton* click, replace the existing *DogListFragment* by a new fragment called *DogEditorFragment*.

The _DogListFragment_ should provide:
-   an _EditText_ for the _Dog_’s name
-   an _EditText_ for the _Dog_’s breed
-   an _EditText_ for the _Dog_’s sub-breed
-   a _Button_ that will go back to the _DogListFragment_ on click.

Clone [this]() to get the solution.

### 0.1.4 - Send data to other fragments
Do not use _ViewModel_ and _LiveData_ at first. Try to implement a way to pass data through the callbacks without it. If a **mandatory** field is left empty (_name_ or _breed_), do **not** add it to the data. Stay on the _DogListEditor_ and warn the user. If a valid _Dog_ is provided, return the user to the _DogListFragment_ and update the displayed list.

Clone [this]() to get the solution.
