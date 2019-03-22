<%--
  Created by IntelliJ IDEA.
  User: timhuhndorf
  Date: 21.03.19
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>JJTL Group - Datenschutz</title>

    <script>
        let features = {
            icon: 'btn_img',
            text: 'btn_txt'
        };

        function edit() {
            getInput().classList.remove('wrong');
        }

        function send() {
            var input = getInput();

            if (input.checkValidity() && input.value !== '') {
                request();
            } else {
                input.classList.add('wrong');
            }
        }

        function request() {
            var mail = getInput().value;
            //ToDo: Send
            console.log('Sending', mail);

        }

        function getButton() {
            return document.getElementById('submit');
        }

        function getButtonFeature(feature) {
            return document.getElementById(feature);
        }

        function getInput() {
            return document.getElementById('mail');
        }
    </script>

    <style>
        html, body {
            display: flex;
            height: 100%;
            width: 100%;

            margin: 0;
            padding: 0;

            font-family: sans-serif;
            color: white;
            background: #025196;
        }

        .cover {
            flex-shrink: 1;
            flex-grow: 1;

            height: 100%;
            width: calc(100% - 720px);

            background: url(src/img/discover.jpg);
            background-position: center;
            background-size: cover;
        }

        .main_wrap {
            display: flex;
            flex-direction: column;
            flex-shrink: 1;
            flex-grow: 1;

            height: 100%;
            width: 100%;
            max-width: 720px;
        }

        header {
            display: flex;
            align-items: center;
            width: calc(100% - 128px);
            height: calc(25% - 160px);
            padding: 64px;
            margin-top: 32px;
        }

        .logo {
            height: 75px;
            width: 75px;
        }

        .title {

            margin-left: 24px;

            font-size: 24px;
        }

        main {
            width: calc(100% - 128px);
            height: calc(75% - 128px);
            padding: 64px;
        }

        .desc {
            font-size: 24px;
            margin-bottom: 25vh;
        }

        .strong {
            font-size: 30px;
        }

        input[type=email] {
            width: calc(100% - 16px);
            padding: 8px;
            border: none;
            border-bottom: 1px solid #fff;
            background: unset;
            font-size: 14px;
            color: white;
            outline: none;
        }

        input.wrong {
            border-bottom: 1px solid red !important;
        }

        button {
            display: flex;
            margin: 32px 0 0 auto;
            color: #025196;
            background: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            outline: none;
            transition: background .2s, border .2s;
        }

        button:hover {
            background: #fff9;
        }

        button:disabled {
            background: #777;
        }

        button img {
            height: 24px;
            width: 24px;

            padding: 6px;
        }

        button span {
            padding: 10px;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="cover"></div>
    <div class="main_wrap">
        <header>
            <img src="src/img/logo.png" class="logo">
            <span class="title">Datenschutz</span>
        </header>
        <main>
            <div class="desc">
                <span class="strong">Du suchst nach deinen Daten?</span>
                <br>
                <span>Wir schicken sie dir!</span>
            </div>


            <form action="javascript:void(0)">
                <input type="email" placeholder="E-Mail" id="mail" onkeydown="edit()" />
                <button onclick="send()" id="submit"><img src="src/img/key.png" id="btn_img"><span id="btn_txt">Anfordern</span></button>
            </form>
        </main>
    </div>
</body>
</html>
