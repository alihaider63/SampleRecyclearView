package com.example.samplerecyclearview.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.samplerecyclearview.models.Butterfly
import com.example.samplerecyclearview.interfaces.MyInterface
import com.example.samplerecyclearview.R
class ButterflyAdapter(
    private var butterfliesList: ArrayList<Butterfly>,
    private val myInterface: MyInterface
    )
    : RecyclerView.Adapter<ButterflyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(butterfliesList, position, this, myInterface)
    }

    override fun getItemCount(): Int {
        return butterfliesList.size
    }

    fun addItem(butterfly: Butterfly) {
        butterfliesList.add(butterfly)
        notifyItemInserted(butterfliesList.size - 1)
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var butterflyImageView: ImageView? = itemView.findViewById<ImageView>(R.id.butterfly)
        var butterflyName: TextView? = itemView.findViewById<TextView>(R.id.butterflyName)
        var butterflyFamily: TextView? = itemView.findViewById<TextView>(R.id.butterflyFamily)
        var minusBtn: Button? = itemView.findViewById(R.id.minusBtn)
        var plusBtn: Button? = itemView.findViewById(R.id.plusBtn)
        var counter: TextView? = itemView.findViewById(R.id.counter)
        var deleteBtn: Button? = itemView.findViewById(R.id.delete_Item_Btn)
        var forwardBtn: ImageView? = itemView.findViewById(R.id.forward_ic)


        fun bind(butterfliesList: ArrayList<Butterfly>,
                 position: Int, butterflyAdapter: ButterflyAdapter,
                 myInterface: MyInterface
        ) {
            butterflyImageView?.let { butterflyImageView ->
                if (butterfliesList[position].butterfly != null &&
                    butterfliesList[position].image == null) {
                    butterfliesList[position].butterfly?.let {
                        butterflyImageView.setImageResource(
                            it
                        )
                    }
                } else if (butterfliesList[position].image != null &&
                        butterfliesList[position].butterfly == null){
                    butterfliesList[position].image?.let {
                        butterflyImageView.setImageBitmap(it)
                    }
                } else {
                    val imageTaken: Bitmap? = BitmapFactory.decodeFile(butterfliesList[position].imagePath)
                    imageTaken?.let {
                        butterflyImageView.setImageBitmap(imageTaken)
                    }
                }
            }

            butterflyName?.let {
                it.text = butterfliesList[position].name
            }

            butterflyFamily?.let {
                it.text = butterfliesList[position].family
            }

            butterflyImageView?.let {
                it.setOnClickListener {
                        myInterface.onButterClicked(position, butterfliesList)
                }
            }
            counter?.let {
                it.text = "${butterfliesList[position].count}"
//                it.text = (butterfliesList[position].count).toString()

            }
            minusBtn?.let {
                it.setOnClickListener {
//                    butterfliesList[position].count -= 1
//                    myAdapter.notifyItemChanged(position)
                    butterfliesList[position].count -= 1
                    counter?.text = (butterfliesList[position].count).toString()
                }
            }
            plusBtn?.let {
                it.setOnClickListener {
                    //butterfliesList[position].count += 1
                    //myAdapter.notifyItemChanged(position)
                    butterfliesList[position].count += 1
                    counter?.text = (butterfliesList[position].count).toString()
                }
            }
            deleteBtn?.let {
                it.setOnClickListener {
                    butterfliesList.removeAt(position)
                    butterflyAdapter.notifyItemRemoved(position)
                    butterflyAdapter.notifyItemRangeChanged(position, butterfliesList.size)
                }
            }
            forwardBtn?.setOnClickListener {
                myInterface.onForwardPressed(position, butterfliesList)
            }
        }
    }
}
