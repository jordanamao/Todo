<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<table class="table table-striped">
	<thead>
		<tr>
			<th scope="col">Full Name</th>
			<th scope="col">User Name</th>
			<th scope="col">Password</th>
			<th scope="col">Roles</th>
			<th scope="col"></th>
			<th scope="col"></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var = "user"> 
			<tr scope="row">
				<td>${user.fullName}</td>
				<td>${user.userName }</td>
				<td>********</td>
				<td> <c:forEach items="${user.roles}" var = "role">
						${role}
					</c:forEach>
				</td>
				<td> <a class="btn btn-warning" 
				href="/deleteuser?userId=${user.id}" >Delete</a> </td>
				<td> <a class="btn btn-success" 
				href="/updateuser?userId=${user.id}" >Edit</a> </td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<a class="btn btn-success" href="/adduser">Add User</a>


<%@ include file="common/footer.jspf" %>