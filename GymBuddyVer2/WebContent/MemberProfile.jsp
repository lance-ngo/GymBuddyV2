<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*,GymBuddy.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body class = "member-background2">
<div class = "top-navigation2">
  <a href = "index.html">Home</a>
  <a class = "active" href = "MemberProfile.html">Profile</a>
  <a href = "CPassword.html">Change Password</a>
  <a href = "Trainer.html">Find Trainers</a>
  <a href = "Class.html">Enroll Class</a>
  <a href = "ManageMembership.html">Manage Membership</a>
</div>
<div class = "member-background">
<br><br><br><br><br><br><br><br><br><br><br><br>
</div>

<h1 style = "color:White">Profile</h1>
<% User current=LoginServlet.Act.temp; %>
<table id = "custom1">
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
	 	</tr>
	 	<tr>
	 	<td>  </td>
	 	<td>  </td>
	 	<td>  </td>
	 	<td><button onClick="update(<%="extend"%>);">Extend MemberShip</button>  </td>
	 	<%if(current.detActive()) 
	 	{%>
	 		<td><button onClick="update(<%="cancel"%>);">Cancel MemberShip</button>  </td>
	 	<%}%>
	 	
	 	
	 	
	 </tr>
 </tbody>

 </table>
 <script>
 function update(x)
 {
 	 
	 document.getElementById("data").value=x;
	 
	 document.getElementById("form1").submit();
 }
 </script>
 <div style ="display:none">
 <form id="form1" method="post" action="MembershipServlet">
 <input type="hidden" name="data" id="data" value="">
 </form></div>

</body>
</html>