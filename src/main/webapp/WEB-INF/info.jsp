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
                    <form action="/modifyself" method="POST" role="form" enctype="multipart/form-data">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="username">username</label>
                                <input id="username" type="text" class="form-control" disabled value="<s:property value="user.username" />">
                                <input type="hidden" class="form-control" name="user.username" value="<s:property value="user.username" />">
                            </div>
                            <br>
                            <div>
                                <label for="email">email</label>
                                <input id="email" type="text" class="form-control" name="user.email" value="<s:property value="user.email" />">
                            </div>
                            <br>
                            <div>
                                <label for="address">address</label>
                                <input id="address" type="text" class="form-control" name="user.address" value="<s:property value="user.address" />">
                            </div>
                            <br>
                            <div>
                                <label for="password">password</label>
                                <input id="password" type="text" class="form-control" name="user.password" value="<s:property value="user.password" />">
                            </div>
                            <br/>
                            <div>
                                <label for="totalorder">total order: </label>
                                <span id="totalorder"> <s:property value="totalOrder" /></span>
                                <br/>
                                <label for="totalspent">total spent: </label>
                                <span id="totalspent"> $<s:property value="totalSpent" /></span>
                            </div>
                            <br>
                            <input type="submit" class="btn btn-primary show-detail" value="Modify">
                            <input type="hidden" name="user.id" value="<s:property value="user.id" />">
                            <br/>
                        </div>
                        <div class="col-md-5 col-md-offset-1">
                            <br>
                            <br>
                            <div>
                                <img style="width:256px;height: 256px;" src="/img?id=<s:property value="user.imageID" />">
                            </div>
                            <div class="col-md-12">
                                upload image
                                <input type="file" name="user.image" id="upload_file">
                            </div>
                        </div>
                    </form>

                </div>
            </div>

        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />