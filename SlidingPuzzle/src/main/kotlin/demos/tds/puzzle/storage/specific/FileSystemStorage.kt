package demos.tds.puzzle.storage.specific

import demos.tds.puzzle.storage.Serializer
import demos.tds.puzzle.storage.Storage
import okio.FileSystem
import okio.Path.Companion.toPath

/**
 * Simple implementation of [demos.tds.puzzle.storage.Storage] that stores data in the file system.
 * @param T the type of the data stored.
 */
class FileSystemStorage<T>(private val serializer: Serializer<T>) : Storage<String, T> {

    private fun upSert(key: String, data: T) {
        val file = key.toPath()
        FileSystem.SYSTEM.write(file) {
            writeUtf8(string = serializer.serialize(data))
        }
    }

    override fun create(key: String, data: T) {
        upSert(key, data)
    }

    override fun read(key: String): T? {
        val file = key.toPath()
        val fs = FileSystem.SYSTEM
        return if (fs.exists(file)) {
            fs.read(file) {
                serializer.deserialize(readUtf8())
            }
        } else {
            null
        }
    }

    override fun update(key: String, data: T) {
        upSert(key, data)
    }

    override fun delete(key: String) {
        TODO("Not yet implemented")
    }
}