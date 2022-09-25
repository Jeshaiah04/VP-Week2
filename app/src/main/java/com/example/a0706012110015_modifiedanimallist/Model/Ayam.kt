package com.example.a0706012110015_modifiedanimallist.Model

import android.os.Parcel
import android.os.Parcelable

open class Ayam(override var nama: String?,override var usia: Int?): Hewan(), Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override var jenis:String?
    get() ="Ayam"
    set(value) {}

    override fun suara():String {
        return "Cockoroyok!"
    }

    override fun makan(tipe: Biji):String{
        return "Ayam-Mu makan biji!"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nama)
        parcel.writeValue(usia)
    }

    override fun describeContents(): Int {
        return 0
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

