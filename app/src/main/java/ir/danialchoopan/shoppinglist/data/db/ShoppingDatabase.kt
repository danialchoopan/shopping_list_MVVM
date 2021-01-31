package ir.danialchoopan.shoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.danialchoopan.shoppinglist.data.db.entety.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract fun getShoppingDao(): ShoppingDao

    companion object {
        @Volatile
        private var intense: ShoppingDatabase? = null
        private var LOOK = Any()
        operator fun invoke(context: Context) = intense ?: synchronized(LOOK) {
            intense ?: createDatabase(context).also { intense = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context, ShoppingDatabase::class.java, "ShoppingDB.db"
        ).build()
    }
}