$(document).ready(function () {
    get_contact_list();
    get_unread_message();
});

var contacts = [];

function get_contact_list() {
    console.log(user_name);
    $.ajax({
            url: '/contact',
            type: 'post',
            data: {
                username: user_name
            },
            success: function (contactJson) {

                $.each(contactJson, function (index, item) {
                    contacts.push("<li class='contact_user_name'>" +
                        "<span class='name'>" + item + "</span>" +
                        "<span class='count'>" + "</span>" +
                        "</li>");
                });
                $("#contact_list").append(contacts.join(""));
            }
        }
    )
}


function get_unread_message() {
    $.ajax({
        url: '/chat',
        type: 'post',
        data: {
            post_type: "check_unread",
            from: "any",
            to: user_name
        },
        success: function (unreadJson) {
            $.each(unreadJson, function (key, value) {
                console.log(key + ": " + value);
            })
        }
    });
    setTimeout(get_unread_message, 1500);
}
