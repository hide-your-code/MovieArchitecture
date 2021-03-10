package minhdtm.example.model

data class Genres(
    val genres: List<Genre>? = null
) {
    companion object {
        val empty = Genres(emptyList())
    }
}

data class Genre(
    val id: Int? = null,
    val name: String? = null
)
