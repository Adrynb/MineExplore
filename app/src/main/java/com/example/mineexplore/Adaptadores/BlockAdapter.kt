import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mineexplore.Block
import com.example.mineexplore.databinding.FragmentContentBinding
import com.squareup.picasso.Picasso

class BlockAdapter(private val viewModel: BlockViewModel) :
    RecyclerView.Adapter<BlockAdapter.ViewHolder>() {

    var click: ((Int, Block) -> Unit)? = null

    inner class ViewHolder(private val binding: FragmentContentBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView : TextView = binding.itemNumber
        val nameTextView: TextView = binding.content
        val imageView: ImageView = binding.preImageView
        val seeButton: Button = binding.seeButton

        init {
            seeButton.setOnClickListener {
                click?.invoke(adapterPosition, viewModel.blocks[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false

            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val block = viewModel.blocks[position]
        holder.nameTextView.text = block.nombre
        Picasso.get().load(block.imageURL).into(holder.imageView)
    }

    override fun getItemCount(): Int = viewModel.blocks.size
}