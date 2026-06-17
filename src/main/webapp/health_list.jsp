<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.HealthData" %>

<%
ArrayList<HealthData> healthDataList = (ArrayList<HealthData>)request.getAttribute("healthDataList");
String errorMessage = (String)request.getAttribute("errorMessage");
%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>健康記録一覧</title>

<style>

body{
    font-family:sans-serif;
    margin:30px;
}

h1{
    text-align:center;
}

.button-area{
    text-align:center;
    margin-bottom:20px;
}

table{
    width:100%;
    border-collapse:collapse;
}

th,td{
    border:1px solid #000;
    padding:8px;
    text-align:center;
}

.warning{
    background-color:yellow;
}

.danger{
    background-color:#ff9999;
}

.error{
    color:red;
    text-align:center;
    margin-bottom:15px;
}

</style>

</head>
<body>

<h1>健康記録一覧</h1>

<% if(errorMessage != null){ %> <div class="error">
<%= errorMessage %> </div>
<% } %>

<div class="button-area">
    <a href="health_data.jsp">
        <button type="button">新規記録</button>
    </a>
</div>

<table>

<tr>
    <th>記録日</th>
    <th>体温</th>
    <th>睡眠時間</th>
    <th>状態</th>
    <th>編集回数</th>
    <th>編集</th>
</tr>

<%
if(healthDataList != null){

for(HealthData data : healthDataList){

    String cssClass = "";

    if("WARNING".equals(data.getStatus())){
        cssClass = "warning";
    }
    else if("DANGER".equals(data.getStatus())){
        cssClass = "danger";
    }

%>

<tr class="<%= cssClass %>">

<td><%= data.getRecordDate() %></td>

<td><%= data.getBodyTemperature() %> ℃</td>

<td>
<%= data.getSleepTime() / 60 %>時間
<%= data.getSleepTime() % 60 %>分
</td>

<td><%= data.getStatus() %></td>

<td><%= data.getEditCount() %></td>

<td>
    <a href="EditRecordServlet?recordId=<%= data.getRecordId() %>">
        編集
    </a>
</td>

</tr>

<%
}
}
%>

</table>

</body>
</html>
