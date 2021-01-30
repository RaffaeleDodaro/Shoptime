<%@ page language="java" contentType="text/html; charset=iso-8859-15"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="/js/productManagement.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/mp.css" type="text/css" />
<script src="/js/productManagement.js"></script>
<jsp:include page="headMarketPlace.jsp" />
</head>
<body>

	<jsp:include page="navbarMarketPlace.jsp" />
	<div>
		<form class="form-horizontal">
			<fieldset>

				<!-- Form Name -->
				<legend>Vendi prodotti!</legend>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="name" id="labelNome">Nome</label>
					<div class="col-md-4">
						<input id="textName" name="name" class="form-control input-md"
							type="text">

					</div>
				</div>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="price" id="labelPrice">Prezzo</label>
					<div class="col-md-4">
						<input id="textPrice" name="price" class="form-control input-md"
							required="" type="number" step=".01">

					</div>
				</div>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="textImage"
						id="labelimage">Immagine</label>
					<div class="col-md-4">
						<input id="textImage" name="image" class="form-control input-md"
							required="" type="text">

					</div>
				</div>

				<!-- Select Basic -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="description"
						id="labeldescription">Descrizione</label>
					<div class="col-md-4">
						<textarea id="textDescription" rows="4" cols="50"></textarea>
					</div>
				</div>
				

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="textQuantity"
						id="labelQuantity">Quantita'</label>
					<div class="col-md-4">
						<input id="textQuantity" class="form-control input-md" required=""
							type="number">

					</div>
				</div>

				<label for="category">Scegli una categoria</label> <select
					id="category" name="category">
					<!-- <option value="">Elettronica</option>-->
					<option value="Informatica">Informatica</option>
					<option value="Videogiochi">Videogiochi</option>

					<!--<option value="CD e Vinili">CD e Vinili</option> -->
					<option value="Film">Film e TV</option>
					<option value="Cucina">Cucina</option>
				</select>

				<div class="form-group">
					<div class="col-md-4">
						<input type="submit" class="btn btn-dark"
							onclick="sellProductFunction()" value="Vendi"></input>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
	<jsp:include page="footerMarketPlace.jsp" />
</body>
</html>