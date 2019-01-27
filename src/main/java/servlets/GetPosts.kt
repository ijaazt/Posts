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


@WebServlet(name = "GetPosts", value = ["/api/getPosts"])
class GetPosts: HttpServlet() {
    fun process(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val file = File(servletContext.getRealPath("/WEB-INF") + File.separator + "posts.json")
        val posts = PostManager()
        resp!!.setContentType("application/json")
        resp.setCharacterEncoding("UTF-8")
        resp.writer.write(posts.getAllPosts(file).toString())
    }
    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        process(req, resp)
    }

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        process(req, resp)
    }
}