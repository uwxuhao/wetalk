
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Chat Room</title>
    <link rel="stylesheet" type="text/css" href="chat_room_style.css">
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript">
        var user_name = '<%=session.getAttribute("user_name")%>';
    </script>
    <script type="text/javascript" src="chat_room_function.js"></script>

</head>
<body>
<%--<h1>Welcome <%= session.getAttribute("first_name")%></h1>--%>

<div id="chat_area">
    <textarea rows="30" cols="200" readonly id="chat_content"></textarea>
</div>

<div id="send_form">
    <div class="box">
        <textarea rows="6" cols="190" id="text_input"></textarea>
    </div>
    <div class="box">
        <button class="btn btn-primary"><i class="fa fa-paper-plane" id="button_send">Send</i></button>
    </div>

</div>

</body>
</html>
