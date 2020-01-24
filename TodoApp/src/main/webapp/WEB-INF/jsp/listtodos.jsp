<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Description</th>
					<th>Target Date</th>
					<th>Status</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<!-- for(Items item: val) -->
				<c:forEach items="${todos}" var="todo">
					<tr>
						<td>${todo.desc}</td>
						<td><fmt:formatDate value="${todo.targetDate }" 
						pattern="dd/MM/yyyy"/> </td>
						<td>${todo.status}</td>
						<td><a role="button" class="btn btn-Success"
							href="/edittodo?todoId=${todo.id}">Edit</a></td>
						<td><a role="button" class="btn btn-warning"
							href="/deletetodo?todoId=${todo.id}">Delete</a></td>
					</tr>

				</c:forEach>

			</tbody>

		</table>



		Click here to <a href="/todo"> Add Todo </a>

<%@ include file="common/footer.jspf" %>