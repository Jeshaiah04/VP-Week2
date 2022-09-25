package com.example.a0706012110015_modifiedanimallist.Model

import android.os.Parcel
import android.os.Parcelable

open class Hewan{
    open var nama : String? = ""
    open var usia : Int? = null
    open var jenis : String? = ""
    var gambar: String = ""

    open fun makan(rumput: Rumput) : String{
        return ""
    }

    open fun makan(biji: Biji) : String{
        return ""
    }

    open fun suara() : String{
        return ""
    }


}

