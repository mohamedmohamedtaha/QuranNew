package com.mohamedtaha.imagine.util

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.helper.HelperClass

const val GOOGLE_ACCOUNT_ID = "https://play.google.com/store/apps/details?id="
private const val MARKET_ID = "market://details?id="

object RateApp {
    fun reteApp(activity: Activity,intent: Intent) {
        intent.action = Intent.ACTION_VIEW
        val resolveInfo = activity.packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL)
        if (resolveInfo.size > 1) {
            try {
                //Open the store and show the app
                intent.data = Uri.parse(MARKET_ID + activity.packageManager)
                val intentChooser =
                    Intent.createChooser(intent, activity.getString(R.string.rateOur))
                activity.startActivity(intentChooser)
            } catch (e: Exception) {
                //In state store there is not open by browser
                intent.data = Uri.parse(GOOGLE_ACCOUNT_ID + activity.packageManager)
                activity.startActivity(intent)

            }
        } else if (intent.resolveActivity(activity.packageManager) != null) {
            activity.startActivity(intent)
        } else {
            HelperClass.customToast(activity, activity.getString(R.string.notSupport))
        }
    }

}