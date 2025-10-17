package demos.tds.puzzle.storage

/**
 * Interface for data serialization, that is, converting to a string-based external representation.
 * @param Data the type of the data to be serialized.
 */
interface Serializer<Data> {

    /**
     * Produces the string-based external representation of the given data.
     * @param data the data to be serialized.
     * @return the string-based external representation.
     */
    fun serialize(data: Data): String

    /**
     * Converts the string-based external representation to the data type.
     * @param data the string-based external representation.
     * @return the converted data, or null if the data could not be converted.
     */
    fun deserialize(data: String): Data?
}
