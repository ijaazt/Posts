package api

import com.google.gson.Gson
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import model.*

@WebServlet("/api/user")
class Account: HttpServlet() {
    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        if (req!!.session.getAttribute("username") == null) {
            req.session.setAttribute("username", User(req.getParameter("username")))
        }
        resp!!.writer.println(Gson().toJson(req.session.getAttribute("username")))
    }

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        if(req!!.session.getAttribute("username") is User)
            resp!!.writer.println(Gson().toJson(req.session.getAttribute("username")))
        else {
            resp!!.setStatus(HttpServletResponse.SC_NOT_FOUND)
        }
    }
}