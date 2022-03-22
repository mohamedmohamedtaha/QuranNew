package com.mohamedtaha.imagine.util

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.helper.HelperClass
import com.mohamedtaha.imagine.mvp.interactor.NavigationDrawarInteractor
import javax.inject.Inject
import javax.inject.Singleton

object ShareApp {

    fun shareApp(activity: Activity,intentShare: Intent) {
        intentShare.action = Intent.ACTION_SEND
        val resolveInfo: List<ResolveInfo> =
            activity.packageManager.queryIntentActivities(intentShare, PackageManager.MATCH_ALL)
        when {
            resolveInfo.size > 1 -> {
                try {
                    intentShare.type = "text/plain"
                    intentShare.putExtra(Intent.EXTRA_SUBJECT, activity.getString(R.string.nameSora))
                    val about: String =
                        activity.getString(R.string.about) + NavigationDrawarInteractor.GOOGLE_ACCOUNT_ID + activity.packageName
                    intentShare.putExtra(Intent.EXTRA_TEXT, about)
                    val intentChooser =
                        Intent.createChooser(intentShare, activity.getString(R.string.shareApp))
                    activity.startActivity(intentChooser)
                } catch (e: Exception) {
                    e.toString()
                }
            }
            intentShare.resolveActivity(activity.packageManager) != null -> {
                activity.startActivity(intentShare)
            }
            else -> {
                HelperClass.customToast(activity, activity.getString(R.string.notShare))
            }
        }
    }

}