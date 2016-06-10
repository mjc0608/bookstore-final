<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="header.jsp"%>

<script type="text/javascript">
    function removeCart(bookid) {
        document.getElementsByName(bookid)[0].action='/removecart';
        document.getElementsByName(bookid)[0].submit();
    }

    function modifyCart(bookid) {
        document.getElementsByName(bookid)[0].action='/modifycart';
        document.getElementsByName(bookid)[0].submit();
    }
</script>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <label class="col-md-1">ID</label>
                    <label class="col-md-3">Name</label>
                    <label class="col-md-1">Price/$</label>
                    <label class="col-md-3">Number</label>
                    <label class="col-md-2">Total/$</label>
                    <br/>
                </div>
                <s:iterator value="items" id="item">
                    <div class="panel-body">
                        <form action="/modifycart" name="<s:property value="#item.book.id" />" method="POST">
                            <div class="col-md-1"><s:property value="#item.book.id" /></div>
                            <div class="col-md-3"><s:property value="#item.book.name" /></div>
                            <div class="col-md-1"><s:property value="#item.book.price" /></div>
                            <div class="col-md-2">
                                <input type="number" class="form-control col-md-1" name="quantity" value="<s:property value="#item.quantity" />">
                            </div>
                            <div class="col-md-1 col-md-offset-1">
                                <fmt:formatNumber pattern="#0.00"><s:property value="#item.quantity*#item.book.price"/></fmt:formatNumber>
                            </div>
                            <div class="col-md-1">
                                <input type="button" class="btn btn-primary show-detail" value="Modify" onclick="modifyCart(<s:property value="#item.book.id" />)">
                                <input type="hidden" name="bookID" value="<s:property value="#item.book.id" />">
                            </div>
                            <div class="col-md-1"><button type="button" class="btn btn-danger show-detail" value="test" onclick="removeCart(<s:property value="#item.book.id" />)">Remove</button></div>
                            <%--<div class="col-md-1"><input type="submit" class="btn btn-warning show-detail" value="Modify"></div>--%>
                            <br/>
                        </form>
                        <%--<form action="/removecart" method="POST" class="col-md-1">--%>
                            <%--<input type="submit" class="btn btn-warning show-detail" value="Modify">--%>
                            <%--<input type="hidden" name="bookID" value="<s:property value="#item.book.id" />">--%>
                        <%--</form>--%>
                    </div>
                </s:iterator>

                <div class="panel-body">
                    <div class="col-md-3 pull-right text-right"><h4>$<fmt:formatNumber pattern="#0.00"><s:property value="totalPrice" /></fmt:formatNumber> Totally</h4></div>
                </div>
                <div class="panel-body pull-right">
                    <form action="/submitcart" method="POST">
                        <input type="submit" class="btn btn-primary show-detail" value="Sumbit Order">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />