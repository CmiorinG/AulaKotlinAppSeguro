package github.com.CmiorinG

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import github.com.CmiorinG.databinding.ActivityRegisterItemBinding
import kotlinx.coroutines.launch

class RegisterItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterItemBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getDatabase(applicationContext)

        binding.saveItemButton.setOnClickListener {
            saveItemToDatabase()
        }

        binding.viewItemsButton.setOnClickListener {
            val intent = Intent(this, ItemListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveItemToDatabase() {
        val itemNumber = binding.itemNumberEditText.text.toString()
        val itemName = binding.itemNameEditText.text.toString()
        val expiryDate = binding.expiryDateEditText.text.toString()
        val productCode = binding.productCodeEditText.text.toString()


        if (itemNumber.isBlank() || itemName.isBlank() || expiryDate.isBlank() || productCode.isBlank()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        val newItem = Item(
            itemNumber = itemNumber,
            name = itemName,
            expiryDate = expiryDate,
            productCode = productCode
        )

        lifecycleScope.launch {
            db.itemDao().insert(newItem)

            Toast.makeText(this@RegisterItemActivity, "Legal, Seu cartão não está clonado!", Toast.LENGTH_LONG).show()
            clearFields()
        }
    }

    private fun clearFields() {
        binding.itemNumberEditText.text.clear()
        binding.itemNameEditText.text.clear()
        binding.expiryDateEditText.text.clear()
        binding.productCodeEditText.text.clear()
        binding.itemNumberEditText.requestFocus()
    }
}