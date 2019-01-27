package servlets

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.json.Json
import java.io.*


@WebServlet(name = "GetPosts", value = ["/api/getPosts"])
class GetPosts: HttpServlet() {
    fun process(req: HttpServletRequest?, resp: HttpServletResponse?) {
        resp
        resp!!.setContentType("application/json")
        resp.setCharacterEncoding("UTF-8")
        val parser = Json.createParser(FileInputStream(servletContext.getRealPath("/WEB-INF") + File.separator + "posts.json"))
        resp.writer.write(parser.`object`.toString())
    }
    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        process(req, resp)
    }

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        process(req, resp)
    }
}