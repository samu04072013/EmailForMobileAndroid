package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable

data class CorreoModel(
    val asunto: String,
    val destinatario: String,
    val cuerpo: String
): Parcelable
{
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString()?:"",
        parcel.readString() ?: ""
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(asunto)
        parcel.writeString(destinatario)
        parcel.writeString(cuerpo)
    }
    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<CorreoModel> {
        override fun createFromParcel(parcel: Parcel): CorreoModel {
            return CorreoModel(parcel)
        }

        override fun newArray(size: Int): Array<CorreoModel?> {
            return arrayOfNulls(size)
        }
    }
}