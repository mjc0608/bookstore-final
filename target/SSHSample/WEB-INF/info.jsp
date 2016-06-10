<%@ taglib prefix="s" uri="/struts-tags" %>

<%@include file="header.jsp"%>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="text-center"><label>User ID: <s:property value="user.id"/></label></div>
                </div>

                <div class="panel-body">
                    <form action="/modifyuser" method="POST" role="form">
                        <div class="form-group">
                            <label for="username">username</label>
                            <input id="username" type="text" class="form-control" name="user.username" disabled value="<s:property value="user.username" />">
                        </div>
                        <br>
                        <div>
                            <label for="email">email</label>
                            <input id="email" type="text" class="form-control" name="user.email" value="<s:property value="user.email" />">
                        </div>
                        <br>
                        <div>
                            <label for="password">password</label>
                            <input id="password" type="text" class="form-control" name="user.password" value="<s:property value="user.password" />">
                        </div>
                        <br>
                        <input type="submit" class="btn btn-primary show-detail" value="Modify">
                        <input type="hidden" name="user.id" value="<s:property value="user.id" />">
                        <br/>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />