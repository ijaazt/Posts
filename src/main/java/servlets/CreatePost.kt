package servlets

import file_io.PostManager
import model.Post
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.PrintWriter
import javax.json.Json
import com.sun.corba.se.spi.presentation.rmi.StubAdapter.request
import java.util.*


@WebServlet(name = "CreatePost", value = ["/api/createPost"])
class CreatePost: HttpServlet() {
    fun process(req: HttpServletRequest?, resp: HttpServletResponse?) {
        System.out.println("Processing")
        val file = File(servletContext.getRealPath("/WEB-INF") + File.separator + "posts.json")
//        val printer = FileWriter(file, true)
//        val jsonWriter = Json.createWriter(printer)
//        val jsonArray = Json.createArrayBuilder()
//        val jsonObject = Json.createObjectBuilder()
//        jsonObject.add("username", req?.getParameter("username"))
//        jsonObject.add("content", req?.getParameter("content"))
//        jsonArray.add(jsonObject.build())
//        jsonWriter.writeArray(jsonArray.build())
//        jsonWriter.close()
        val posts = PostManager()
                .addPost(file, Post(req!!.getParameter("username"), req.getParameter("content")))
        val ajax = "XMLHttpRequest" == req.getHeader("X-Requested-With")
        resp!!.setContentType("application/json")
        resp.setCharacterEncoding("UTF-8")
        resp.writer.write(posts.getAllPosts(file).toString())
//                .getAllPosts(file).forEach { resp?.writer?.println(it.toString()) }

    }
    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        process(req, resp)
    }

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        process(req, resp)
    }
}