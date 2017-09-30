// bind the click and keypress event.
$(document).ready(function () {
    $("#button_login").click(verify_login)
    $("#password_input").bind("keypress", {}, key_press);
    $("#username_input").bind("keypress", {}, key_press);
});

function key_press(e) {
    var code = (e.keyCode ? e.keyCode : e.which);
    // press the enter
    if (code === 13) {
        e.preventDefault();
        verify_login();
    }
}

function verify_login() {
    var username_input = $("#username_input").val();
    var password_input = $("#password_input").val();
    if (username_input.length === 0 || password_input.length === 0) {
        alert("UserName and Password cannot be empty!");
        return;
    }
    $.post("loginServlet",
        {
            username: username_input,
            password: password_input
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
