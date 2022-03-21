package com.mohamedtaha.imagine.util

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.helper.HelperClass

private const val NAME_EMAIL = "mohamed01007919166@gmail.com"

object SendUs {

    fun sendUs(activity: Activity) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        val resolveInfo =
            activity.packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL)
        when {
            resolveInfo.size > 1 -> {
                intent.data = Uri.parse("mailto:")
                intent.type = "message/rfc2822"
                intent.putExtra(Intent.EXTRA_EMAIL, NAME_EMAIL)
                intent.putExtra(Intent.EXTRA_SUBJECT, activity.getString(R.string.subject))
                intent.putExtra(Intent.EXTRA_TEXT, activity.getString(R.string.messageBody))
                val intentChooser = Intent.createChooser(intent, activity.getString(R.string.sendTo))
                activity.startActivity(intentChooser)
            }
            intent.resolveActivity(activity.packageManager) != null -> {
                activity.startActivity(intent)
            }
            else -> {
                HelperClass.customToast(activity, activity.getString(R.string.notSupport))
            }
        }
    }
}