<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"--%>
	<%--pageEncoding="ISO-8859-1"%>--%>
<%--<%@ taglib prefix="s" uri="/struts-tags"%>--%>

<%--<jsp:include page="header.jsp" />--%>

<%@ include file="header.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<div class="panel panel-default text-center">
				<div class="panel-heading">
					<label class="col-md-1">ID</label>
					<label class="col-md-2">Name</label>
					<label class="col-md-3">Email</label>
					<label class="col-md-1">Admin</label>
					<label class="col-md-3">Address</label>
					<br/>
				</div>
				<s:iterator value="users" id="user">
					<div class="panel-body">
						<div class="col-md-1"><s:property value="#user.id" /></div>
						<div class="col-md-2"><s:property value="#user.username" /></div>
						<div class="col-md-3"><s:property value="#user.email" /></div>
						<div class="col-md-1"><s:property value="#user.admin" /></div>
						<div class="col-md-3"><s:property value="#user.address" /></div>
						<div class="col-md-1"><a href="/userinfo?id=<s:property value="#user.id" />"><input type="button" class="btn btn-primary btn-xs show-detail" value="Modify"></a></div>
						<div class="col-md-1"><a href="/analysis?userID=<s:property value="#user.id" />"><input type="button" class="btn btn-primary btn-xs show-detail" value="Analysis"></a></div>
						<br/>
					</div>
				</s:iterator>
			</div>
			<div class="panel panel-default text-center">
				<div class="panel-heading">
					<label>Add User</label>
					<br/>
				</div>
				<form action="/adduser" method="POST">
					<div class="panel-body">
						<br/>
						<div class="col-md-3"><input type="text" name="user.username" class="form-control" placeholder="User Name"/></div>
						<div class="col-md-4"><input type="text" name="user.email" class="form-control" placeholder="Email"/></div>
						<div class="col-md-4"><input type="text" name="user.password" class="form-control" placeholder="Password"/></div>
						<div class="col-md-1"><s:checkbox name="user.admin" label="admin"/></div>
					</div>
					<div class="panel-body">
						<div class="col-md-11"><input type="text" name="user.address" class="form-control" placeholder="Address" /></div>
						<input type="submit" class="btn btn-primary show-detail" value="Add">
						<br/>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>
<%--<jsp:include page="footer.jsp" />--%>