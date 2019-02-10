package api
import com.google.gson.Gson
import com.sun.net.httpserver.Authenticator
import model.*
import org.apache.tomcat.jdbc.pool.ConnectionPool
import sql.PostsManager
import javax.servlet.ServletConfig
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@WebServlet("/api/posts")
class Posts: HttpServlet(){
    private fun checkAccount(session: HttpSession, operation: String): Success {
        return Success(session.getAttribute("username") != null, operation)
    }
    private lateinit var postsManager: PostsManager

    override fun init(config: ServletConfig?) {
        super.init(config)
        postsManager = PostsManager((config!!.servletContext.getAttribute("connectionPool") as ConnectionPool).connection);
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
            val gson = Gson()
            if(req!!.getParameter("user") != null) {
                writer.println(gson.toJson(postsManager.getRowsOfUser(req.getParameter("user"))))
            } else {
                writer.println(gson.toJson(postsManager.getRows()))
            }
        }
    }

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        req!!.apply {
            checkAccount(session, "POST").apply {
                if(successful) {
                    postsManager.createRow(Post((session.getAttribute("username") as User).username, getParameter("content"), -1))
                }
                resp!!.writer.print(Gson().toJson(this))
            }
            resp!!.writer.print("NOPE")
        }
    }

    override fun doPut(req: HttpServletRequest?, resp: HttpServletResponse?) {
        req!!.apply {
            checkAccount(session, "POST").apply {
                if(successful) {
                    val post = Gson().fromJson<Post>(reader, Post::class.java)
                    postsManager.editRow(post.id, post)
                }
            }
        }
    }

    override fun destroy() {
        postsManager.apply {
            closeConnection()
        }
    }
}