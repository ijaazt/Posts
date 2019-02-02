package servlets

import com.google.gson.Gson
import model.Post
import sql.PostsManager
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@WebServlet("/api/posts")
data class Success(var successful: Boolean, var operation: String)
class Posts: HttpServlet(){
    private fun checkAccount(session: HttpSession, operation: String): Success {
        return Success(session.getAttribute("username") != null, operation)
    }
    private val postsManager = PostsManager()
    override fun init() {
        postsManager.createTable()
    }
    override fun doDelete(req: HttpServletRequest?, resp: HttpServletResponse?) {
        checkAccount(req!!.session, "DELETE").apply {
            if(successful) {
                postsManager.deleteRow(req.getParameter("id").toInt())
            }
            resp!!.writer.print(Gson().toJson(this))
        }
    }

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        resp!!.apply {
            contentType = "application/json"
            characterEncoding = "UTF-8"
            resp.writer.println(Gson().toJson(postsManager.getRows()))
        }
    }

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        req!!.apply {
            checkAccount(session, "POST").apply {
                if(successful) {
                    postsManager.createRow(Post(session.getAttribute("username") as String, getParameter("content"), -1))
                }
                resp!!.writer.print(Gson().toJson(this))
            }
        }

    }

    override fun doPut(req: HttpServletRequest?, resp: HttpServletResponse?) {
        req!!.apply {
            checkAccount(session, "POST").apply {
                if(successful) {
                    postsManager.editRow(getParameter("id").toInt(), Post(getParameter("username"), getParameter("content"), getParameter("id").toInt()))
                }
                resp!!.writer.print(Gson().toJson(this))
            }
        }
    }

    override fun destroy() {
        postsManager.apply {
            closeConnection()
        }
    }
}