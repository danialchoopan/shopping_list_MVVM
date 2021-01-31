package ir.danialchoopan.shoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ir.danialchoopan.shoppinglist.R
import ir.danialchoopan.shoppinglist.data.db.ShoppingDatabase
import ir.danialchoopan.shoppinglist.data.db.entety.ShoppingItem
import ir.danialchoopan.shoppinglist.data.repositories.ShoppingRepository
import ir.danialchoopan.shoppinglist.other.ShoppingItemRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this@ShoppingActivity)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        val viewModel =
            ViewModelProviders.of(this@ShoppingActivity, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemRecyclerViewAdapter(listOf(), viewModel)
        rv_shopping_items.layoutManager = LinearLayoutManager(this@ShoppingActivity)
        rv_shopping_items.adapter = adapter

        viewModel.getAllShoppingListItem().observe(this@ShoppingActivity, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })
        floatingActionButton.setOnClickListener {
            AddItemShoppingDialog(
                this@ShoppingActivity,
                object : AddItemShoppingDialog.AddShopItemDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
        }
    }
}