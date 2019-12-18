package com.giedrius.baseproject.utils.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.giedrius.baseproject.R

fun FragmentActivity.replaceFragment(fragment: Fragment, container: Int = R.id.fragmentContainer) {
    supportFragmentManager.beginTransaction()
        .replace(container, fragment)
        .commit()
}
