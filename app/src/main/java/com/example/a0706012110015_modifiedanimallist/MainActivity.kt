package com.example.a0706012110015_modifiedanimallist

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a0706012110015_modifiedanimallist.Adapter.HewanListAdapter
import com.example.a0706012110015_modifiedanimallist.Database.Data
import com.example.a0706012110015_modifiedanimallist.Database.Data.Companion.ArrayHewan
import com.example.a0706012110015_modifiedanimallist.Interface.Click
import com.example.a0706012110015_modifiedanimallist.Interface.EventListener
import com.example.a0706012110015_modifiedanimallist.Model.Hewan
import com.example.a0706012110015_modifiedanimallist.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), EventListener, Click {
    private lateinit var bind : ActivityMainBinding
    private var adapter = HewanListAdapter(Data.ArrayHewan, this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        supportActionBar?.hide()
        adapter = HewanListAdapter(ArrayHewan, this, this)
        listener()
        setupRecyclerView()
        CheckPermissions()
    }



    private fun listener(){
        bind.tambahHewan.setOnClickListener{
            val intent = Intent(this, FormActivity::class.java)
            startActivityForResult(intent, 10)
        }

        bind.filterSemua.setOnClickListener{
            updateFilterHewan("All")
        }

        bind.filterKambing.setOnClickListener{
            updateFilterHewan("Kambing")
        }

        bind.filterAyam.setOnClickListener{
            updateFilterHewan("Ayam")
        }

        bind.filterSapi.setOnClickListener{
            updateFilterHewan("Sapi")
        }

    }

    private fun updateFilterHewan(type : String){
        var hasil = ArrayList<Hewan>()
        if(type == "All"){
            hasil = ArrayHewan
        }

        for(hewan in ArrayHewan){
            if(hewan.jenis.equals(type)){
                hasil.add(hewan)
            }
        }
        adapter = HewanListAdapter(hasil, this, this)
        setupRecyclerView()
        adapter.notifyDataSetChanged()
    }

    private fun CheckPermissions() {


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
        }

    }

    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(baseContext)
        bind.binatangList.layoutManager = layoutManager   // Set layout
        bind.binatangList.adapter = adapter   // Set adapter
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (!grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val snackBar = Snackbar.make(bind.root, "Permission is denied", Snackbar.LENGTH_SHORT)
            snackBar.setAction("Dismiss", View.OnClickListener {
                snackBar.dismiss()
            }).show()
        }

    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    override fun onCardClick(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun pencetTombol(event: String, position: Int) {
        if(event == "edit"){
            val myIntent =Intent(this, FormActivity::class.java).apply {
                putExtra("position", position)
            }
            startActivity(myIntent)
        }else{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Hapus Data Hewan")
            builder.setMessage("Apakah kamu sangat yakin untuk menghapus data hewan?")

            builder.setPositiveButton(android.R.string.yes) { function, which ->
                Toast.makeText(this,"Data Hewan Berhasil Terhapus", Toast.LENGTH_SHORT).show()
                Data.ArrayHewan.removeAt(position)

                adapter.notifyDataSetChanged()
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(this,
                    android.R.string.no, Toast.LENGTH_SHORT).show()
            }
            builder.show()
    }
}

}