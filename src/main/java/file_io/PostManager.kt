package file_io

import model.Post
import java.io.*
import java.util.function.Consumer
import javax.json.*

class PostManager {

    private fun postToJson(post: Post): JsonObjectBuilder {
        val builder = Json.createObjectBuilder()
         hashMapOf("content" to post.content, "username" to post.username).forEach { t, u -> builder.add(t, u) }
        return builder
    }
    private fun jsonToPost(postJson: JsonObject): Post{
        return Post(postJson.getString("content"), postJson.getString("username"))
    }
    private fun arrayOfPostsToJson(array: Array<Post>): JsonArrayBuilder {
        val jsonArray = Json.createArrayBuilder()
        array.forEach { jsonArray.add(postToJson(it)) }
        return jsonArray
    }
    private fun arrayOfJsonToPost(jsonArray: JsonArray): Array<Post>{
        val arrayOfPosts = mutableListOf<Post>()
        jsonArray.forEach(Consumer {
            arrayOfPosts.add(jsonToPost(it.asJsonObject()))
        })
        return arrayOfPosts.toTypedArray()
    }

    fun getAllPosts(file: File): JsonArray {
        val parser = Json.createParser(FileInputStream(file))
        parser.next()
        val array = parser.array
        parser.close()
        return array;
    }
    fun addPost(file: File, post: Post): PostManager {
        val savedPosts = arrayOfJsonToPost( getAllPosts(file)).toMutableList()
        savedPosts.add(post)
        val writer = Json.createWriter(FileWriter(file, false))
        writer.writeArray(arrayOfPostsToJson(savedPosts.toTypedArray()).build())
        writer.close()
        return this
    }
}