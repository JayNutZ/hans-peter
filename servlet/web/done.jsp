<%--
  Created by IntelliJ IDEA.
  User: timhuhndorf
  Date: 21.03.19
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <style>
        html, body {
            height: 100%;
            width: 100%;
            margin: 0;
            padding: 0;
        }

        .ico {
            display: flex;

            flex-direction: row;

            justify-content: center;
            align-items: center;

            height: 100%;
            width: 100%;

            background: #025196;
        }

        img {
            height: 24px;
            width: 24px;
        }

        span {
            padding-left: 12px;
            font-size: 18px;
            font-family: sans-serif;
            color: white;
        }
    </style>
</head>
<body>
    <div class="ico">
        <img src="src/img/check.png" />
        <span>Schauen sie in ihr Postfach!</span>
    </div>
</body>
</html>
