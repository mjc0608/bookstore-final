<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"--%>
	<%--pageEncoding="ISO-8859-1"%>--%>
<%--<%@ taglib prefix="s" uri="/struts-tags"%>--%>

<%--<jsp:include page="header.jsp" />--%>

<%@ include file="header.jsp"%>

<%--<script src="/statics/js/masonry.pkgd.js"></script>--%>
<script src="/statics/js/imagesloaded.pkgd.min.js"></script>
<div id="text-reveal" class="reveal-modal bookinfo">
	<div>
		<h2 id="book-title"></h2>
		<h4 id="book-cate"></h4>
		<h4>Detail: </h4><p id="book-desc"></p>
		<h4 id="book-price"></h4>
	</div>
	<form method="POST" action="/addcart">
		<div class="input-group">
			<input type="number" class="form-control" name="quantity" placeholder="Quantity">
			<input type="hidden" name="bookID" id="book-id">
              <span class="input-group-btn">
                <input class="btn btn-default btn-primary" type="submit" value="Add to Cart">Add to Cart</input>
              </span>
		</div>
	</form>
	<a class="close-reveal-modal">&#215;</a>
</div>

<div id="img-reveal" class="reveal-modal bookinfo">
	<div style="padding: 30px 0px 0px 0px">
		<img id="large-img" class="carousel-inner img-responsive" src="">
	</div>
	<a class="close-reveal-modal">&#215;</a>
</div>

<div class="container">
	<div class="col-md-10 col-md-offset-1">

		<div id="masonry" class="grid" data-masonry='{"itemSelector": ".grid-item","isAnimated": true}'>
		<s:iterator value="books" id="book">
			<div class="col-sm-6 col-md-4 grid-item">
				<div class="thumbnail">
					<div>
						<a href="javascript:show_large_img(<s:property value="id"/>)">
							<img id="img-<s:property value="id"/>" style="padding-top: 5px" class="carousel-inner img-responsive" src="/img?id=<s:property value="imageID"/>" alt="<s:property value="name"/>">
						</a>
					</div>
					<div class="caption">
						<a id="book-<s:property value="id"/>" href="javascript:get_book_info(<s:property value="id"/>)">
							<div class="row">
								<h4 class="col-md-8">$<s:property value="price"/></h4>
							</div>
							<p><s:property value="name"/></p>
						</a>
					</div>
				</div>
			</div>
		</s:iterator>
		</div>

	</div>
</div>
<script src="/statics/js/masonry.pkgd.js"></script>
<script>
	var $container = $('#masonry');
	$container.imagesLoaded( function() {
		$container.masonry({
			itemSelector : '.grid-item',
			isAnimated: true
		});
	}
	)
</script>

<script>
	function get_book_info(id) {
		$.get("/bookinfojson?id="+id,function(data,status){
			if (status!="success") return;
			$('#book-title').html(data.name);
			$('#book-cate').html('Category: '+data.category);
			$('#book-desc').html(data.description);
			$('#book-price').html("Price: $"+data.price);
			$('#book-id').attr('value',id);
		});
		$('#text-reveal').reveal({
			animation: 'fadeAndPop',                   //fade, fadeAndPop, none
			animationspeed: 300,                       //how fast animtions are
			closeonbackgroundclick: true,              //if you click background will modal close?
			dismissmodalclass: 'close-reveal-modal'    //the class of a button or element that will close an open modal
		});
	}
	function show_large_img(id) {
		$('#large-img').attr('src',$('#img-'+id).attr('src'));
		$('#img-reveal').reveal({
			animation: 'fadeAndPop',                   //fade, fadeAndPop, none
			animationspeed: 300,                       //how fast animtions are
			closeonbackgroundclick: true,              //if you click background will modal close?
			dismissmodalclass: 'close-reveal-modal'    //the class of a button or element that will close an open modal
		});
	}
</script>

<%@ include file="footer.jsp"%>
<%--<jsp:include page="footer.jsp" />--%>