package sql

import org.apache.tomcat.jdbc.pool.DataSource
import java.sql.Connection

interface SQLManager<T> {

    fun getRows(): Array<T>
    fun createTable()
    fun deleteRow(id: Int)
    fun createRow(value: T)
    fun dropTable()
    fun editRow(id: Int, value: T)
    fun closeConnection()

}