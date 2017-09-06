$(document).ready(function () {
    get_contact_list();
    get_unread_message();
    $("#add_contact").click(add);
});

var read = 0;

function get_contact_list() {
    console.log(user_name);
    var contacts = [];
    $.ajax({
            url: '/contact',
            type: 'post',
            data: {
                type: "get contact list",
                username: user_name
            },
            success: function (contactJson) {
                $("#contact_list").empty();
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

function add() {
    var contact_username = prompt("Please enter the username");
    if(contact_username!=null){
        $.ajax({
            url: '/contact',
            type: 'post',
            data: {
                username: user_name,
                contact: contact_username,
                type: "add contact"
            },
            success: function (message) {
                alert(message);
                if(message === "Success"){
                    get_contact_list();
                }
            }

        });
    }
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
            var unread_pair = {};
            $.each(unreadJson, function (key, value) {
                console.log(key + ": " + value);
                unread_pair[key] = value;
            })

            $(".contact_user_name").each(
                function () {
                    var name = $(this).find(".name").text();
                    if (name in unread_pair) {
                        var total_count = unread_pair[name];
                        if ($(this).css("font-weight") === "bold") {
                            $(this).find(".count").text("");
                            read = total_count;
                            return;
                        }
                        if (total_count > read) {
                            var count = parseInt(total_count) - read;
                        }
                        if (count > 0) {
                            $(this).find(".count").text("  " + count.toString());
                        }
                    }

                }
            )
        }
    });
    setTimeout(get_unread_message, 1500);
}

