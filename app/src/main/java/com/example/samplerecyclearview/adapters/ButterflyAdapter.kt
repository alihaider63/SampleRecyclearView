package com.example.samplerecyclearview.adapters

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.samplerecyclearview.models.Butterfly
import com.example.samplerecyclearview.interfaces.ButterflyInterface
import com.example.samplerecyclearview.databinding.ItemViewBinding

class ButterflyAdapter(
    private val butterflies: ArrayList<Butterfly>,
    private val butterflyInterface: ButterflyInterface
    )
    : RecyclerView.Adapter<ButterflyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(butterflies[position], position)
    }

    override fun getItemCount(): Int {
        return butterflies.size
    }

    fun addButterfly(butterfly: Butterfly) {
        butterflies.add(butterfly)
        notifyItemInserted(butterflies.size - 1)
    }

    inner class MyViewHolder(binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root) {

        private val butterflyImageView: ImageView = binding.butterflyImageView
        private val butterflyName = binding.butterflyName
        private val butterflyFamily = binding.butterflyFamily
        private val minusBtn = binding.minusBtn
        private val plusBtn = binding.plusBtn
        private val counter = binding.counter

        @SuppressLint("ResourceType")
        fun bind(butterfly: Butterfly, position: Int) {

            when {
                butterfly.imageRes != null -> butterflyImageView.setImageResource(butterfly.imageRes)
                butterfly.imageBitmap != null -> butterflyImageView.setImageBitmap(butterfly.imageBitmap)
                else -> {
                    val imageBitmap=BitmapFactory.decodeFile(butterfly.imagePath)
                    butterflyImageView.setImageBitmap(imageBitmap)
                }
            }

            butterflyName.text = butterfly.butterflyName

            butterflyFamily.text = butterfly.family

            butterflyImageView.setOnClickListener {
                butterflyInterface.onImageClicked(butterfly)
            }

            counter.text = butterfly.count.toString()

            minusBtn.setOnClickListener {
                butterfly.count -= 1
                counter.text = (butterfly.count).toString()
            }

            plusBtn.setOnClickListener {
                butterfly.count += 1
                counter.text = (butterfly.count).toString()
            }

            itemView.setOnClickListener {
                butterflyInterface.onForwardIconPressed(butterfly)
            }

            itemView.setOnLongClickListener {
                butterflies.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, butterflies.size)
                true
            }
        }
    }
}
