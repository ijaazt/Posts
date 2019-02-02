package sql

import org.apache.tomcat.jdbc.pool.DataSource
import java.sql.Connection

interface SQLManager<T> {
    fun createConnection(): Connection {
        val dataSource = DataSource()

        dataSource.driverClassName = "com.mysql.jdbc.Driver"
        dataSource.url = "jdbc:mysql://localhost:3306/mitello"
        dataSource.username = "root"
        dataSource.password = "En7j6pur8v"
        return dataSource.connection
    }

    fun getRows(): Array<T>
    fun getRow(id: Int): T
    fun createTable()
    fun deleteRow(id: Int)
    fun createRow(value: T)
    fun dropTable()
    fun editRow(id: Int, value: T)
    fun closeConnection()

}