<%@ page import="spd.trello.domain.Workspace" %><%--
  Created by IntelliJ IDEA.
  User: Синхрофазотронируєми
  Date: 19.01.2022
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<form action="/workspace/update" method="post">
    <%
        Workspace workspace=(Workspace) request.getAttribute("workspace");
    %>

    <table>
        <tr>
            <td>
                <label for="id">Id</label>
            </td>
            <td>
                <input type="text" id="id" name="id" value="<%=workspace.getId()%>" disabled>
            </td>
        </tr>
        <tr>
            <td>
                <label for="name">Name</label>
            </td>
            <td>
                <input type="text" id="name" name="name" value="<%=workspace.getName()%>">
            </td>
        </tr>

        <tr>
            <td>
                <label for="description">Description</label>
            </td>
            <td>
                <input type="text" id="description" name="description" value="<%=workspace.getDescription()%>">
            </td>
        </tr>

        <tr>
            <td>
                <label for="visibility"> Visibility</label>
            </td>
            <td>
                <select name="visibility" id="visibility">
                    <option value="PRIVATE">Private</option>
                    <option value="PUBLIC">Public</option>
                </select>
            </td>
        </tr>

        <%--            <tr>
                        <td>
                            <label for="members">Members</label>
                        </td>
                        <td>
                            <input type="text" id="members" name="members">
                        </td>
                    </tr>--%>


        <%--            <tr>
                        <td>
                            <label for="boards">Boards</label>
                        </td>
                        <td>
                            <input type="text" id="boards" name="boards">
                        </td>
                    </tr>--%>


        <tr>
            <td>
                <input type="submit" value="Update">
            </td>
            <td>
                <input type="reset" value="Clear">
            </td>
        </tr>
    </table>

</form>
<br>
<a href="/">Return to list</a>

</body>
</html>
