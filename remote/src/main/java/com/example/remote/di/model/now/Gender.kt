package com.example.remote.di.model.now

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gender(
    @SerializedName("id")
    @Expose
    val id: Int = 0
):Parcelable




