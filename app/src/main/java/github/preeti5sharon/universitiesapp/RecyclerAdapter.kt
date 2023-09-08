package github.preeti5sharon.universitiesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import github.preeti5sharon.universitiesapp.databinding.RvItemBinding

class RecyclerAdapter(private val itemOnClickListener: onClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class RVViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class UniversityDiffer : DiffUtil.ItemCallback<UniversityResponseItem>() {
        override fun areItemsTheSame(
            oldItem: UniversityResponseItem,
            newItem: UniversityResponseItem,
        ): Boolean {
            return newItem.name === oldItem.name
        }

        override fun areContentsTheSame(
            oldItem: UniversityResponseItem,
            newItem: UniversityResponseItem,
        ): Boolean {
            return newItem.name == oldItem.name
        }
    }

    val asyncDiffer = AsyncListDiffer(this, UniversityDiffer())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return RVViewHolder(view)
    }

    override fun getItemCount(): Int {
        return asyncDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = RvItemBinding.bind(holder.itemView)
        val item = asyncDiffer.currentList.getOrNull(position)
        binding.state.text = item?.stateProvince.toString()
        binding.uniName.text = item?.name.toString()
        binding.rvItem.setOnClickListener {
            itemOnClickListener.onClickListener(position, item)
        }
    }
}
