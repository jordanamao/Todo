<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

	Add todo ${name }
	<form:form method="post" modelAttribute="todo">
		<form:hidden path="id"/>
		<form:hidden path="user"/>
		<fieldset class="form-group">
			<form:label path="desc">Todo Description:</form:label>
			<form:input type="text" path="desc" class="form-control"  required="required"/>
			<form:errors path="desc" class="text-warning"/>
		</fieldset>
		
		<fieldset class="form-group">
			<form:label path="targetDate">Target Date:</form:label>
			<form:input type="text" path="targetDate" class="form-control"  required="required"/>
			<form:errors path="targetDate" class="text-warning"/>
		</fieldset>
		
		
		 
		<button type="submit" class="btn btn-success">Add</button>
	</form:form>




<%@ include file="common/footer.jspf" %>