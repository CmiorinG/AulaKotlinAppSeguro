package github.com.CmiorinG

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import github.com.CmiorinG.databinding.ItemRowLayoutBinding

class ItemAdapter(private var items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // Esta classe interna representa a view de UMA ÚNICA linha da lista.
    // Ela segura as referências para os TextViews dentro daquela linha.
    class ItemViewHolder(val binding: ItemRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    // Chamado pelo RecyclerView quando ele precisa criar uma nova linha (ViewHolder).
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // "Infla" (cria) o layout da nossa linha (item_row_layout.xml) usando ViewBinding.
        val binding = ItemRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    // Retorna o número total de itens na nossa lista de dados.
    override fun getItemCount(): Int {
        return items.size
    }

    // Chamado pelo RecyclerView para conectar os dados de um item específico a uma linha (ViewHolder).
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // Pega o item da nossa lista de dados na posição atual.
        val currentItem = items[position]

        // Usa o 'holder' para acessar os TextViews daquela linha e preenchê-los com os dados.
        holder.binding.itemNameTextView.text = currentItem.name
        holder.binding.itemNumberTextView.text = "Número: ${currentItem.itemNumber}"
        holder.binding.itemExpiryTextView.text = "Validade: ${currentItem.expiryDate}"
        holder.binding.itemCodeTextView.text = "Cód: ${currentItem.productCode}"
    }

    // Uma função para atualizar a lista de itens no adapter quando novos dados chegam do banco.
    fun updateItems(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged() // Notifica o RecyclerView que os dados mudaram e ele precisa redesenhar a lista.
    }
}