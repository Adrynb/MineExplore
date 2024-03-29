import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.mineexplore.Items
import com.example.mineexplore.R
import com.example.mineexplore.ViewModels.ItemViewModel
import com.example.mineexplore.databinding.FragmentContentBinding
import com.squareup.picasso.Picasso

class ItemAdapter(private val viewModel: ItemViewModel) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    var click: ((Int, Items) -> Unit)? = null

    inner class ViewHolder(private val binding: FragmentContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val nameTextView: TextView = binding.content
        val imageView: ImageView = binding.preImageView
        val seeButton: Button = binding.seeButton

        init {
            seeButton.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    val mob = viewModel.items.value?.get(position)
                    mob?.let { clickedItem ->
                        click?.invoke(position, clickedItem)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentContentBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() : Int {
        return viewModel.items.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        viewModel.items.value?.get(position)?.let { item ->
            holder.nameTextView.text = item.nombre
            if(!item.imageURL.isNullOrEmpty()){
                Picasso.get().load(item?.imageURL).into(holder.imageView)
            }
            else{
                holder.imageView.setImageResource(com.google.android.material.R.drawable.mtrl_ic_error)
            }
           }
        }
    }

