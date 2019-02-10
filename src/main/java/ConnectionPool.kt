import java.sql.Connection

//class ConnectionPool(val username: String, val password: String, val initialConnections: Int, val maxConnections: Int, val waitIfBusy: Int){
//    val availableConnection = ArrayList<Connection>()
//    @Synchronized fun getConnection(): Connection {
//        availableConnection.forEach { t: Connection ->
//            if(!t.isClosed) return t
//            else if (availableConnection.size - 1 <= maxConnections) {
//                availableConnection.add(Connection())
//                return availableConnection.get(availableConnection.size - 1)
//            } else {
//
//            }
//        }
//        return availableConnection.get(availableConnection.size - 1)
//
//    }
//    @Synchronized fun freeConnection(connection: Connection) {}
//}