<%--
  Created by IntelliJ IDEA.
  User: haoxu
  Date: 9/2/17
  Time: 2:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contact</title>
    <link rel="stylesheet" type="text/css" href="contact_list_style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript">
        var user_name = '<%=session.getAttribute("user_name")%>';
    </script>
    <script type="text/javascript" src="contact_list_function.js"></script>

</head>

<p id="test">
    My friends
</p>

<ul id="contact_list">

</ul>

<button type="button" id="add_contact">Add Contact</button>

</html>
