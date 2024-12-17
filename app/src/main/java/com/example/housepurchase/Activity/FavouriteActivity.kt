package com.example.housepurchase.Activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.housepurchase.Adapter.ItemsAdapter
import com.example.housepurchase.Model.ItemsDomain
import com.example.housepurchase.databinding.ActivityDetailBinding
import com.example.housepurchase.databinding.ActivityFavouriteBinding
import androidx.recyclerview.widget.LinearLayoutManager


class FavouriteActivity : AppCompatActivity(){

    private lateinit var binding: ActivityFavouriteBinding
    private lateinit var favouriteAdapter: ItemsAdapter
    private var favouriteItems: ArrayList<ItemsDomain> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //thiết lập sự kiện nút back
        binding.backButton.setOnClickListener {
            finish() //Kêt thúc activity và back trang trc
            Toast.makeText(this, "Back button clicked!", Toast.LENGTH_SHORT).show()
        }

        val items = intent.getSerializableExtra("favouriteList") as? ArrayList<ItemsDomain>
        if (items != null) {
            favouriteItems = items.filter { it.isFavourite } as ArrayList<ItemsDomain>
            Toast.makeText(this, "Loaded ${favouriteItems.size} favourite items.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No data received in FavouriteActivity.", Toast.LENGTH_SHORT).show()
        }

        if (favouriteItems.isEmpty()) {
            binding.noFavouriteTextView.visibility = View.VISIBLE // Hiển thị nếu không có mục nào
        } else {
            binding.noFavouriteTextView.visibility = View.GONE // Ẩn nếu có mục yêu thích
        }

        initRecyclerView()

    }

        private fun initRecyclerView() {
            favouriteAdapter = ItemsAdapter(favouriteItems)
            binding.favouriteRecyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.favouriteRecyclerView.adapter = favouriteAdapter
    }

}