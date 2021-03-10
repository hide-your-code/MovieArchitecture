package minhdtm.example.movieapparchitecture.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import minhdtm.example.model.Genre
import minhdtm.example.movieapparchitecture.databinding.ItemMovieGenreMediumBinding
import minhdtm.example.movieapparchitecture.databinding.ItemMovieGenreSmallBinding
import minhdtm.example.movieapparchitecture.extension.executeAfter

enum class GenreSize {
    MEDIUM,
    SMALL
}

class GenresAdapter(
    private val itemSize: GenreSize
) : ListAdapter<Genre, ViewHolder>(GenreDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = when (itemSize) {
        GenreSize.MEDIUM -> {
            val binding = ItemMovieGenreMediumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MovieGenreMediumViewHolder(binding)
        }
        else -> {
            val binding = ItemMovieGenreSmallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MovieGenreSmallViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (itemSize) {
            GenreSize.MEDIUM -> {
                (holder as MovieGenreMediumViewHolder).bind(getItem(position))
            }
            else -> {
                (holder as MovieGenreSmallViewHolder).bind(getItem(position))
            }
        }

    }
}

private class MovieGenreMediumViewHolder(
    private val binding: ItemMovieGenreMediumBinding
) : ViewHolder(binding.root) {

    fun bind(item: Genre) {
        binding.executeAfter {
            genre = item
        }
    }
}

private class MovieGenreSmallViewHolder(
    private val binding: ItemMovieGenreSmallBinding
) : ViewHolder(binding.root) {

    fun bind(item: Genre) {
        binding.executeAfter {
            genre = item
        }
    }
}

private object GenreDiff : DiffUtil.ItemCallback<Genre>() {

    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean = true
}
