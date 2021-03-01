package minhdtm.example.shared.data.local.entities

@Suppress("UnnecessaryAbstractClass")
abstract class BaseRemoteKey {
    abstract val id: Int
    abstract val prevKey: Int?
    abstract val nextKey: Int?
}
