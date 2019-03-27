<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>JJTL Group - Datenschutz</title>

    <link rel="stylesheet" type="text/css" href="src/style.css"
    <script type="text/javascript" src="src/script.js"></script>
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


            <form action="SendData" method="post">
                <input type="email" placeholder="E-Mail" id="mail" onkeydown="edit()" />
                <button onclick="send()" id="submit"><img src="src/img/key.png" id="btn_img"><span id="btn_txt">Anfordern</span></button>
            </form>
        </main>
    </div>
</body>
</html>
