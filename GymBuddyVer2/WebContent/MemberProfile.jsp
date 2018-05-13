<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*,GymBuddy.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Member Profile</title>
<link href = "GymWeb.css" rel = "stylesheet" type = "text/css">
</head>
<body class = "member-background2">
<div class = "top-navigation2">
  <a href = "index.html">Home</a>
  <a class = "active" href = "MemberProfile.html">Profile</a>
   <a href = "Contact.html">Give Feedback</a>
  <a href = "Trainer.html">Find Trainers</a>
  <a href = "Class.html">Enroll Class</a>
  <a href = "ManageMembership.html">Manage Membership</a>
</div>

<div class = "member-background">
<br><br><br><br><br><br><br><br><br><br><br><br>
</div>

<h1 style = "color:White">Profile</h1>
<% User current=LoginServlet.Act.temp; %>
<table id = "custom">
 <tr>
    <th>Username</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Balance Due</th>
    <th>Date Expire</th>
 </tr>
 

   
 <tbody>
	 <tr>
	 	<td> <%= current.getUname() %></td>
	 	<td> <%= current.getFirst() %> </td>
	 	<td> <%= current.getLast() %> </td>
	 	<td> <%= current.getBalance()%> </td>
	 	<td> <%= current.getExpire() %> </td>
	 	<td><button onClick="location.href='ManageMembership.html'">Manage MemberShip</button>  </td>
	 	</tr>
 </tbody>

 </table>


</body>
</html>