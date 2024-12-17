package com.example.housepurchase.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.housepurchase.Activity.DetailActivity
import com.example.housepurchase.Activity.FavouriteActivity
import com.example.housepurchase.Model.ItemsDomain
import com.example.housepurchase.R
import com.example.housepurchase.databinding.ViewholderItemBinding
import com.google.firebase.firestore.persistentCacheSettings

class ItemsAdapter(private val items: ArrayList<ItemsDomain>) :
    RecyclerView.Adapter<ItemsAdapter.Viewholder>(){

    private lateinit var context: Context

    class Viewholder(val binding: ViewholderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapter.Viewholder {
        val binding =
            ViewholderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: ItemsAdapter.Viewholder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            titleTxt.text = item.title
            priceTxt.text = "$${item.price}"
            bedTxt.text = item.bed.toString()
            bathTxt.text = item.bath.toString()

            val drawableResourceId = holder.itemView.resources.getIdentifier(
                item.pickPath, "drawable", holder.itemView.context.packageName
            )
            Glide.with(context)
                .load(drawableResourceId)
                .into(pic)

            // Set icon cho favouriteButton tùy theo trạng thái isFavourite
            if (item.isFavourite) {
                favouriteButton.setImageResource(R.drawable.ic_heart_filled) // Trái tim đầy
            } else {
                favouriteButton.setImageResource(R.drawable.ic_heart_outline) // Trái tim rỗng
            }

            favouriteButton.setOnClickListener {
                // Đảo ngược trạng thái isFavourite
                item.isFavourite = !item.isFavourite

                // Cập nhật icon dựa trên trạng thái mới
                if (item.isFavourite) {
                    favouriteButton.setImageResource(R.drawable.ic_heart_filled)
                } else {
                    favouriteButton.setImageResource(R.drawable.ic_heart_outline)
                }
            }

            holder.itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("object", item)
                context.startActivity(intent)
            }



        }
    }

    override fun getItemCount(): Int = items.size

}
