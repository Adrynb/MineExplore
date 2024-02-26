import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.mineexplore.Block
import com.example.mineexplore.R
import com.example.mineexplore.databinding.FragmentContentBinding
import com.squareup.picasso.Picasso

class BlockAdapter(private val viewModel: BlockViewModel) :
    RecyclerView.Adapter<BlockAdapter.ViewHolder>() {

    var click: ((Int, Block) -> Unit)? = null

    inner class ViewHolder(private val binding: FragmentContentBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameTextView: TextView = binding.content
        val imageView: ImageView = binding.preImageView
        val seeButton: Button = binding.seeButton

        init {
            seeButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val mob = viewModel.blocks.value?.get(position)
                    mob?.let {clickedBlock ->
                        click?.invoke(position, clickedBlock)
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val block = viewModel.blocks.value?.get(position)
        if (block != null) {
            holder.nameTextView.text = block.nombre
            if (!block.imageURL.isNullOrEmpty()) {
                Picasso.get().load(block.imageURL).into(holder.imageView)
            } else {
                holder.imageView.setImageResource(com.google.android.material.R.drawable.mtrl_ic_error)
            }
        } else {

        }
    }

    override fun getItemCount(): Int {
        return viewModel.blocks.value?.size ?: 0
    }
}
