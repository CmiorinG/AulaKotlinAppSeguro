package github.com.CmiorinG

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory_items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val itemNumber: String,
    val name: String,
    val expiryDate: String,
    val productCode: String
)