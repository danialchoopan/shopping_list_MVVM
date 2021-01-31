package ir.danialchoopan.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.danialchoopan.shoppinglist.R
import ir.danialchoopan.shoppinglist.data.db.entety.ShoppingItem
import ir.danialchoopan.shoppinglist.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemRecyclerViewAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) :
    RecyclerView.Adapter<ShoppingItemRecyclerViewAdapter.View_Holder>() {
    inner class View_Holder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): View_Holder = View_Holder(
        LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
    )

    override fun onBindViewHolder(holder: View_Holder, position: Int) {
        val curShoppingItem = items[position]
        holder.view.tvName.text = curShoppingItem.name
        holder.view.tvAmount.text = "${curShoppingItem.anount}"
        holder.view.ivDelete.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }
        holder.view.ivPlus.setOnClickListener {
            curShoppingItem.anount++
            viewModel.upsert(curShoppingItem)
        }
        holder.view.ivMines.setOnClickListener {
            if (curShoppingItem.anount > 0) {
                curShoppingItem.anount--
                viewModel.upsert(curShoppingItem)
            }
        }

    }

    override fun getItemCount(): Int = items.size
}