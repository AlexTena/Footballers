package com.alextena.footballers.utils

object CurrentFragment {

    var currentFragment: String = "onGoing"

    fun changeCurrentFragment(status: String) {
        currentFragment = status
    }
}