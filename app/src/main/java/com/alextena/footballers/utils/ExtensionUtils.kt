@file:Suppress("DEPRECATION")

package com.alextena.footballers.utils

import android.content.Context
import android.net.ConnectivityManager
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

object ExtensionUtils {

    //Show toast
    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    fun Context.toast(stringResId: Int) =
        Toast.makeText(this, resources.getString(stringResId), Toast.LENGTH_SHORT).show()

    //Show snackbar
    fun View.snackbar(message: String, view: View) = Snackbar.make(this, message, Snackbar.LENGTH_SHORT).setAnchorView(view).show()

    fun View.snackbar(message: String) = Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()

    fun View.snackbar(stringResId: Int, view: View) =
        Snackbar.make(this, resources.getString(stringResId), Snackbar.LENGTH_SHORT).setAnchorView(view).show()

    fun View.snackbar(stringResId: Int) =
        Snackbar.make(this, resources.getString(stringResId), Snackbar.LENGTH_SHORT).show()

    //Show keyboard
    fun View.showKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        this.requestFocus()
        imm.showSoftInput(this, 0)
    }

    //Hide keyboard
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        this.requestFocus()
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    //Show view
    fun View.show(): View {
        if (visibility != View.VISIBLE) {
            visibility = View.VISIBLE
        }
        return this
    }

    //Hide view
    fun View.hide(): View {
        if (visibility != View.GONE) {
            visibility = View.GONE
        }
        return this
    }

    //Scan using an EditText
    fun EditText.scan(keyEvent: KeyEvent): String? {
        return if (((keyEvent.keyCode == 285) || (keyEvent.keyCode == 286) || (keyEvent.keyCode == 0) || (keyEvent.keyCode == 66)
                    || (keyEvent.keyCode == 262) || (keyEvent.keyCode == 10036)) && (keyEvent.action == KeyEvent.ACTION_UP)
        ) {
            val data = this.text.toString()
            this.requestFocus()
            this.text = null
            data
        } else {
            null
        }
    }

    //Check if internet is available
    fun Context.isInternetAvailable(): Boolean {
        try {
            val cm =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}