<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*,GymBuddy.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Membership</title>
<link href = "GymWeb.css" rel = "stylesheet" type = "text/css">
</head>

<body class = "member-background2">
<div class = "top-navigation2">
  <a href = "index.html">Home</a>
  <a href = "MemberProfile.html">Profile</a>
  <a href = "CPassword.html">Change Password</a>
  <a href = "Trainer.html">Find Trainers</a>
  <a href = "Class.html">Enroll Class</a>
  <a class = "active" href = "ManageMembership.html">Manage Membership</a>
</div>

<div class = "member-background">
<br><br><br><br><br><br><br><br><br><br><br><br>
</div>
<%accountMgr mgr2=LoginServlet.Act;%>
<form action = "MemebershipServlet" method = "post">
<div class = "Search-box">
   <label class = "Newuser-format" for="months"><b>Enter the number of months you want to add/extend</b></label>
   <input class = "Newuser-format" type="text" placeholder="Enter the number of months" name="months" id="months" required>
   <button class = "Login-button" type = "submit" ><b>Subscribe/Extend</b></button>; 
   <br>
   
   <table id="custom">
  <th> Membership Status</th>
  
	  <tr>Feature Unavailable </tr>
  
  
   
   </table>
   <button class = "Newuser-button" name = "button2" onclick="<%mgr2.cancelShip(mgr2.temp);%>;location.href='CancelConfirm.html';"><b>Cancel Membership</b></button>;
</div>
</form>

<div id="id01" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form class="modal-content" action="/action_page.php">
    <div class="container">
      <h1>Current MemberShip Status</h1>   
    </div>
  </form>
</div>


</body>
</html>