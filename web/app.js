let user = null;
getAllPosts = () => $.getJSON("api/posts", function(posts) {addingCardsOfPosts(posts)});

getUsersPosts = () => $.ajax({
    dataType: "json",
    url: "api/user",
    success: function (user) {
        $.ajax({
            dataType: 'json',
            url: 'api/posts',
            data: {user: user["username"]},
            success: function (posts) {
                addingCardsOfPosts(posts)
            }
        });
    }
});
$('#exampleModal').on('hide.bs.modal', function (e) {
});

function addingCardsOfPosts(posts) {
    let items = [];
    posts.forEach((post) => {
        items.push(`<div class='card bg-light mb-4'>
            <div class='card-header'>Post</div>
            <div class='card-body'>
                <h5 class=card-title> ${post["username"]}</h5>
                <p class=card-text> ${post["content"]}</p>
                <a href="#" data-toggle="modal" data-target="#exampleModal" class="edit-posts btn btn-primary ${(user != null && user["username"] === post["username"]) ? "edit-post": "collapse" }" >Edit Post</a>
            </div></div>`);
    });
    $("<div>", {
        "class": "card-deck mt-4",
        "id": "all-posts",
        html: items.join("")
    }).replaceAll("#all-posts");
}

$(".edit-post").on("click", function (evt) {

});


$(document).on("click", "#show-my-posts", function () {

    // get user data
    $.ajax({
        dataType: "json",
        url: 'api/user',
        success: function(userData) {
            // get users posts
            $.ajax({
                dataType: "json",
                url: "api/posts",
                data: {user: userData["username"]},
                success: function (posts) {
                    console.log(posts);
                    addingCardsOfPosts(posts)
                    $(this).parent("li").addClass("active");
                    $("#show-all-posts").parent("li").removeClass("active");
                }
            });
        }
    });

});

$(document).on("click", "#show-all-posts", function () {
    getAllPosts();
    $(this).parent("li").addClass("active")
    $("#show-my-posts").parent("li").removeClass("active");
});


$(document).ready(() => {
    $.getJSON("api/user", function(userA) {
        $("#user-name").val(userA["username"]).prop("readonly", true);
        user = userA;
    });
    getAllPosts();
});

$("#content-form").on("submit", function (event) {
    event.preventDefault();
    console.log($(this).serialize());
    $.ajax({
        type: "POST",
        url: $(this).attr("action"),
        data: $(this).serialize(),
        complete: function () {
            getAllPosts();
        }
    });
});
$("#signin-form").on("submit", function (evt) {
    evt.preventDefault();
    console.log($(this).serialize());
    $.ajax({
        type: "POST",
        dataType: 'json',
        url: $(this).attr("action"),
        data: $(this).serialize(),
        success: function (user) {
            $('.alert').text( "Successfully signed in as " + user["username"]).show().fadeOut(2000);
        }
    });
});