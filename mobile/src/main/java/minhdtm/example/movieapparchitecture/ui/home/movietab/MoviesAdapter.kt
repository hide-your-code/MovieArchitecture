package minhdtm.example.movieapparchitecture.ui.home.movietab

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import minhdtm.example.model.MovieTrendingItem
import minhdtm.example.movieapparchitecture.databinding.ItemMovieBinding
import minhdtm.example.movieapparchitecture.extension.executeAfter

class MoviesAdapter : ListAdapter<MovieTrendingItem, MovieItemViewHolder>(MovieDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val inflate = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        inflate.root.layoutParams.width = (parent.measuredWidth * RATIO_ITEM_WIDTH).toInt()
        return MovieItemViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private const val RATIO_ITEM_WIDTH = 0.65
    }
}

object MovieDiff : DiffUtil.ItemCallback<MovieTrendingItem>() {

    override fun areItemsTheSame(oldItem: MovieTrendingItem, newItem: MovieTrendingItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieTrendingItem, newItem: MovieTrendingItem): Boolean =
        oldItem == newItem
}

class MovieItemViewHolder(
    private val binding: ItemMovieBinding
) : ViewHolder(binding.root) {

    internal fun bind(item: MovieTrendingItem) {
        binding.executeAfter {
            movie = item
        }
    }
}
