package minhdtm.example.movieapparchitecture.ui.home.movietab

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import minhdtm.example.model.Genre
import minhdtm.example.movieapparchitecture.databinding.ItemMovieGenreBinding
import minhdtm.example.movieapparchitecture.extension.executeAfter

class MovieTabGenreAdapter : ListAdapter<Genre, MovieGenreViewHolder>(GenreDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGenreViewHolder {
        val binding = ItemMovieGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieGenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieGenreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MovieGenreViewHolder(
    private val binding: ItemMovieGenreBinding
) : ViewHolder(binding.root) {

    internal fun bind(item: Genre) {
        binding.executeAfter {
            genre = item
        }
    }
}

private object GenreDiff : DiffUtil.ItemCallback<Genre>() {

    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean = true
}
