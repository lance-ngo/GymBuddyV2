<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*, GymBuddy.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href = "GymWeb.css" rel = "stylesheet" type = "text/css">
</head>

<body class = "home-background">
<div class = "top-navigation">
  <a href = "index.html">Home</a>
  <a class = "active" href = "LogIn.html">LogIn</a>
  <a href = "Membership.html">Membership</a>
  <a href = "NMTrainer.html">Trainers</a>
  <a href = "NMClass.html">Class</a>
  <a href = "Contact.html">Contact</a>
</div>

<br><br><br><br>
<%ArrayList<String> feedback=GetFeedbackServlet.mgr.fbResult; %>
<table id = "custom">
 <tr>
    <th>Feedback</th>
   
 </tr>
 <% for(int i=0;i<feedback.size();i++) { %>
 <tbody>
	 <tr>
	 	<td> <%= feedback.get(i) %></td>
	 	
	 	
	 	
	 </tr>
 </tbody>
<% }%>
 </table>


</body>
</html>