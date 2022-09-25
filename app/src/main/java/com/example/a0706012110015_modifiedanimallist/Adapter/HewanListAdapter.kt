package com.example.a0706012110015_modifiedanimallist.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a0706012110015_modifiedanimallist.Interface.Click
import com.example.a0706012110015_modifiedanimallist.Interface.EventListener
import com.example.a0706012110015_modifiedanimallist.Model.*
import com.example.a0706012110015_modifiedanimallist.R
import com.example.a0706012110015_modifiedanimallist.databinding.ActivityCardBinding
import java.util.*

class HewanListAdapter(val listHewan: ArrayList<Hewan>, val eventListener: EventListener, val click: Click):
    RecyclerView.Adapter<HewanListAdapter.viewHolder>() {

    class viewHolder (itemView: View, eventListener: EventListener, click: Click): RecyclerView.ViewHolder(itemView) {
        val bind = ActivityCardBinding.bind(itemView)

        fun setData(data: Hewan, cardClick: EventListener, click: Click){
            bind.namaHewan.text = data.nama
            bind.usiaHewan.text = "Usia : " + data.usia.toString() + " tahun"

            if (data.gambar.isNotEmpty()) {
                bind.hewanImage.setImageURI(Uri.parse(data.gambar))
            }

            bind.makanButton.setOnClickListener{
                if(data is Sapi){
                    click.onCardClick(data.makan(Rumput("Rumput")))

                }else if(data is Ayam){
                    click.onCardClick(data.makan(Biji("Biji")))

                }else if(data is Kambing){
                    click.onCardClick(data.makan(Rumput("Rumput")))
                }

            }

            bind.suaraButton.setOnClickListener {
                click.onCardClick(data.suara())
            }

            bind.jenisHewan.text = data.jenis

            bind.editButton.setOnClickListener {
                cardClick.pencetTombol("edit", adapterPosition)
            }
            bind.deleteButton.setOnClickListener {
                cardClick.pencetTombol("delete", adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.activity_card, parent, false)
        return viewHolder(view, eventListener, click)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(listHewan[position], eventListener, click)
    }

    override fun getItemCount(): Int {
        return listHewan.size
    }

}