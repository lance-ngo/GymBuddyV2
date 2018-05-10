<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <button onClick="setValue()">Test getFeedback</button>
	 		
	 	
	

 <script>
 function setValue()
 {
 	 

	 document.getElementById("data").value="idk";
	 document.getElementById("form1").submit();
 }
 </script>

 <div style ="display:none">
 <form id="form1" method="post" action="GetFeedbackServlet">
 <input type="hidden" name="data" id="data" value="">
 </form></div>
</body>
</html>