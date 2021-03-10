package minhdtm.example.movieapparchitecture.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import minhdtm.example.model.Cast
import minhdtm.example.movieapparchitecture.databinding.ItemCastBinding
import minhdtm.example.movieapparchitecture.extension.dpToPx
import minhdtm.example.movieapparchitecture.extension.executeAfter

class CastAdapter : ListAdapter<Cast, CastViewHolder>(CastDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val isLast = position == itemCount - 1
        holder.bind(getItem(position), isLast)
    }
}

class CastViewHolder(
    private val binding: ItemCastBinding
) : RecyclerView.ViewHolder(binding.root) {

    internal fun bind(item: Cast, isLast: Boolean) {
        binding.executeAfter {
            cast = item
        }

        val layoutParam = binding.root.layoutParams as RecyclerView.LayoutParams
        layoutParam.marginEnd = binding.root.dpToPx(
            if (isLast) {
                NO_MARGIN
            } else {
                MARGIN_END
            }
        ).toInt()
    }

    companion object {
        private const val MARGIN_END = 28f
        private const val NO_MARGIN = 0f
    }
}

private object CastDiff : DiffUtil.ItemCallback<Cast>() {

    override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean = true
}
