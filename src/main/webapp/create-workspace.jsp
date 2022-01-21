<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Workspace</title>
</head>
<body>
    <form action="/workspace/create" method="post">
        <table>
            <tr>
                <td>
                    <label for="name">Name</label>
                </td>
                <td>
                    <input type="text" id="name" name="name">
                </td>
            </tr>

            <tr>
                <td>
                    <label for="description">Description</label>
                </td>
                <td>
                    <input type="text" id="description" name="description">
                </td>
            </tr>

            <tr>
                <td>
                    <label for="visibility"> Visibility</label>
                </td>
                <td>
                    <select name="visibility" id="visibility">
                        <option value="PRIVATE" selected="">Private</option>
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
                    <input type="submit" value="Create">
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
