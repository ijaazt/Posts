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
  </head>
  <body>

  <form action="api/createPost" id="content-form">
    <input type="text" placeholder="username" name="username"/>
    <label for="form-textarea">Content</label>
    <textarea name="content" id="form-textarea"></textarea>
    <input type="submit" value="Submit">
  </form>

  <div class="posts">

  </div>
  <script   src="https://code.jquery.com/jquery-3.3.1.js"   integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="   crossorigin="anonymous"></script>
  <script>
    function addPost(response) {
      var html = "";
      for(var i = response.length; i--;) {
        html += "<article class=post><h1>" + response[i]["username"] + "</h1>" + "<p>" + response[i]["content"] + "</p></article>";
      }
      $(".posts").html(html);
    }
      $(document).on("submit", "#content-form", function(event) {
        var $form = $(this);
        $.post($form.attr("action"), $form.serialize(), function(response) {
          addPost(response)
        });
        event.preventDefault(); // Important! Prevents submitting the form.
      });
      $.getJSON("api/getPosts", function (response) {
          addPost(response)
      })
  // </script>

  </body>
</html>
