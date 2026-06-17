<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>基準値設定</title>

<style>
body{
    font-family:sans-serif;
}

.container{
    width:400px;
    margin:50px auto;
}

h1{
    text-align:center;
}

.form-group{
    margin-bottom:15px;
}

.error{
    color:red;
    text-align:center;
    margin-bottom:15px;
}

.button-area{
    text-align:center;
    margin-top:20px;
}
</style>

</head>
<body>

<div class="container">

<h1>基準値設定</h1>

<%
String errorMessage =
(String)request.getAttribute("errorMessage");

if(errorMessage != null){
%>

<div class="error">
<%= errorMessage %>
</div>

<%
}
%>

<form action="HealthStandardServlet" method="post">

<div class="form-group">
<label>平均体温</label>
<input type="number"
       name="averageBodyTemperature"
       step="0.1"
       min="35"
       max="42">
</div>

<div class="form-group">
<label>平均睡眠時間</label>

<select name="sleepHour">
<%
for(int i=0;i<=24;i++){
%>
<option value="<%= i %>"><%= i %></option>
<%
}
%>
</select>
時間

<select name="sleepMinute">
<option value="0">00</option>
<option value="15">15</option>
<option value="30">30</option>
<option value="45">45</option>
</select>
分

</div>

<div class="button-area">
<input type="submit" value="登録">
</div>

</form>

</div>

</body>
</html>
