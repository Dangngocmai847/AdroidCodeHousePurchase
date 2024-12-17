package com.example.housepurchase.Activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.housepurchase.Adapter.ItemsAdapter
import com.example.housepurchase.Model.ItemsDomain
import com.example.housepurchase.R
import com.example.housepurchase.databinding.ActivityMainBinding
import com.example.housepurchase.databinding.ViewholderItemBinding
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import android.content.Intent
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    private lateinit var itemsAdapter: ItemsAdapter
    private lateinit var items: ArrayList<ItemsDomain> // Danh sách gốc
    private lateinit var filteredItems: ArrayList<ItemsDomain> // Danh sách đã lọc


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        items= arrayListOf(
            ItemsDomain (
                "Apartment",
                "Royal Apartment",
                "Los Angeles",
                "item_1",
                1500,
                2,
                3,
                350,
                true,
                4.5,
                "This 2-bed/1-bath home boasts an enormous, open-living plan, accented by " +
                        "striking architectural features and high-end finishes. Be inspired by " +
                        "open sight lines that embrace the outdoors, crowned by stunning coffered ceilings.",
            ),

            ItemsDomain(
                "House",
                "House with Great View",
                "New York",
                "item_2",
                800,
                1,
                2,
                500,
                false,
                4.9,
                "This 2-bed/1-bath home boasts an enormous, open-living plan, accented by " +
                        "striking architectural features and high-end finishes. Be inspired by " +
                        "open sight lines that embrace the outdoors, crowned by stunning coffered ceilings.",
            ),

            ItemsDomain(
                "Villa",
                "Royal Villa",
                "Chicago",
                "item_3",
                999,
                2,
                1,
                400,
                true,
                4.7,
                "This 2-bed/1-bath home boasts an enormous, open-living plan, accented by " +
                        "striking architectural features and high-end finishes. Be inspired by " +
                        "open sight lines that embrace the outdoors, crowned by stunning coffered ceilings.",
            ),

            ItemsDomain(
                "House",
                "Beauty House",
                "Texas",
                "item_4",
                1750,
                3,
                2,
                1100,
                true,
                4.3,
                "This 2-bed/1-bath home boasts an enormous, open-living plan, accented by " +
                        "striking architectural features and high-end finishes. Be inspired by " +
                        "open sight lines that embrace the outdoors, crowned by stunning coffered ceilings.",
            )
        )

        filteredItems = ArrayList(items) // Ban đầu danh sách lọc giống danh sách gốc

        initList()
        setupSearchBar()

        setupFavouriteNavigation()

        // Ẩn thanh trạng thái
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun initList() {
        itemsAdapter = ItemsAdapter(filteredItems) // Kết nối danh sách lọc với Adapter
        binding.recommendedView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recommendedView.adapter = itemsAdapter
    }

    private fun setupSearchBar() {
        binding.searchEdt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterList(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterList(query: String) {
        filteredItems.clear()
        if (query.isEmpty()) {
            filteredItems.addAll(items) // Hiển thị toàn bộ danh sách nếu không nhập
        } else {
            val searchQuery = query.lowercase()
            items.filter { item ->
                item.title.lowercase().contains(searchQuery) ||
                        item.address.lowercase().contains(searchQuery)
            }.also { filteredItems.addAll(it) }
        }
        itemsAdapter.notifyDataSetChanged() // Cập nhật danh sách hiển thị
    }

    private fun setupFavouriteNavigation() {
        val navigationBar = findViewById<ChipNavigationBar>(R.id.navigationBar)

        navigationBar.setOnItemSelectedListener { itemId ->
            if (itemId == R.id.favourite) {

                Toast.makeText(this, "Favourite selected!", Toast.LENGTH_SHORT).show()
                // Chuyển sang FavouriteActivity
                val intent = Intent(this, FavouriteActivity::class.java)
                intent.putExtra("favouriteList", items)
                startActivity(intent)

                navigationBar.setItemSelected(-1)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val navigationBar  = findViewById<ChipNavigationBar>(R.id.navigationBar)
        navigationBar.setItemSelected(-1) // Đảm bảo trạng thái ban đầu khi quay về
    }

}


