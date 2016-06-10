<%@ taglib prefix="s" uri="/struts-tags" %>

<%@include file="header.jsp"%>

<script type="text/javascript">
    function removeOrderItem(bookid) {
        document.getElementsByName(bookid)[0].action='/removeorderitem';
        document.getElementsByName(bookid)[0].submit();
    }

    function modifyOrderItem(bookid) {
        document.getElementsByName(bookid)[0].action='/modifyorderitem';
        document.getElementsByName(bookid)[0].submit();
    }
</script>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div><h4>Order ID: <s:property value="order.id" /></h4></div>
            <div><h4>Total Price: $<fmt:formatNumber pattern="#0.00"><s:property value="totalPrice" /></fmt:formatNumber></h4></div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <label class="col-md-2">Book ID</label>
                    <label class="col-md-3">Book Name</label>
                    <label class="col-md-2">Price/$</label>
                    <label class="col-md-2">Quantity</label>
                    <br/>
                </div>
                <s:if test="order.status==0">
                    <s:iterator value="items" id="item">
                        <div class="panel-body">
                            <form action="/modifyorderitem" name="<s:property value="#item.book.id"/>" method="POST">
                                <div class="col-md-2"><s:property value="#item.book.id" /></div>
                                <div class="col-md-3"><s:property value="#item.book.name" /></div>
                                <div class="col-md-2"><s:property value="#item.book.price" /></div>
                                <div class="col-md-2">
                                    <input type="number" name="quantity" class="form-control" value="<s:property value="#item.quantity" />">
                                </div>
                                <input class="btn btn-primary col-md-offset-1" type="button" class="btn btn-primary show-detail" value="Modify" onclick="modifyOrderItem(<s:property value="#item.book.id"/>)">
                                <input type="button" class="btn btn-danger show-detail" value="Remove" onclick="removeOrderItem(<s:property value="#item.book.id"/>)">
                                <input type="hidden" name="orderID" value="<s:property value="order.id" />">
                                <input type="hidden" name="bookID" value="<s:property value="#item.book.id" />">
                                <br/>
                            </form>
                        </div>
                    </s:iterator>
                </s:if>
                <s:else>
                    <s:iterator value="items" id="item">
                        <div class="panel-body">
                            <form action="/modifyorderitem" name="<s:property value="#item.book.id"/>" method="POST">
                                <div class="col-md-2"><s:property value="#item.book.id" /></div>
                                <div class="col-md-3"><s:property value="#item.book.name" /></div>
                                <div class="col-md-2"><fmt:formatNumber pattern="#0.00"><s:property value="#item.book.price" /></fmt:formatNumber></div>
                                <div class="col-md-2"><s:property value="#item.quantity" /></div>
                                <br/>
                            </form>
                        </div>
                    </s:iterator>
                </s:else>
            </div>
            <s:if test="order.status==0">
                <div class="pull-right">
                    <form action="/setorderstatus" method="POST">
                        <input type="submit" class="btn btn-success show-detail" value="Finish Order">
                        <input type="hidden" name="status" value="1">
                        <input type="hidden" name="id" value="<s:property value="order.id" />">
                    </form>
                </div>
            </s:if>
            <s:else>
                <div class="pull-right">
                    <form action="/setorderstatus" method="POST">
                        <input type="submit" class="btn btn-warning show-detail" value="Unfinish Order">
                        <input type="hidden" name="status" value="0">
                        <input type="hidden" name="id" value="<s:property value="order.id" />">
                    </form>
                </div>
            </s:else>
            <br/>
            <br/>
            <div class="col-md-offset-2 pull-right">
                <form action="/removeorder" method="POST">
                    <input type="submit" class="btn btn-danger show-detail" value="Delete Order">
                    <input type="hidden" name="id" value="<s:property value="order.id" />">
                </form>
            </div>
            <br/>
            <br/>
            <br/>
            <s:if test="order.status==0">
                <div class="panel panel-default text-center">
                    <div class="panel-heading">
                        <label>Add to Order</label>
                        <br/>
                    </div>
                    <div class="panel-body">
                        <form action="/addorderitem" method="POST">

                            <div class="col-md-5"><input type="number" name="bookID" class="form-control" placeholder="Book ID"></div>
                            <div class="col-md-5"><input type="number" name="quantity" class="form-control" placeholder="Quantity"></div>
                            <input type="submit" class="btn btn-primary show-detail col-md-2" value="Add to Order">
                            <input type="hidden" name="orderID" value="<s:property value="order.id" />">
                        </form>
                        <br/>
                    </div>
                </div>
            </s:if>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />