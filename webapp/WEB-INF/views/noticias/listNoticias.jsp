<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Listado de Noticias</title>
<spring:url value="/resources" var="urlPublic" />
<spring:url value="/noticias/create" var="urlCreate" />
<spring:url value="/noticias/edit" var="urlEdit" />
<spring:url value="/noticias" var="urlNoticia" />
<link href="${urlPublic}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">

</head>

<body>

	<jsp:include page="../includes/menu.jsp"></jsp:include>

	<div class="container theme-showcase" role="main">

		<h3>Listado de Noticias</h3>

		<c:if test="${mensaje!=null}">
			<div class='alert alert-success' role="alert">${mensaje}</div>
		</c:if>

		<a href="${urlCreate}" class="btn btn-success" role="button"
			title="Nueva Pelicula">Nueva</a><br> <br>

		<div class="table-responsive">
			<table class="table table-hover table-striped table-bordered">
				<tr>
					<th>Id</th>
					<th>Titulo</th>
					<th>Fecha</th>
					<th>Estatus</th>
					<th>Opciones</th>
				</tr>
				<c:forEach var="noticias" items="${noticias.content}">
					<tr>
						<td>${noticias.id}</td>
						<td>${noticias.titulo}</td>
						<td><fmt:formatDate pattern="dd-MM-yyyy"
								value="${noticias.fecha}" /></td>
						<c:choose>
							<c:when test="${noticias.estatus eq 'Activa' }">
								<td><span class="label label-success">${noticias.estatus}</span></td>
							</c:when>
							<c:otherwise>
								<td><span class="label label-danger">${noticias.estatus}</span></td>
							</c:otherwise>
						</c:choose>
						<td><a href="${urlEdit}/${noticias.id}"
							class="btn btn-success btn-sm" role="button" title="Edit"><span
								class="glyphicon glyphicon-pencil"></span></a> <a
							href="${urlNoticia}/delete/${noticias.id}"
							onclick='return confirm("Estas seguro?")'
							class="btn btn-danger btn-sm" role="button" title="Eliminar"><span
								class="glyphicon glyphicon-trash"></span></a></td>
					</tr>
				</c:forEach>
			</table>
			<nav aria-label="">
				<ul class="pager">
					<li><a
						href="${urlNoticia}/indexPaginate?page=${noticias.number -1 }">Anterior</a></li>
					<li><a
						href="${urlNoticia}/indexPaginate?page=${noticias.number + 1 }">Siguiente</a></li>
				</ul>
			</nav>
		</div>
		<hr class="featurette-divider">

		<jsp:include page="../includes/footer.jsp"></jsp:include>

	</div>
	<!-- /container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
