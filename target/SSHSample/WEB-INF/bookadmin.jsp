<%@ taglib prefix="s" uri="/struts-tags" %>

<%@include file="header.jsp"%>


<div id="img-reveal" class="reveal-modal bookinfo">
    <div style="padding: 30px 0px 0px 0px">
        <img id="large_img" class="carousel-inner img-responsive" src="/img?id=<s:property value="book.imageID"/>">
    </div>
    <a class="close-reveal-modal">&#215;</a>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-default text-center">
                <div class="panel-heading">
                    <label><s:property value="book.id" /></label>
                    <br/>
                </div>

                <form action="/modifybook" method="POST" enctype="multipart/form-data">
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
                        <div class="row">
                            <div class="col-md-2">
                                <a class="btn btn-default btn-xs" href="javascript:show_large_img(<s:property value="id"/>)">Show Image</a>
                            </div>
                            <div class="col-md-2">
                                <p>Upload Image:</p>
                            </div>
                            <input type="hidden" name="book.imageID" value="<s:property value="book.imageID"/>">
                            <div class="col-md-3">
                                <input type="file" name="book.image" id="upload_file">
                            </div>
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

<script>
    function show_large_img(id) {
        $('#img-reveal').reveal({
            animation: 'fadeAndPop',                   //fade, fadeAndPop, none
            animationspeed: 300,                       //how fast animtions are
            closeonbackgroundclick: true,              //if you click background will modal close?
            dismissmodalclass: 'close-reveal-modal'    //the class of a button or element that will close an open modal
        });
    }
</script>

<jsp:include page="footer.jsp" />