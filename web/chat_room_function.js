var other_user_name;


$(document).ready(function () {
    initialize();
    $("#button_send").click(send_message)
    $("#text_input").bind("keypress", {}, enter_press);
    get_message();

    $(document).on('click', '.contact_user_name', function () {
        $(".contact_user_name").css("font-weight", "normal");
        $(this).css("font-weight", "bolder");
        if (other_user_name !== $(this).find(".name").text()) {
            other_user_name = $(this).find(".name").text();
            switch_user();
        }
    });

    //TODO figure why the .click cannot work

});


function initialize() {
    other_user_name = "";
}

function send_message() {
    var chat_content = $("#text_input").val();
    if (chat_content.length != 0 && other_user_name.length != 0) {
        console.log("send message");
        var message = {
            from: user_name,
            to: other_user_name,
            content: chat_content
        };
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

}


function get_message() {
    if (other_user_name.length != 0) {
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
                console.log("get message: " + message)
                var chat_content = $("#chat_content");
                chat_content.text(message);
            }

        });
    }
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


function switch_user() {
    clean();
}

function clean() {
    $("#text_input").val('');
}

