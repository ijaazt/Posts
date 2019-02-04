package model

import jdk.nashorn.internal.runtime.JSONFunctions

data class Post (val username: String, val content: String, val id: Int)
data class Success(var successful: Boolean, var operation: String)
data class User(val username: String)
