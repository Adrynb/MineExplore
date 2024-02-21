import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.mineexplore.Block
import com.example.mineexplore.databinding.FragmentContentBinding
import com.squareup.picasso.Picasso

class BlockAdapter(private val viewModel: BlockViewModel) :
    RecyclerView.Adapter<BlockAdapter.ViewHolder>() {

    var click: ((Int, Block) -> Unit)? = null

    inner class ViewHolder(private val binding: FragmentContentBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val nameTextView: TextView = binding.content
        val imageView: ImageView = binding.preImageView
        val seeButton: Button = binding.seeButton

        init {
            seeButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    viewModel.setSelectedBlock(viewModel.blocksLiveData.value?.get(position))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val block = viewModel.blocksLiveData.value?.get(position)
        block?.let {
            holder.nameTextView.text = it.nombre
            Picasso.get().load(it.imageURL).into(holder.imageView)
        }
    }

    override fun getItemCount(): Int {
        return viewModel.blocksLiveData.value?.size ?: 0
    }

    init {
        viewModel.blocksLiveData.observeForever { notifyDataSetChanged() }
    }
}
