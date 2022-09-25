package com.example.a0706012110015_modifiedanimallist.Model

import android.os.Parcel
import android.os.Parcelable

open class Kambing (override var nama: String?,override var usia: Int?): Hewan(), Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override var jenis: String?
        get() = "Kambing"
        set(value) {}

    override fun suara() : String {
        return "Embheckkkkk"
    }

    override fun makan(tipe: Rumput) : String{
        return "Kambing-Mu barusan makan rumput!"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nama)
        parcel.writeValue(usia)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Kambing> {
        override fun createFromParcel(parcel: Parcel): Kambing {
            return Kambing(parcel)
        }

        override fun newArray(size: Int): Array<Kambing?> {
            return arrayOfNulls(size)
        }
    }
}