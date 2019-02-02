package api

import com.google.gson.Gson
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/api/user")
class Account: HttpServlet() {
    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        if (req!!.session.getAttribute("username") == null) {
            req.session.setAttribute("username", req.getParameter("username"))
        }
    }

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        resp!!.writer.println(Gson().toJson(req!!.session.getAttribute("username") as String))
    }
}