<%@ taglib prefix="s" uri="/struts-tags" %>

<%@include file="header.jsp"%>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-default text-center">
                <div class="panel-heading">
                    <label><s:property value="book.id" /></label>
                    <br/>
                </div>

                <form action="/modifybook" method="POST">
                    <br/>
                    <div class="panel-body">
                        <div class="col-md-4">
                            <input type="text" name="book.name" class="form-control" placeholder="Book Name" value="<s:property value="book.name" />">
                        </div>
                        <div class="col-md-4">
                            <input type="text" name="book.category" class="form-control" placeholder="Category" value="<s:property value="book.category" />">
                        </div>
                        <div class="col-md-4">
                            <input type="text" name="book.price" class="form-control" placeholder="Price" value="<s:property value="book.price" />">
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="col-md-12">
                            <input type="text" name="book.photoUrl" class="form-control" placeholder="Photo URL of the Book" value="<s:property value="book.photoUrl" />">
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="col-md-12">
                            <textarea class="form-control" name="book.description" rows="10" placeholder="Description of the Book" style="resize:vertical"><s:property value="book.description" /></textarea>
                        </div>
                    </div>
                    <input type="hidden" name="book.id" value="<s:property value="book.id" />">
                    <input type="submit" class="btn btn-primary show-detail" value="Make Change">
                </form>

                <br/>
            </div>
            <div class="pull-right">
                <form action="/removebook" method="POST">
                    <input type="submit" class="btn btn-danger show-detail" value="Delete Book">
                    <input type="hidden" name="id" value="<s:property value="book.id" />">
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />