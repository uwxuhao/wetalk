var other_user_name;



$(document).ready(function () {
    initialize();
    $("#button_send").click(send_message)
    $("#text_input").bind("keypress", {}, enter_press);
    get_message();
});


function initialize() {
    if (user_name == "hao") {
        other_user_name = "gpc";
    }
    else {
        other_user_name = "hao";
    }
}

function send_message() {
    var chat_content = $("#text_input").val();
    var message = {
        from: user_name,
        to: other_user_name,
        content: chat_content
    }
    $.ajax({
        url: '/chat',
        type: 'post',
        data: {
            post_type: "send_message",
            from: message.from,
            to: message.to,
            content: message.content
        }
    });
    $("#text_input").val('');
}


function get_message() {
    console.log("get_message");
    var message = {
        from: user_name,
        to: other_user_name,
    }
    $.ajax({
        url: '/chat',
        type: 'post',
        data: {
            post_type: "get_message",
            from: message.from,
            to: message.to
        },
        success: function (message) {
            var chat_content = $("#chat_content");
            if (message.length == 0)
                return;
            else
                chat_content.text(message);
        }

    });
    AutoUpdContent();
}

function AutoUpdContent() {
    setTimeout(get_message, 850);
}

function enter_press(e) {
    var code = (e.keyCode ? e.keyCode : e.which);
    if (code == 13) {
        e.preventDefault();
        send_message();
    }
}

