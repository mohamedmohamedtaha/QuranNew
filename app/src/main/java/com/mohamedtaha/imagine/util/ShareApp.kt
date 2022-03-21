package com.mohamedtaha.imagine.util

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.helper.HelperClass
import com.mohamedtaha.imagine.mvp.interactor.NavigationDrawarInteractor

object ShareApp {
    fun shareApp(activity: Activity) {
        val intentShar = Intent()
        intentShar.action = Intent.ACTION_SEND
        val resolveInfo: List<ResolveInfo> =
            activity.packageManager.queryIntentActivities(intentShar, PackageManager.MATCH_ALL)
        when {
            resolveInfo.size > 1 -> {
                try {
                    intentShar.type = "text/plain"
                    intentShar.putExtra(Intent.EXTRA_SUBJECT, activity.getString(R.string.nameSora))
                    val about: String =
                        activity.getString(R.string.about) + NavigationDrawarInteractor.GOOGLE_ACCOUNT_ID + activity.packageName
                    intentShar.putExtra(Intent.EXTRA_TEXT, about)
                    val intentChooser =
                        Intent.createChooser(intentShar, activity.getString(R.string.shareApp))
                    activity.startActivity(intentChooser)
                } catch (e: Exception) {
                    e.toString()
                }
            }
            intentShar.resolveActivity(activity.packageManager) != null -> {
                activity.startActivity(intentShar)
            }
            else -> {
                HelperClass.customToast(activity, activity.getString(R.string.notShare))
            }
        }
    }

}