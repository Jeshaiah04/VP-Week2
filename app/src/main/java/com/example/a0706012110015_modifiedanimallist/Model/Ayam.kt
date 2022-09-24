package com.example.a0706012110015_modifiedanimallist.Model

import android.os.Parcel
import android.os.Parcelable

open class Ayam (override var nama: String?, override var usia: Int?): Hewan(), Parcelable {

    override var jenis: String?
        get() = "Ayam"
        set(key) {}
    override var gambar: String? = null

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Ayam> {
        override fun createFromParcel(parcel: Parcel): Ayam {
            return Ayam(parcel)
        }

        override fun newArray(size: Int): Array<Ayam?> {
            return arrayOfNulls(size)
        }
    }

}