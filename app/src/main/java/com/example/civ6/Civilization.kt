package com.example.civ6


import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi


class Civilization(val name: String?, var leader: String?, val image: Int, var civilizationBan: Boolean): Parcelable  {
    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readBoolean())

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(leader)
        parcel.writeInt(image)
        parcel.writeBoolean(civilizationBan)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Civilization> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Civilization {
            return Civilization(parcel)
        }

        override fun newArray(size: Int): Array<Civilization?> {
            return arrayOfNulls(size)
        }
    }

}

