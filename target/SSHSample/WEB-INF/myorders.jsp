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
					<label class="col-md-2">ID</label>
					<label class="col-md-4">Time</label>
					<label class="col-md-2">Book Number</label>
					<label class="col-md-2">Cost/$</label>
					<br/>
				</div>
				<s:iterator value="orders" id="order">
					<div class="panel-body">
						<div class="col-md-2"><s:property value="#order.id" /></div>
						<div class="col-md-4"><s:property value="#order.time.getTime().toString()" /></div>
						<div class="col-md-2"><s:property value="#order.bookNumber" /></div>
						<div class="col-md-2"><fmt:formatNumber pattern="#0.00"><s:property value="#order.totalMoney" /></fmt:formatNumber></div>
						<div class="col-md-1 col-md-offset-1"><a href="/myorder?id=<s:property value="#order.id" />"><input type="button" class="btn btn-primary btn-xs show-detail" value="Detail"></a></div>
						<br/>
					</div>
				</s:iterator>

			</div>

		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>
<%--<jsp:include page="footer.jsp" />--%>