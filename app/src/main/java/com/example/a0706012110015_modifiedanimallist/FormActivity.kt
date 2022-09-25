package com.example.a0706012110015_modifiedanimallist

import android.R
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.a0706012110015_modifiedanimallist.Database.Data.Companion.ArrayHewan
import com.example.a0706012110015_modifiedanimallist.Model.Ayam
import com.example.a0706012110015_modifiedanimallist.Model.Hewan
import com.example.a0706012110015_modifiedanimallist.Model.Kambing
import com.example.a0706012110015_modifiedanimallist.Model.Sapi
import com.example.a0706012110015_modifiedanimallist.databinding.ActivityFormBinding

class FormActivity: AppCompatActivity() {
    private lateinit var bind: ActivityFormBinding
    private var position: Int = -1
    private var gambar: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityFormBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        listener()
        checkIfValuePassed()

    }

    private fun checkIfValuePassed() {
        position = intent.getIntExtra("Position", -1)
        val act_bar = supportActionBar
        if (position != -1) {
            setValueToInput(ArrayHewan.get(position))
            bind.buttonSimpan.setText("UPDATE")
            act_bar?.setTitle("Edit Hewan")
        } else {
            bind.buttonSimpan.setText("ADD")
            act_bar?.setTitle("Tambah Hewan")
        }
    }

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

        if (it.resultCode == Activity.RESULT_OK){
            val uri = it.data?.data
            bind.hewanImage.setImageURI(uri)
            gambar = uri.toString()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                baseContext.getContentResolver().takePersistableUriPermission(Uri.parse(it.data?.data.toString()),
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
            }
        }

    }


    private fun setValueToInput(hewan: Hewan) {
        bind.InputNamaHewan.editText?.setText(hewan.nama)
        var radioButton = 0

        if (hewan.jenis == "Ayam") {
            radioButton = bind.radioButtonAyam.id
        } else if (hewan.jenis == "Kambing") {
            radioButton = bind.radioButtonKambing.id
        } else if (hewan.jenis == "Sapi") {
            radioButton = bind.radioButtonSapi.id
        }

        bind.JenisHewan.check(radioButton)

        bind.InputUsiaHewan.editText?.setText(hewan.usia.toString())
        Log.d("TAG", "setValueToInput: ${hewan.gambar}")
        if (hewan.gambar != null) {
            bind.hewanImage.setImageURI(Uri.parse(hewan.gambar))
        }

        gambar = hewan.gambar.toString()
    }

    private fun listener() {
        bind.hewanImage.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        bind.buttonSimpan.setOnClickListener {
            val nama = bind.InputNamaHewan.editText?.text.toString()
            val jenisId = bind.JenisHewan.checkedRadioButtonId

            var umur = bind.InputUsiaHewan.editText?.text.toString()
            if (umur == "") {
                umur = "-1"
            }
            var hewan: Hewan? = null
            if (bind.radioButtonAyam.id == jenisId) {
                hewan = Ayam(nama = nama, usia = umur.toInt())
            } else if (bind.radioButtonSapi.id == jenisId) {
                hewan = Sapi(nama = nama, usia = umur.toInt())
            } else if (bind.radioButtonKambing.id == jenisId) {
                hewan = Kambing(nama = nama, usia = umur.toInt())
            }
            inputChecker(hewan!!)
        }



    }

    private fun inputChecker(hewan: Hewan) {
        var isCompleted = true

        if(hewan.nama!!.isEmpty()){
            bind.InputNamaHewan.error = "Tolong isi kolom nama"
            isCompleted = false
        }else{
            bind.InputNamaHewan.error = ""
        }


        if(hewan.usia!! == 0){
            bind.InputUsiaHewan.error = "Tolong isi kolom umur"
            isCompleted = false
        }else if(hewan.usia!! < 0){
            bind.InputUsiaHewan.error = "Tolong input umur terlebih dahulu"
            isCompleted = false
        }else{
            bind.InputUsiaHewan.error = ""
        }


        if (isCompleted){
            hewan.gambar = gambar

            if(position == -1){
                ArrayHewan.add(hewan)
                setResult(1)

            }else{
                ArrayHewan.set(position, hewan)
                setResult(2)
            }
            Log.d("awdaw", gambar)
            Log.d("wadaw", "wadawd")
            finish()

        }
    }

}