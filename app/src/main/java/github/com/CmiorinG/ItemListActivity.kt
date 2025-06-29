package github.com.CmiorinG

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import github.com.CmiorinG.databinding.ActivityItemListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ItemListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemListBinding
    private lateinit var db: AppDatabase
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getDatabase(applicationContext)

        setupRecyclerView()

        lifecycleScope.launch {
            db.itemDao().getAllItems().collectLatest { itemsFromDb ->
                itemAdapter.updateItems(itemsFromDb)
            }
        }
    }

    private fun setupRecyclerView() {
        itemAdapter = ItemAdapter(emptyList())
        binding.itemsRecyclerView.apply {
            adapter = itemAdapter
            // Define como os itens serão organizados (uma lista vertical simples).
            layoutManager = LinearLayoutManager(this@ItemListActivity)
        }
    }
}