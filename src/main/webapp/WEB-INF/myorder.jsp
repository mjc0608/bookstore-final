<%@ taglib prefix="s" uri="/struts-tags" %>

<%@include file="header.jsp"%>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div><h4>Order ID: <s:property value="order.id" /></h4></div>
            <div><h4>Total Price: $<s:property value="totalPrice" /></h4></div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <label class="col-md-2">Book ID</label>
                    <label class="col-md-3">Book Name</label>
                    <label class="col-md-2">Price/$</label>
                    <label class="col-md-2">Quantity</label>
                    <label class="col-md-2">Total/$</label>
                    <br/>
                </div>
                <s:iterator value="items" var="item">
                    <div class="panel-body">
                        <div class="col-md-2"><s:property value="#item.book.id" /></div>
                        <div class="col-md-3"><s:property value="#item.book.name" /></div>
                        <div class="col-md-2"><s:property value="#item.book.price" /></div>
                        <div class="col-md-2"><s:property value="#item.quantity" /></div>
                        <div class="col-md-2"><fmt:formatNumber pattern="#0.00"><s:property value="#item.quantity*#item.book.price" /></fmt:formatNumber></div>
                        <br/>
                    </div>
                </s:iterator>
            </div>
        </div>
        <br/>

    </div>
</div>
<jsp:include page="footer.jsp" />