---
title: Walkthrough
date: 2019-04-20
sections:
 - "0.2.1 Walkthrough"
 - "0.2.2 Walkthrough"
---

<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#btn-walkthrough" aria-expanded="false" aria-controls="btn-walkthrough">
*&nbsp;*{: .fa .fa-question-circle} Show walkthrough
</button>
<div class="collapse" id="btn-walkthrough">

### 0.2.1 LiveData
{: id="0.2.1-walkthrough"}
[**Clone on Github**](https://github.com/CamilleBC/android-kotlin-basics/tree/)

[LiveData](https://developer.android.com/topic/libraries/architecture/livedata) is a holder class for some observable data. It only updates components that are active.

You subscribe an observer to some data. When the data updates, the LiveData will notify the active subscribed observers.

### 0.2.2 ViewModel
{: id="0.2.2-walkthrough"}
 [**Clone on Github**](https://github.com/CamilleBC/android-kotlin-basics/tree/)

A [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel.html) is basically a container for some data, and its lifecycle is different from those of fragments and activities. They are kep alive during configuration changes, if an activity/fragment is re-created, etc.

The ViewModel object is scoped is to the lifecycle passed to the [ViewModelProvider](https://developer.android.com/reference/androidx/lifecycle/ViewModelProvider.html): in case of an activity, when it finishes, in case of a fragment, when it is detached.

</div>
