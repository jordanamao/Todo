<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

Custom Login page

<c:if test="${not empty errorMessage}">
	<div style="color: red;">${errorMessage}</div>
</c:if>

<form name='login' action='/login' method='POST'>
	
	User name: <input type="text" name="username" /> <br>
	
	Password: <input type="password" name="password" /><br>
	
	<input type="submit" name="submit" value="submit"/>


</form>
	
<%@ include file="common/footer.jspf" %>