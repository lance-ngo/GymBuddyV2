<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*,GymBuddy.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
<link href = "GymWeb.css" rel = "stylesheet" type = "text/css">
</head>
 <body>
<body class = "member-background2">
<div class = "top-navigation2">
  <a href = "index.html">Home</a>
  <a class = "active" href = "MemberProfile.html">Profile</a>
  <a href = "CPassword.html">Change Password</a>
  <a href = "Trainer.html">Find Trainers</a>
  <a href = "Class.html">Enroll Class</a>
  <a href = "ManageMembership.html">Manage Membership</a>
</div>
<br>
<br>


<table id = "custom">
 <tr>
    <th>Name</th>
    <th>Instructor</th>
    <th>Date</th>
    <th>Time</th>
    <th>Capacity</th>
    <th>Enrolled</th>
 </tr>

 
 <%
 SearchClassServlet.mgr.SearchClassN(request.getParameter("class-name"));
 ArrayList<GymClass> search= SearchClassServlet.mgr.result;%>
 
 <script>
 function clearTable()
 {
	 var myTable = document.getElementById("custom"); 
	 var rowCount = myTable.rows.length; 
	 for (var x=rowCount-1; x>0; x--) { myTable.deleteRow(x);

 }
 
 </script>
 <% for(int i=0;i<search.size();i++) { %>
 <tbody>
	 <tr>
	 	<td> <%= search.get(i).name %></td>
	 	<td> <%= search.get(i).instructor %> </td>
	 	<td> <%= search.get(i).date %> </td>
	 	<td> <%= search.get(i).time %> </td>
	 	<td> <%= search.get(i).capacity %> </td>
	 	<td> <%= search.get(i).enrolled %> </td>
	 	<%if(search.get(i).capacity<search.get(i).enrolled+1)
	 		{%>
	 			<td>Class Full</td>
	 		<%}
	 		else
	 		{%>
	 			<td> <button onClick="<% SearchClassServlet.mgr.updateClassEnrollment(search.get(i).id ,search.get(i).enrolled,i);%>;setValue(<%= search.get(i).id %>);
	 			clearTable();">Sign Up</button></td>
	 		<%}%>
	 	
	 </tr>
 </tbody>
<% }%>
 </table>
 <script>
 function setValue(x)
 {
 	 
	 document.getElementById("data").value=x;
	 
	 document.getElementById("form1").submit();
 }
 </script>

 <div style ="display:none">
 <form id="form1" method="post" action="ConfirmationServlet">
 <input type="hidden" name="data" id="data" value="">
 </form></div>
 
 
</html>