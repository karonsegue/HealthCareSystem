<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>

<style>
body{
    font-family: sans-serif;
}

.container{
    width: 350px;
    margin: 80px auto;
}

h1{
    text-align:center;
}

.form-group{
    margin-bottom:15px;
}

label{
    display:block;
    margin-bottom:5px;
}

input{
    width:100%;
    padding:8px;
    box-sizing:border-box;
}

.button-area{
    text-align:center;
    margin-top:20px;
}

.error{
    color:red;
    text-align:center;
    margin-bottom:15px;
}

.register-link{
    text-align:center;
    margin-top:20px;
}
</style>

</head>
<body>

<div class="container">

<h1>ログイン</h1>

<% String errorMessage =
        (String)request.getAttribute("errorMessage"); %>

<% if(errorMessage != null){ %>
    <div class="error">
        <%= errorMessage %>
    </div>
<% } %>

<form action="LoginServlet" method="post">

    <div class="form-group">
        <label>ユーザーID</label>
        <input type="text" name="userId">
    </div>

    <div class="form-group">
        <label>パスワード</label>
        <input type="password" name="password">
    </div>

    <div class="button-area">
        <input type="submit" value="ログイン">
    </div>

</form>

<div class="register-link">
    アカウントをお持ちでない方はこちら<br>
    <a href="register.jsp">新規登録</a>
</div>

</div>

</body>
</html>
