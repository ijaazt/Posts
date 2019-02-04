<%--
  Created by IntelliJ IDEA.
  User: ijaaztello
  Date: 2019-01-26
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Posts</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="custom.css"/>
    <%--<link rel="stylesheet" href="node_modules/bootstrap/scss/bootstrap-grid.css">--%>
</head>
<body>
<nav class="navbar navbar-light navbar-expand-lg bg-light">
    <span class="navbar-brand h1">Post Application</span>

    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="#" id="show-all-posts">All Posts<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="show-my-posts" href="#">My Posts</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Add Post</a>
            </li>
            <li class="dropdown nav-item">
                <a class="nav-link dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="true">
                    Dropdown link
                </a>
                <form action="api/posts" id="content-form" class="dropdown-menu p-4 form"
                      aria-labelledby="dropdownMenuLink">
                    <div class="form-group row">
                        <label for="form-textarea">Content</label>
                        <textarea name="content" id="form-textarea" class="form-control row" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary row">Submit post</button>
                </form>
            </li>
        </ul>
    </div>
    <form class="form-inline form" action="api/user" id="signin-form">
        <div class="input-group">
            <input type="text" id="user-name" class="form-control mr-sm-2" placeholder="Username" name="username"
                   aria-label="Username"/>
            <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Sign In</button>
        </div>
    </form>
</nav>
<div class="alert alert-success collapse" role="alert">
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Post</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <textarea class="modal-body">
                ...
            </textarea>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
<div id="like_button_container">

</div>
<div class="container">
    <div id="all-posts"></div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
        integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
        integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>
<script src="app.js"></script>
</body>
</html>
