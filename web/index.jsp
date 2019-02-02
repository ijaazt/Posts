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
                <a class="nav-link" href="#">All Posts<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">My Posts</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Add Post</a>
            </li>
        </ul>
    </div>
    <form class="form-inline">
        <div class="input-group">
            <input type="text" class="form-control mr-sm-2" placeholder="Username" aria-label="Username"/>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign In</button>
        </div>
    </form>
</nav>
<div class="container">


    <div class="dropdown">
        <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown"
           aria-haspopup="true" aria-expanded="true">
            Dropdown link
        </a>
        <form action="api/posts" id="content-form" class="dropdown-menu p-4" aria-labelledby="dropdownMenuLink">
            <div class="form-group">
                <label for="save">Username</label>
                <input type="text" placeholder="username" value="${username}" name="username" id="save"
                       class="${readonlyUsername}"/>
            </div>

            <div class="form-group row">
                <label for="form-textarea">Content</label>
                <textarea name="content" id="form-textarea" class="form-control row" rows="3"></textarea>
            </div>

            <button type="submit" class="btn btn-primary row">Submit post</button>
    </form>
    </div>


    <div class="container _card-container">
    </div>
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
<script>
    function getPosts() {
        $.getJSON("api/posts", function (data) {
            var items = [];
            $.each(data, function (index, value) {

                items.push("<div class='card bg-light mb-4'>" + "<div class='card-header'>Post</div>" +
                    "<div class='card-body'>" +

                    "<h5 class=card-title>" + value["username"] + "</h5>" +
                    "<p class=card-text>" + value["content"] + "</p>" +
                    "</div></div>");
            });
            $("<div>", {
                "class": "card-deck _card-container",
                html: items.join(""),
                "id": "case-studies"
            }).replaceAll("._card-container");
        });
    }

    getPosts();

    $("#content-form").on("submit", function (event) {
        event.preventDefault();
        console.log($(this).serialize());
        $.ajax({
            type: "POST",
            url: $(this).attr("action"),
            data: $(this).serialize(),
            complete: function () {
                getPosts();
                $('#save').prop('readonly', true);
            }
        });
    });
    $('.saved').prop('readonly', true);
</script>

</body>
</html>
