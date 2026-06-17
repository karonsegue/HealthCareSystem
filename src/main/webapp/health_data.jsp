<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>健康記録登録</title>

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

<script>

function checkWarning(){

    let temp = parseFloat(
            document.getElementsByName(
                "bodyTemperature")[0].value);

    let hour = parseInt(
            document.getElementsByName(
                "sleepHour")[0].value);

    let minute = parseInt(
            document.getElementsByName(
                "sleepMinute")[0].value);

    let sleepTime = hour * 60 + minute;

    let danger = false;
    let warning = false;

 	// 体温判定

    if(temp < 35.0 || temp >= 39.0){

        danger = true;

    }else if(temp < 36.5 || temp >= 37.5){

        warning = true;
    }

    // 睡眠時間判定

    if(sleepTime < 300 || sleepTime >= 600){

        danger = true;

    }else if(sleepTime < 360 || sleepTime >= 480){

        warning = true;
    }

    // DANGER優先

    if(danger){

        return confirm("危険値です。本当に登録しますか？");
    }

    if(warning){

        return confirm("警告値です。登録しますか？");
    }

    return true;
}

</script>

</head>
<body>

<div class="container">

<h1>健康記録登録</h1>

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

<form action="HealthDataServlet" method="post" onsubmit="return checkWarning();">

<div class="form-group">
<label>体温</label>
<input type="number"
       name="bodyTemperature"
       step="0.1">
</div>

<div class="form-group">

<label>睡眠時間</label>

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
