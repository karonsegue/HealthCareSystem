<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>

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

.login-link{
    text-align:center;
    margin-top:20px;
}
</style>

</head>
<body>

<div class="container">

<h1>新規登録</h1>

<% String errorMessage =
        (String)request.getAttribute("errorMessage"); %>

<% if(errorMessage != null){ %>
    <div class="error">
        <%= errorMessage %>
    </div>
<% } %>

<form action="RegisterServlet" method="post">

    <div class="form-group">
        <label>ユーザーID</label>
        <input type="text" name="userId">
    </div>

    <div class="form-group">
        <label>パスワード</label>
        <input type="password" name="password">
    </div>

    <div class="form-group">
        <label>患者電話番号</label>
        <input type="text" name="userPhoneNumber">
    </div>

    <div class="form-group">
        <label>家族電話番号</label>
        <input type="text" name="familyPhoneNumber">
    </div>

    <div class="button-area">
        <input type="submit" value="登録">
    </div>

</form>

<div class="login-link">
    既にアカウントをお持ちの方はこちら<br>
    <a href="login.jsp">ログイン</a>
</div>

</div>

</body>
</html>
