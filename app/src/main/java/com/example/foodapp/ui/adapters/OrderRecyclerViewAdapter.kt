import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.databinding.ItemRvOrdersBinding
import com.example.foodapp.model.CartItem


class OrderRecyclerViewAdapter(
    private val onAddClick: (CartItem) -> Unit,
    private val onRemoveClick: (CartItem) -> Unit
) : ListAdapter<CartItem, OrderRecyclerViewAdapter.CartViewHolder>(DiffCallback()) {

    class CartViewHolder(val binding: ItemRvOrdersBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemRvOrdersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            mealName.text = item.name
            mealSubTitle.text = item.subtitle
            mealPrice.text = "${'$'}${item.price}"
            tvNumMeal.text = item.quantity.toString()
            // image load with Glide/Picasso if needed
            addBut.setOnClickListener { onAddClick(item) }
            removeBut.setOnClickListener { onRemoveClick(item) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem) = oldItem == newItem
    }
}
