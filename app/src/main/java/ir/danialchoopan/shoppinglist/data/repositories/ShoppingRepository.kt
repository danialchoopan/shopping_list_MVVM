package ir.danialchoopan.shoppinglist.data.repositories

import ir.danialchoopan.shoppinglist.data.db.ShoppingDatabase
import ir.danialchoopan.shoppinglist.data.db.entety.ShoppingItem

class ShoppingRepository(
    private val db: ShoppingDatabase
) {
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)
    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)
    fun getAllShoppingItem() = db.getShoppingDao().getAllShoppingItem()
}