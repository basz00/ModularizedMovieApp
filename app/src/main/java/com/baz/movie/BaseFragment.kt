package com.baz.movie

import android.widget.Toast
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

    fun showToast(stringResourceId: Int) = Toast.makeText(activity, getString(stringResourceId), Toast.LENGTH_SHORT).show()

    fun showToast(message: String) = Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}