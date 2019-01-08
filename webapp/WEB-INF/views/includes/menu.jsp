   <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
   <spring:url value="/" var="urlRoot"></spring:url>
   <spring:url value="/peliculas" var="urlPeliIndex"></spring:url>
   <spring:url value="/banners" var="urlBanIndex"></spring:url>
   <spring:url value="/noticias" var="urlNotIndex"></spring:url>
   <spring:url value="/acerca" var="urlAbout"></spring:url>
   <!-- Fixed navbar -->
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="${urlRoot}">My CineSite</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">          
          	<li><a href="${urlPeliIndex}/indexPaginate?page=0">Peliculas</a></li>
          	<li><a href="${urlBanIndex}/indexPaginate?page=0"">Banners</a></li> 
          	<li><a href="${urlNotIndex}/indexPaginate?page=0"">Noticias</a></li> 
            <li><a href="${urlAbout}">Acerca</a></li>
            <li><a href="#">Login</a></li>        
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>