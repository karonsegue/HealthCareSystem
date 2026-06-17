<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entity.HealthData" %>

<%
HealthData healthData =
(HealthData)request.getAttribute("healthData");

String errorMessage =
(String)request.getAttribute("errorMessage");

int sleepHour =
healthData.getSleepTime() / 60;

int sleepMinute =
healthData.getSleepTime() % 60;
%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>記録編集</title>

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

<h1>記録編集</h1>

<%
if(errorMessage != null){
%>

<div class="error">
<%= errorMessage %>
</div>

<%
}
%>

<form action="EditRecordServlet" method="post">

<input type="hidden"
    name="recordId"
    value="<%= healthData.getRecordId() %>">

<div class="form-group">

<label>体温</label>

<input type="number"
    name="bodyTemperature"
    step="0.1"
    value="<%= healthData.getBodyTemperature() %>">

</div>

<div class="form-group">

<label>睡眠時間</label>

<select name="sleepHour">

<%
for(int i=0;i<=24;i++){
%>

<option value="<%= i %>"
<%= (i == sleepHour) ? "selected" : "" %>>
<%= i %>
</option>

<%
}
%>

</select>

時間

<select name="sleepMinute">

<option value="0"
<%= (sleepMinute == 0) ? "selected" : "" %>>
00
</option>

<option value="15"
<%= (sleepMinute == 15) ? "selected" : "" %>>
15
</option>

<option value="30"
<%= (sleepMinute == 30) ? "selected" : "" %>>
30
</option>

<option value="45"
<%= (sleepMinute == 45) ? "selected" : "" %>>
45
</option>

</select>

分

</div>

<div class="button-area">
<input type="submit" value="更新">
</div>

</form>

</div>

</body>
</html>
