---
layout: doc_nav
title: Basic
excerpt: Learning the basics of Android through a tutorial.
icon:
  type: fas
  name: fa-smile
color: green
navigation:
  - /basic/fragment
  - /basic/viewmodel
  - /basic/room
---

## Objectives

<div class="text-left" style="line-height: 180%;">

1. You will need to create an app that can display and manage a list of dogs.
2. You will need to have two screens, one to display the list of _Dog_ items, the other to edit or add a _Dog_ item.
     1. _Dog_ list:
        - Edit a _Dog_ item on click
        - Delete a _Dog_ item on long press 
     2. _Dog_ editor:
        - Edit a _Dog_ item properties
        - Add/update a _Dog item
3. The data should persist between launches, screen orientation changes, phone reboots, etc.
4. The _Dog_ class will need to have the following properties:
  - _name_: a string for the dog's name => **mandatory field**
  - _breed_: a string for the dog's breed => **mandatory field**
  - _subBreed_: a string for the dog's sub-breed => **optional field**

</div>