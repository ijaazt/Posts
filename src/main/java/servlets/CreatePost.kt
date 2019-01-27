package servlets

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
import com.sun.tools.javac.code.Kinds.VAL
import file_io.asJson
import sun.rmi.server.Dispatcher
import java.util.*
import file_io.Data
import file_io.asData


@WebServlet(name = "CreatePost", value = ["/api/createPost"])
class CreatePost: HttpServlet() {
    fun process(req: HttpServletRequest?, resp: HttpServletResponse?) {
        resp!!.setContentType("application/json")
        resp.setCharacterEncoding("UTF-8")
        val file = servletContext.getRealPath("/WEB-INF") + File.separator + "posts.json"
       val writer = Json.createWriter(FileWriter(file))
        val reader = Json.createReader(FileReader(file))
        var data = reader.readObject().asData
        reader.close()

        if(req!!.getParameter("username") == "" ||  req.getParameter("content") == "") {
            data = Data(false, data.posts)
        } else {
            data.posts.add(Post(req!!.getParameter("username"), req.getParameter("content")))
            writer.writeObject(data.asJson)
            writer.close()
        }
        resp.writer.println(data.asJson.toString())
    }
    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        process(req, resp)
    }

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        process(req, resp)
    }
}