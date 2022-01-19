<%@ page import="spd.trello.domain.Workspace" %><%--
  Created by IntelliJ IDEA.
  User: Синхрофазотронируєми
  Date: 19.01.2022
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Read Workspace</title>
</head>
<body>
<%
    Workspace workspace=(Workspace) request.getAttribute("workspace");
%>
<p>Id: <%=workspace.getId()%></p>
<p>Name: <%=workspace.getName()%></p>
<p>Description: <%=workspace.getDescription()%></p>
<p>Create by: <%=workspace.getCreatedBy()%></p>
<p>Create at: <%=workspace.getCreatedDate()%></p>
<p>Update by: <%=workspace.getUpdatedBy()%></p>
<p>Update at: <%=workspace.getCreatedDate()%></p>
<br>
<a href="/">Return to list</a>
</body>
</html>
