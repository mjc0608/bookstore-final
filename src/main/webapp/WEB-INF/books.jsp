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
					<label class="col-md-2">Name</label>
					<label class="col-md-2">Category</label>
					<label class="col-md-6">Description</label>
					<label class="col-md-1">Price</label>
					<label class="col-md-1"></label>
					<br/>
				</div>
				<s:iterator value="books" id="book">
				<div class="panel-body">
					<div class="col-md-2"><s:property value="#book.name" /></div>
					<div class="col-md-2"><s:property value="#book.category" /></div>
					<div class="col-md-6"><s:property value="#book.description" /></div>
					<div class="col-md-1"><s:property value="#book.price" /></div>
					<div class="col-md-1"><a href="/bookinfo?id=<s:property value="#book.id" />"><input type="button" class="btn btn-primary btn-xs show-detail" value="Modify"></a></div>
					<br/>
				</div>
				</s:iterator>
			</div>
			<div class="panel panel-default text-center">
				<div class="panel-heading">
					<label>Add Book</label>
					<br/>
				</div>

				<form action="/addbook" method="POST" accept-charset="UTF-8" enctype="multipart/form-data">
					<br/>
					<div class="panel-body">
						<div class="col-md-4">
							<input type="text" name="book.name" class="form-control" placeholder="Book Name">
						</div>
						<div class="col-md-4">
							<input type="text" name="book.category" class="form-control" placeholder="Category">
						</div>
						<div class="col-md-4">
							<input type="text" name="book.price" class="form-control" placeholder="Price">
						</div>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-2">
								<p>Upload Image:</p>
							</div>
							<div class="col-md-3">
								<input type="file" name="book.image" id="upload_file">
							</div>
						</div>
					</div>
					<div class="panel-body">
						<div class="col-md-12">
							<textarea class="form-control" name="book.description" rows="10" placeholder="Description of the Book" style="resize:vertical"></textarea>
						</div>
					</div>

					<input type="submit" class="btn btn-primary show-detail" value="Add">

				</form>

				<br/>
			</div>
		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>
<%--<jsp:include page="footer.jsp" />--%>