package servlets

import org.apache.tomcat.jdbc.pool.ConnectionPool
import org.apache.tomcat.jdbc.pool.DataSource
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener

class WebSharedPool: ServletContextListener {
    override fun contextDestroyed(sce: ServletContextEvent?) {
        (sce!!.servletContext.getAttribute("connectionPool") as ConnectionPool).purge()
    }


    override fun contextInitialized(sce: ServletContextEvent?) {
        DataSource().apply {
            driverClassName = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://localhost:3306/root"
            username = "root"
            password = "En7j6pur8v"
            sce!!.servletContext.setAttribute("connectionPool", pool)
        }
    }
}