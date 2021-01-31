package ir.danialchoopan.shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import ir.danialchoopan.shoppinglist.R
import ir.danialchoopan.shoppinglist.data.db.entety.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*

class AddItemShoppingDialog(
    context: Context,
    val addItemShoppingDialogListener: AddItemShoppingDialog.AddShopItemDialogListener
) :
    AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)
        btn_add.setOnClickListener {
            val name = et_name.text.toString()
            val amount = et_amount.text.toString()
            if (name.isNotEmpty() && amount.isNotEmpty()) {
                val item_shopping_list = ShoppingItem(name, amount.toInt())
                addItemShoppingDialogListener.onAddButtonClicked(item_shopping_list)
                dismiss()
            }
        }
        btn_cansel.setOnClickListener {
            cancel()
        }
    }

    interface AddShopItemDialogListener {
        fun onAddButtonClicked(item: ShoppingItem)
    }
}