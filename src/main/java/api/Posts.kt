package servlets

import com.google.gson.Gson
import model.Post
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/api/posts")
class Posts: HttpServlet(){
    private val postsManager = PostsManager()
    override fun init() {
        postsManager.createTable()
    }
    override fun doDelete(req: HttpServletRequest?, resp: HttpServletResponse?) {
        postsManager.deleteRow(req!!.getParameter("id").toInt())
    }

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        resp!!.apply {
            setContentType("application/json")
            setCharacterEncoding("UTF-8")
            resp.outputStream.print(Gson().toJson(postsManager.getRows()))
        }
    }

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        req!!.apply{
            postsManager.createRow(Post(getParameter("username"), getParameter("content"), -1))
        }
    }

    override fun doPut(req: HttpServletRequest?, resp: HttpServletResponse?) {
        req!!.apply {
            postsManager.editRow(getParameter("id").toInt(), Post(getParameter("username"), getParameter("content"), getParameter("id").toInt()))
        }
    }

    override fun destroy() {
        postsManager.apply {
            dropTable()
            closeConnection()
        }
    }
}