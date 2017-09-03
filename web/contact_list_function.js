$(document).ready(function () {
    get_contact_list();
});


function get_contact_list() {
    console.log(user_name);
    $.ajax({
            url: '/contact',
            type: 'post',
            data: {
                username: user_name
            },
            success: function (contactJson) {
                var contacts = [];
                $.each(contactJson, function (index, item) {
                    contacts.push("<li>" + item + "</li>");
                });
                $("#contact_list").append(contacts.join(""));
            }
        }
    )
}


