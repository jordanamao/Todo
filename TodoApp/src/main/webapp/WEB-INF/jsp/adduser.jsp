<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

Add User<br>

<form:form method="post" modelAttribute="user">
	<form:hidden path="id"/>
	<form:hidden path="roles"/>
	<fieldset class="form-group">
		<form:label path="fullName">Full Name</form:label>
		<form:input type="text" path="fullName" required="required"/> <br>
		<form:errors path="fullName" 
		cssClass="text-warning"/>
	</fieldset>
	<fieldset class="form-group">
		<form:label path="userName">User Name</form:label>
		<form:input type="text" path="userName" required="required"/> <br>
		<form:errors path="userName" 
		cssClass="text-warning"/>
	</fieldset>
	<fieldset class="form-group">
		<form:label path="password">Password</form:label>
		<form:input type="text" path="password" required="required"/> <br>
		<form:errors path="password" 
		cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="roles">Roles</form:label>
		<form:input type="text" path="roles" required="required"/> <br>
		<form:errors path="roles" 
		cssClass="text-warning"/>
	</fieldset>
	<input type="submit"/>
</form:form> 


<%@ include file="common/footer.jspf" %>

