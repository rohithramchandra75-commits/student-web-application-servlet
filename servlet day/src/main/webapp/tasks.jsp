<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.TaskDAO" %>
<%@ page import="model.Task" %>
<%@ page import="model.User" %>

<%
    User user = (User) session.getAttribute("user");

    if(user == null){
        response.sendRedirect("login.html");
        return;
    }

    TaskDAO dao = new TaskDAO();
    ArrayList<Task> tasks = dao.getTasks(user.getId());
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Tasks</title>
<link rel="stylesheet" href="style.css">
</head>

<body>

<header>
    <h1>Student Productivity Portal</h1>
</header>

<nav>
    <a href="index.html">Home</a>
    <a href="tasks.jsp">Tasks</a>
    <a href="logout">Logout</a>
</nav>

<h2>Welcome <%= user.getName() %></h2>

<form action="addTask" method="post">

    <input type="text"
           name="task"
           placeholder="Enter Task"
           required>

    <button type="submit">
        Add Task
    </button>

</form>

<br>

<table border="1">

<tr>
<th>ID</th>
<th>Task</th>
<th>Action</th>
</tr>

<%
for(Task t : tasks){
%>

<tr>

<td><%= t.getId() %></td>

<td><%= t.getTaskName() %></td>

<td>

<a href="deleteTask?id=<%= t.getId() %>">

Delete

</a>

</td>

</tr>

<%
}
%>

</table>

</body>
</html>