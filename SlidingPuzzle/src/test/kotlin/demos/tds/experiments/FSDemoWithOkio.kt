package demos.tds.experiments

import okio.FileSystem
import okio.Path.Companion.toPath
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Demo code for basic use of the Okio library.
 * @see <a href="https://square.github.io/okio/">Okio</a>
 *
 * IMPORTANT NOTE: These are not actual tests because they do not test any functionality implemented by us.
 * It's not work job to test Okio. Regardless, I'm leaving them here to illustrate Okio's use.
 *
 * TODO: Discuss rules about creating tests that change the system state.
 */
class FSDemoWithOkio {

    val testFile = "Test.txt".toPath()

    @Test
    fun `write to a file succeeds`() {
        FileSystem.SYSTEM.write(testFile) {
            writeUtf8("Hello World!")
        }
    }

    @Test
    fun `read from a file succeeds`() {
        val expected = "It's alive!!!"
        FileSystem.SYSTEM.write(testFile) {
            writeUtf8(string = expected)
        }

        val actual = FileSystem.SYSTEM.read(testFile) {
            readUtf8()
        }

        assertEquals(expected, actual)
    }

    @AfterTest
    fun cleanup() {
        FileSystem.SYSTEM.delete(testFile)
    }
}