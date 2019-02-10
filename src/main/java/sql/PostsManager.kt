package sql

import model.Post
import java.sql.Connection

class PostsManager(val conn: Connection): SQLManager<Post> {
    override fun closeConnection() {
        conn.close()
    }

    override fun getRows(): Array<Post> {
        conn.createStatement().apply {
            execute("select * from Posts")
            resultSet.apply {
                val posts = ArrayList<Post>()
                while (next()) {
                    posts.add(Post(getString("username"), getString("content"), getInt("id")))
                }
                return posts.toTypedArray()
            }
        }
    }

    fun getRowsOfUser(user: String): Array<Post> {
        conn.prepareStatement("select * from Posts where username=?").apply {
            setObject(1, user)
            execute()
            resultSet.apply {
                val posts = ArrayList<Post>();
                while(next())
                    posts.add(Post(getString("username"), getString("content"), getInt("id")))
                return posts.toTypedArray()
            }
        }
    }

    override fun createTable() {
        conn.createStatement().execute("create table if not exists Posts (id int PRIMARY KEY auto_increment, username varchar(40), content text not null);")
    }

    override fun deleteRow(id: Int) {
        conn.prepareStatement("delete from Posts where id=?").apply {
            setObject(1, id)
            execute()
        }
    }

    override fun createRow(value: Post) {
        conn.prepareStatement("insert into Posts (username, content) value (?, ?)").apply {
            setObject(1, value.username)
            setObject(2, value.content)
            execute()
        }
    }

    override fun dropTable() {
        conn.createStatement().execute("drop table if exists Posts")
    }

    override fun editRow(id: Int, value: Post) {
        conn.prepareStatement("update Posts set content = ?, username = ? where id= ?").apply {
            setObject(1, value.content)
            setObject(2, value.username)
            setObject(3, value.id)
            execute()
        }
    }

}