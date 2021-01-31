package ir.danialchoopan.shoppinglist.data.db.entety

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItem(
    var name: String,
    var anount: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}