package jp.co.cyberagent.dojo2020

import android.app.Activity
import android.content.Intent
import android.widget.ImageView
import androidx.fragment.app.Fragment

open class SelectPhoto(private val fragment: Fragment, private val image: ImageView): Activity() {

    fun selectPhoto() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {

            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }

        fragment.startActivityForResult(intent, READ_REQUESTED_CODE)
    }

    companion object {
        const val READ_REQUESTED_CODE = 42
    }

}

