package utils

import java.io.InputStream

class FileUtils {
    fun getFileFromResources(name: String): List<String> {
        // List of lines
        val lineList = mutableListOf<String>()
        var inputStream: InputStream? = null

        try {
            // Read file
            inputStream = javaClass.classLoader.getResourceAsStream(name)
            inputStream.bufferedReader().forEachLine {
                lineList.add(it)
            }
        } catch (e: java.lang.NullPointerException) {
            println("Null pointer exception")
        } catch (e: java.io.FileNotFoundException) {
            println("FileNotFoundException")
        } finally {
            return lineList
        }
    }
}