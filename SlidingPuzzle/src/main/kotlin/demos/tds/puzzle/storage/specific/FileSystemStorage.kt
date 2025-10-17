package demos.tds.puzzle.storage.specific

import demos.tds.puzzle.storage.Storage

/**
 * Simple implementation of [demos.tds.puzzle.storage.Storage] that stores data in the file system.
 * @param T the type of the data stored.
 */
class FileSystemStorage<T> : Storage<String, T> {

    override fun create(key: String, data: T) {
        TODO("Not yet implemented")
    }

    override fun read(key: String): T? {
        TODO("Not yet implemented")
    }

    override fun update(key: String, data: T) {
        TODO("Not yet implemented")
    }

    override fun delete(key: String) {
        TODO("Not yet implemented")
    }
}