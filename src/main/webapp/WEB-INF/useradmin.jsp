<%@ taglib prefix="s" uri="/struts-tags" %>

<%@include file="header.jsp"%>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <label class="col-md-1">ID</label>
                    <label class="col-md-2">Name</label>
                    <label class="col-md-3">Email</label>
                    <label class="col-md-1">Admin</label>
                    <label class="col-md-3">Password</label>
                    <br/>
                </div>

                <form action="/modifyuser" method="POST" enctype="multipart/form-data">
                    <div class="panel-body">
                        <div class="col-md-1"><s:property value="user.id" /></div>
                        <div class="col-md-2">
                            <input type="text" class="form-control" name="user.username" disabled value="<s:property value="user.username" />">
                            <input type="hidden" class="form-control" name="user.username" value="<s:property value="user.username" />">
                        </div>
                        <div class="col-md-3">
                            <input type="text" class="form-control" name="user.email" value="<s:property value="user.email" />">
                        </div>
                        <div class="col-md-1">
                            <s:if test="#user.admin==true">
                                <s:checkbox name="user.admin" checked=""/>
                            </s:if>
                            <s:else>
                                <s:checkbox name="user.admin" />
                            </s:else>
                        </div>
                        <div class="col-md-5">
                            <input type="text" class="form-control" name="user.password" value="<s:property value="user.password" />">
                        </div>

                        <br/>
                    </div>
                    <div class="panel-body">
                        <div class="col-md-1"><label>Address</label></div>
                        <div class="col-md-5">
                            <input type="text" class="form-control" name="user.address" value="<s:property value="user.address" />">
                        </div>
                        <div class="col-md-1">
                            <label>Image</label>
                        </div>
                        <div class="col-md-5">
                            <img style="height: 256px; width: 256px;;" src="/img?id=<s:property value="user.imageID" />">
                            <input type="hidden" name="user.imageID" value="<s:property value="user.imageID" />">
                            <input type="file" name="user.image">
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="pull-right">
                            <input type="submit" class="btn btn-primary show-detail" value="Modify">
                            <input type="hidden" name="user.id" value="<s:property value="user.id" />">
                        </div>
                    </div>
                </form>

            </div>
            <div class="pull-right">
                <form action="/removeuser" method="POST">
                    <input type="submit" class="btn btn-danger show-detail" value="Delete User">
                    <input type="hidden" name="id" value="<s:property value="user.id" />">
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />