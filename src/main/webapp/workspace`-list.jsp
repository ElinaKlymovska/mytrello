<%@ page import="spd.trello.domain.Workspace" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Workspace List</title>
</head>
<body>
<table>
    <th>id</th>
    <th>name</th>
    <th>description</th>
    <th>members</th>
    <th>boards</th>
    <th colspan="3">oparetions</th>
</table>

<%
    for (Workspace workspace : (List<Workspace>) request.getAttribute("workspaces")) {
%>
<tr>
    <td><%=workspace.getId()%>
    </td>
    <td><%=workspace.getName()%>
    </td>
    <td><%=workspace.getDescription()%>
    </td>
    <td><%=workspace.getMembers().toString()%>
    </td>
    <td><%=workspace.getBoards().toString()%>
    </td>
    <td>
        <a href="/workspace/read?id=<%=workspace.getId()%>"> Read </a>
    </td>
    <td>
        <a href="/workspace/update?id=<%=workspace.getId()%>"> Update </a>
    </td>
    <td>
        <a href="/workspace/delete?id=<%=workspace.getId()%>"> Delete </a>
    </td>
</tr>
<%
    }
%>
</body>
</html>
