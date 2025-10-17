package demos.tds.puzzle.storage

/**
 * Interface that characterizes storage operations.
 * @param Key the type of the key used to identify entries.
 * @param Value the type of the data stored in the storage.
 *
 * DESIGN NOTE:
 * These operations involve I/O and therefore should be implemented asynchronously. In Kotlin, this is achieved by
 * using coroutines and suspend functions. To be studied thoroughly in the Concurrent Programming course.
 */
interface Storage<Key, Value> {

    /**
     * Creates a new entry in the storage.
     */
    fun create(key: Key, data: Value)

    /**
     * Reads an entry from the storage.
     */
    fun read(key: Key): Value?

    /**
     * Updates an entry in the storage.
     */
    fun update(key: Key, data: Value)

    /**
     * Deletes an entry from the storage.
     */
    fun delete(key: Key)
}

