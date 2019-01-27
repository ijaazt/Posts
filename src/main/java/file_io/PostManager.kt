package file_io

import model.Post
import sun.plugin2.liveconnect.ArgumentHelper.readObject
import java.io.*
import java.util.function.Consumer
import javax.json.*
import javax.json.stream.JsonParser
data class Data(val success: Boolean, val posts: ArrayList<Post>)
val ArrayList<Post>.asJson: JsonArray
get() {
    val arrayBuilder = Json.createArrayBuilder()
    forEach { arrayBuilder.add(it.asJson) }
    return arrayBuilder.build()
}
val Post.asJson: JsonObject
get() {
    return Json.createObjectBuilder().apply {
        add("content", content)
        add("username", username)
    }.build()
}
val Data.asJson: JsonObject
get() {
    return Json.createObjectBuilder().apply {
        add("success", success)
        add("posts", posts.asJson)
    }.build()
}

val JsonArray.asPostArray: ArrayList<Post>
get() {
    val arrayList = ArrayList<Post>()
    forEach { val postObject = it.asJsonObject()
        arrayList.add(Post(postObject.getString("username"), postObject.getString("content")))
    }
    return arrayList
}
val JsonObject.asData: Data
get() {
    return Data(getBoolean("success"), getJsonArray("posts").asPostArray)
}
