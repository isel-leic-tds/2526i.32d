package demos.tds.puzzle.storage

/**
 * Interface for data serialization.
 * @param Data the type of the data to be serialized.
 */
interface Serializer<Data> {
    fun serialize(data: Data): String
    fun deserialize(data: String): Data?
}
