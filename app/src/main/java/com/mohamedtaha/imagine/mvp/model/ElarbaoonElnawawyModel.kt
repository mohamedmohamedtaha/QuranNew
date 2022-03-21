package com.mohamedtaha.imagine.mvp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
class ElarbaoonElnawawyModel : Parcelable {
    var numberElhadeth: String? = null
    var nameElhadeth: String? = null
    var textElhadeth: String? = null
    var descriptionElhadeth: String? = null
    var translateElhadeth: String? = null
    var position = 0
}