var other_user_name;
$(document).ready(function () {
    initialize();
    $("#button_send").click(send_message)
    $("#text_input").bind("keypress", {}, enter_press);
})


function initialize() {
    if (user_name == "hao") {
        other_user_name = "test";
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
            from: message.from,
            to: message.to,
            content: message.content
        }
    });
    $("#text_input").val('');
    var append_text = message.from + ": " + message.content + "\n";
    var chat_content = $("#chat_content");
    chat_content.text(chat_content.text() + append_text);
}

function enter_press(e) {
    var code = (e.keyCode ? e.keyCode : e.which);
    if (code == 13) {
        e.preventDefault();
        send_message();
    }
}

