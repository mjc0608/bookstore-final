<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"--%>
	<%--pageEncoding="ISO-8859-1"%>--%>
<%--<%@ taglib prefix="s" uri="/struts-tags"%>--%>

<%--<jsp:include page="header.jsp" />--%>

<%@ include file="header.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<div class="panel panel-default">

				<div class="panel-heading">
					<label class="col-md-1">ID</label>
					<%--<label class="col-md-1">UID</label>--%>
					<label class="col-md-2">User Name</label>
					<label class="col-md-2">Time</label>
					<label class="col-md-3">Address</label>
					<label class="col-md-1">Book</label>
					<label class="col-md-1">Price/$</label>
					<label class="col-md-1">Status</label>
					<br/>
				</div>
				<s:iterator value="orders" id="order">
					<div class="panel-body">
						<div class="col-md-1"><s:property value="#order.id" /></div>
						<%--<div class="col-md-1"><s:property value="#order.user.id" /></div>--%>
						<div class="col-md-2"><s:property value="#order.user.username" /></div>
						<div class="col-md-2"><s:property value="#order.time.getTime()" /></div>
						<div class="col-md-3"><s:property value="#order.user.address" /></div>
						<div class="col-md-1"><s:property value="#order.bookNumber" /></div>
						<div class="col-md-1"><fmt:formatNumber pattern="#0.00"><s:property value="#order.totalMoney" /></fmt:formatNumber></div>
						<div class="col-md-1">
							<s:if test="#order.getStatus()==0">unfinished</s:if>
							<s:else>finished</s:else>
						</div>
						<div class="col-md-1"><a href="/orderinfo?id=<s:property value="#order.id" />"><input type="button" class="btn btn-primary btn-xs show-detail" value="Detail"></a></div>
						<br/>
					</div>
				</s:iterator>

			</div>
			<div class="panel panel-default text-center">
				<div class="panel-heading">
					<label>Add Order</label>
					<br/>
				</div>
				<div class="panel-body">
					<br/>
					<form action="/addorder" method="POST">
						<div class="col-md-8"><input type="text" name="username" class="form-control" placeholder="User Name"></div>
						<input type="submit" class="btn btn-primary show-detail col-md-4" value="Add">
					</form>
				</div>
				<div class="panel-body">
					<form action="/addorder" method="POST">
						<div class="col-md-8"><input type="text" name="userID" class="form-control" placeholder="User ID"></div>
						<input type="submit" class="btn btn-primary show-detail col-md-4" value="Add">
					</form>
					<br/>
					<br/>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>
<%--<jsp:include page="footer.jsp" />--%>