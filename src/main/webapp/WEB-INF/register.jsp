
<%@ include file="header.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<div class="panel panel-default">
				<div class="panel-heading">Register</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" method="POST" action="/register">

						<div class="form-group">
							<label class="col-md-4 control-label">User Name</label>

							<div class="col-md-6">
								<input type="text" class="form-control" name="user.username">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-4 control-label">E-Mail</label>

							<div class="col-md-6">
								<input type="email" class="form-control" name="user.email">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-4 control-label">Password</label>

							<div class="col-md-6">
								<input type="password" class="form-control" name="user.password">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-4 control-label">Confirm Password</label>

							<div class="col-md-6">
								<input type="password" class="form-control" name="passwordConfirmation">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-4 control-label">Address</label>

							<div class="col-md-6">
								<input type="text" class="form-control" name="user.address">
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-6 col-md-offset-4">
								<button type="submit" class="btn btn-primary">
									<i class="fa fa-btn fa-user"></i>Register
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>