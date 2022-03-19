package com.mohamedtaha.imagine.util

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.view.View
import android.view.Window
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.youtube.player.internal.v
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.helper.HelperClass
import com.mohamedtaha.imagine.helper.SharedPerefrenceHelper
import com.mohamedtaha.imagine.ui.activities.SwipePagesActivity
import com.mohamedtaha.imagine.ui.home.fragment.SwarFragment

object DialogUtils {
    fun showDialog(activity: Activity,text:String,onClickListener:View.OnClickListener){
        val dialog = Dialog(activity,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_details)

        val textviewSavePosition = dialog.findViewById<TextView>(R.id.TV_Save_Position)
        textviewSavePosition.text = text
        textviewSavePosition.setOnClickListener(onClickListener)
//        textviewSavePosition.setOnClickListener { view ->
//            onClickListener
//        dialog.dismiss()}

        val relativeLayout = dialog.findViewById<RelativeLayout>(R.id.Relative)
        relativeLayout.setOnClickListener { dialog.dismiss() }

        dialog.show()

    }

    fun showDialogForRetrieveReading(activity: Activity,title: String,message: String,yesClickListener: DialogInterface.OnClickListener,noClickListener:DialogInterface.OnClickListener){
        val builder = MaterialAlertDialogBuilder(activity)
        builder.setMessage(message)
        builder.setTitle(title)
        builder.setPositiveButton(activity.resources.getString(R.string.textYes),yesClickListener)
        builder.setNegativeButton(activity.resources.getString(R.string.textNo),noClickListener)
        val dialog = builder.create()
        dialog.show()

    }
}