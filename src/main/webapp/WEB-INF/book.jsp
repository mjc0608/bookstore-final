<%@ taglib prefix="s" uri="/struts-tags" %>

<%--<jsp:include page="header.jsp" />--%>

<%@include file="header.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<div class="panel panel-default">
				<div class="panel-body">
					<h2><s:property value="book.name"/></h2>
					<br/>
					<div class="panel-body">
						<img src="<s:property value="book.photoUrl" />" class="img-responsive center-block" alt="">
					</div>
					<br/>
					<p><s:property value="book.description"/></p>
					<br/>
					<h4>Only  $<s:property value="book.price"/>!</h4>

				</div>
				<div class="panel-body">
					<form action="/addcart" method="POST">
						<div class="col-md-2">
							<input type="number" class="form-control col-md-5" name="quantity" placeholder="Quantity">
						</div>
						<input type="submit" class="btn btn-primary show-detail" value="Add to Cart">
						<input type="hidden" name="bookID" value="<s:property value="book.id" />">
						<br/>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="footer.jsp" %>