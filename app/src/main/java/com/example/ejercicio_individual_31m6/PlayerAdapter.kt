package com.example.ejercicio_individual_31m6

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio_individual_31m6.Model.Player
import com.example.ejercicio_individual_31m6.databinding.PlayerListBinding


class PlayerAdapter(private var dataset: List<Player>,private val semuestra: Boolean,private val clickListener: (Player)->Unit): RecyclerView.Adapter<PlayerAdapter.AdapterViewHolder>() {

    class AdapterViewHolder(private val binding: PlayerListBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(player: Player) {
            binding.textView.text = player.apodo
            binding.textView2.text = player.nombrecompleto
            binding.textView3.text = player.edad.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val binding = PlayerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterViewHolder(binding)


    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val currentPlayer = dataset[position]
        holder.bind(currentPlayer)

        if (semuestra) {
            holder.itemView.setOnClickListener {
                clickListener(currentPlayer)
            }

        }
    }


    fun updateData(newDataset: List<Player>) {
        dataset = newDataset
        notifyDataSetChanged()
    }


}
