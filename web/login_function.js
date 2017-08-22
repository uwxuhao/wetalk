$(document).ready(function () {
    $("#button_login").click(verify_login)
    $("#password_input").bind("keypress", {}, key_press);
})

function key_press(e) {
    var code = (e.keyCode ? e.keyCode : e.which);
    if (code == 13) {
        e.preventDefault();
        verify_login();
    }
}

function verify_login() {
    $.post("loginServlet",
        {
            username: $("#username_input").val(),
            password: $("#password_input").val(),
        },
        function (message) {
            console.log(message);
            if (message !== "ok") {
                alert("UserName or Password Wrong!");
                return false;
            }
            else {
                window.location.href = "chat_room.jsp";
            }
        }
    );
}
