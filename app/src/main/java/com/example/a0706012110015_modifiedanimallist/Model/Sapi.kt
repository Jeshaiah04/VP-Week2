package com.example.a0706012110015_modifiedanimallist.Model

import android.os.Parcel
import android.os.Parcelable

open class Sapi(override var nama: String?, override var usia: Int?) : Hewan(), Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override var jenis: String?
        get() = "Sapi"
        set(value) {}

    override fun suara() : String {
        return "Mwoooooooooooo"
    }

    override fun makan(tipe: Rumput) : String{
        return "Sapi-Mu barusan makan rumput!"
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nama)
        parcel.writeValue(usia)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Sapi> {
        override fun createFromParcel(parcel: Parcel): Sapi {
            return Sapi(parcel)
        }

        override fun newArray(size: Int): Array<Sapi?> {
            return arrayOfNulls(size)
        }
    }

}