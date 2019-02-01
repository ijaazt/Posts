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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
  </head>
  <body>

  <div class="container pt-5">
    <header class="">
      <h1>My Post Application</h1>
      <p>Feel free to add a post!!</p>
    </header>
    <form action="api/posts" id="content-form">
    <div class="form-group">
      <label for="form-username">Username</label>
      <input type="text" placeholder="username" name="username" id="form-username"/>
    </div>

    <div class="form-group row">
      <label for="form-textarea">Content</label>
      <textarea name="content" id="form-textarea" class="form-control row" rows="3"></textarea>
    </div>

    <button type="submit" class="btn btn-primary row">Submit post</button>
    </form>

    <div class="posts row">

    </div>
  </div>
  <script   src="https://code.jquery.com/jquery-3.3.1.js"   integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="   crossorigin="anonymous"></script>
  <script>
    function getPosts() {
      $.getJSON("api/posts", function(data) {
        var items = [];
        $.each(data, function(index, value) {

          items.push("<div class='card col m-2'><article class='card-body'><h5 class=card-title>" + value["username"] + "</h5>" + "<p class=card-text>" + value["content"] + "</p></article></div>");
        });
        $( ".row", {
          "class": "my-new-list",
          html: items.join( "" )
        }).appendTo(".posts");
      });
    }
    getPosts();

    $("#content-form").on("submit", function (event) {
      event.preventDefault();
      console.log( $( this ).serialize() );
      $.ajax({
        type: "POST",
        url: $(this).attr("action"),
        data:  $(this).serialize(),
        success: function(response) {
          getPosts();
        }
      });
    });
  </script>

  </body>
</html>
