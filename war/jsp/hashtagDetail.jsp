<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Quien TV</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/style.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    
    <!-- Datatable CSS -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index"><img src="img/logo.png"></a>
            </div>

            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li>
                        <a href="index"><i class="fa fa-home"></i> Inicio</a>
                    </li>
                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#demo2"><i class="fa fa-fw fa-list-ol"></i> Búsquedas anteriores <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="demo2" class="collapse">
                        
                        	<c:forEach items="${searchList}" var="search">
                        		<li>
	                                <a href="index?searchId=${search.id}"><i class="fa fa-search"></i> <c:out value="${search.nombre}" /></a>
	                            </li>
                        	</c:forEach>
                        </ul>
                    </li>
                    <li class="active">
                        <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-list"></i> Hashtags <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="demo" class="collapse">
                            <li>
                                <a href="hashtagDetail?id=${hashtag1.id}"><i class="fa fa-hashtag"></i> <c:out value="${hashtag1.nombre}" /></a>
                            </li>
                            <c:if test="${not empty hashtag2}">                                          
	                            <li>
	                                <a href="hashtagDetail?id=${hashtag2.id}"><i class="fa fa-hashtag"></i> <c:out value="${hashtag2.nombre}" /></a>
	                            </li>
                            </c:if>
                            <c:if test="${not empty hashtag3}">
	                            <li>
	                                <a href="hashtagDetail?id=${hashtag3.id}"><i class="fa fa-hashtag"></i> <c:out value="${hashtag3.nombre}" /></a>
	                            </li>
                            </c:if>
                            <c:if test="${not empty hashtag4}">
	                            <li>
	                                <a href="hashtagDetail?id=${hashtag4.id}"><i class="fa fa-hashtag"></i> <c:out value="${hashtag4.nombre}" /></a>
	                            </li>
                            </c:if>
                        </ul>
                    </li>
                    <li>
                        <a href="add"><i class="fa fa-fw fa-edit"></i> Configuración de búsqueda</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header"><i class="fa fa-hashtag"></i><c:out value="${hashtag.nombre}" /><small> detalles</small>
                        <!-- <span class="franja-horaria">Prime-Time</span> -->
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-list"></i>  <a href="index">Hashtags</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-hashtag"></i> <c:out value="${hashtag.nombre}" />
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

                <!-- Timeline -->
                <!-- <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-red">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Timeline</h3>
                            </div>
                            <div class="panel-body">
                                <div id="morris-area-chart"></div>
                            </div>
                        </div>
                    </div>
                </div>-->
                
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-red">
                        	<div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-twitter"></i> Tweets</h3>
                            </div>
                            <div class="panel-body">
                                <table id="data" class="display" cellspacing="0" width="100%">
							        <thead>
							            <tr>
							                <th>Texto</th>
							                <th>Idioma</th>
							                <th>Localizacion</th>
							                <th>Usuario</th>
							                <th>Avatar</th>
							                <th>Perfil</th>
							                <th>Seguidores</th>
							                <th>Retweets</th>
							                <th>Favoritos</th>
							            </tr>
							        </thead>
							        <tfoot>
							            <tr>
							                <th>Texto</th>
							                <th>Idioma</th>
							                <th>Localizacion</th>
							                <th>Usuario</th>
							                <th>Avatar</th>
							                <th>Perfil</th>
							                <th>Seguidores</th>
							                <th>Retweets</th>
							                <th>Favoritos</th>
							            </tr>
							        </tfoot>
							        <tbody>
							        	<c:forEach items="${tweetList}" var="tweet">
								        	<tr>
								            	<td><c:out value="${tweet.texto}" /></td>
								                <td><c:out value="${tweet.idioma}" /></td>
								                <td><c:out value="${tweet.localizacion}" /></td>
								                <td><c:out value="${tweet.usuario}" /></td>
								                <td><img src="${tweet.avatar}"></td>
								                <td><a target="_blank" href="${tweet.linkProfile}">Link</a></td>
								                <td><c:out value="${tweet.seguidoresUsuario}" /></td>
								                <td><c:out value="${tweet.retweets}" /></td>
								                <td><c:out value="${tweet.favoritos}" /></td>
								            </tr>
							        	</c:forEach>
									</tbody>
								</table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-6">
                        <div class="panel panel-green">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-language"></i> Idiomas</h3>
                            </div>
                            <div class="panel-body chart-idiomas">
                                <canvas id="chart-area" width="50" height="50"/>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-map-marker"></i> Localización</h3>
                            </div>
                            <div class="panel-body">
                                <div id="morris-bar-chart"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-8">
                        <div class="panel panel-purple">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-list"></i> Top 5 Usuarios más activos</h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped tabla-usuarios">
                                        <thead>
                                            <tr>
                                                <th><i class="fa fa-twitter"></i> Cuenta</th>
                                                <th><i class="fa fa-users "></i> Seguidores</th>
                                                <th><i class="fa fa-hashtag"></i> Uso del Hashtag</th>
                                                <th><i class="fa fa-external-link"></i> Perfil</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td class="cuenta"><img src="${topAvatars[0]}"><c:out value="${topUsers[0]}" /></td>
                                                <td><c:out value="${topFollowers[0]}" /></td>
                                                <td><c:out value="${topFrecuencies[0]}" /></td>
                                                <td><a target="_blank" href="${topLinks[0]}">Link</a></td>
                                            </tr>
                                            <tr>
                                                <td class="cuenta"><img src="${topAvatars[1]}"><c:out value="${topUsers[1]}" /></td>
                                                <td><c:out value="${topFollowers[1]}" /></td>
                                                <td><c:out value="${topFrecuencies[1]}" /></td>
                                                <td><a target="_blank" href="${topLinks[1]}">Link</a></td>
                                            </tr>
                                            <tr>
                                                <td class="cuenta"><img src="${topAvatars[2]}"><c:out value="${topUsers[2]}" /></td>
                                                <td><c:out value="${topFollowers[2]}" /></td>
                                                <td><c:out value="${topFrecuencies[2]}" /></td>
                                                <td><a target="_blank" href="${topLinks[2]}">Link</a></td>
                                            </tr>
                                            <tr>
                                                <td class="cuenta"><img src="${topAvatars[3]}"><c:out value="${topUsers[3]}" /></td>
                                                <td><c:out value="${topFollowers[3]}" /></td>
                                                <td><c:out value="${topFrecuencies[3]}" /></td>
                                                <td><a target="_blank" href="${topLinks[3]}">Link</a></td>
                                            </tr>
                                            <tr>
                                                <td class="cuenta"><img src="${topAvatars[4]}"><c:out value="${topUsers[4]}" /></td>
                                                <td><c:out value="${topFollowers[4]}" /></td>
                                                <td><c:out value="${topFrecuencies[4]}" /></td>
                                                <td><a target="_blank" href="${topLinks[4]}">Link</a></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-4">
                        <div class="panel panel-orange">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-bullhorn"></i> Interacciones</h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped tabla-usuarios">
                                        <thead>
                                            <tr>
                                                <th><i class="fa fa-heart"></i> Favoritos</th>
                                                <th><i class="fa fa-retweet"></i> Retweets</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td><c:out value="${favsCount}" /></td>
                                                <td><c:out value="${retweetsCount}" /></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>  
                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
    
    <!-- DataTable -->
    <script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="js/plugins/morris/raphael.min.js"></script>
    <script src="js/plugins/morris/morris.min.js"></script>

    <!-- Charts -->
    <script src="js/plugins/chart/Chart.min.js"></script>
    
    <!-- Table of tweets -->
    <script type="text/javascript">
	    $(document).ready(function() {
	        $('#data').DataTable({
	        	"scrollY": 400,
	            "scrollX": true,
	            "language": {
	                "lengthMenu": "Mostrar _MENU_ resultados",
	                "zeroRecords": "Sin resultados",
	                "info": "Página _PAGE_ de _PAGES_",
	                "infoEmpty": "No hay registros disponibles",
	                "infoFiltered": "(filtrado de _MAX_ resultados totales)",
	                "loadingRecords": "Cargando...",
	                "processing":     "Procesando...",
	                "paginate": {
	                    "first":      "Primero",
	                    "last":       "Último",
	                    "next":       "Siguiente",
	                    "previous":   "Anterior"
	                },
	                "search": "Buscar:",
	            }
	        });
	    } );
    </script>
    
    <!-- Area Chart -->
    <!--<script type="text/javascript">
    	var data = [];
    	var intervalos = "${intervalos}";
    	
    	function js_yyyy_mm_dd_hh_mm (now) {
   		  	year = "" + now.getFullYear();
   		  	month = "" + (now.getMonth() + 1); if (month.length == 1) { month = "0" + month; }
   		  	day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
   		  	hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
   		  	minute = "" + now.getMinutes(); if (minute.length == 1) { minute = "0" + minute; }
   		  	return year + "-" + month + "-" + day + " " + hour + ":" + minute;
   		}
    	
    	for(var i = 0; i <= intervalos; i++) {
    		var fechas = [];
    		var tweets = [];
    		<c:forEach items="${datos}" var="dato">
    			var a = "${dato.intervalo}";
    			var b = a.split("CEST");
    			var d = new Date(b[0]);
				fechas.push(d);
    			tweets.push("${dato.tweets}");
			</c:forEach>
    		var format = js_yyyy_mm_dd_hh_mm(fechas[i]);
    		data.push({
    			period: format,
	            hashtag1: tweets[i],
    		});
    	}
    
	    Morris.Area({
	        element: 'morris-area-chart',
	        data: data,
	        xkey: 'period', // Valores eje x
	        ykeys: ['hashtag1'], // Valores eje y
	        labels: ['Cantidad'],
	        pointSize: 3,
	        hideHover: 'auto',
	        resize: true,
	        lineColors: ['#337AB7']
	        //'#5cb85c', '#f0ad4e', '#d9534f', '#FF8000', '#AC58FA', '#0404B4', '#088A08'
	    });
    </script>-->
    
    <!-- Idiomas -->
    <script type="text/javascript">
    
    	var pieData = [];
       	var languages = [];
       	var languageFrecuencies = [];
    	var colors = ["#F7464A", "#01DF01", "#2251EB", "#EDF904", "#F904F5", "#05F7FB", "#F6C30A", "#07F096", "#F20684", "#F37805", "#130199", "#87023C"];
		var highlightColors = ["#E64043", "#00B200", "#2251EB", "#F4FB6D", "#FA73F8", "#90F7F9", "#F8D865", "#78F2C3", "#F27EBC", "#F5B06F", "#524995", "#824962"];
       
      	<c:forEach items="${languages}" var="i">
	   		languages.push("${i}");
	   	</c:forEach>
	   	<c:forEach items="${languageFrecuencies}" var="i">
	   		languageFrecuencies.push("${i}");
   		</c:forEach>

       	for(var i = 0; i < languages.length; i++) {
			pieData.push({
		    	value: languages[i],
		    	color: colors[i],
		    	highlight: highlightColors[i],
		        label: languages[i]
		    });
        }
    
    	var ctx = document.getElementById("chart-area").getContext("2d");
    
    	var data = {
    	    labels: languages,
    	    datasets: [
    	        {
    	            data: languageFrecuencies,
    	            backgroundColor: colors,
    	            hoverBackgroundColor: highlightColors
    	        }]
    	};
    
    	var options = {
            responsive: true,
            legend: {
                position: 'bottom',
            },
            title: {
                display: false
            },
            animation: {
                animateScale: true,
                animateRotate: true
            }
        };
   		
	    var myDoughnutChart = new Chart(ctx, {
	        type: 'pie',
	        data: data,
	        options: options
	    }); 			
    </script>
    
    <!-- Bar Chart - Localización -->
    <script type="text/javascript">
    
    	var data = [];
       	var locations = [];
       	var locationFrecuencies = [];
       	var labels = [];
       	
       	<c:forEach items="${locations}" var="i">
	   		locations.push("${i}");
	   		labels.push("Tweets");
	   	</c:forEach>
	   	<c:forEach items="${locationFrecuencies}" var="i">
	   		locationFrecuencies.push("${i}");
			</c:forEach>
	
	   	for(var i = 0; i < locations.length; i++) {
			data.push({
		    	province: locations[i],
		    	value: locationFrecuencies[i]
		    });
	    }
    
	    Morris.Bar({
	        element: 'morris-bar-chart',
	        data: data,
	        xkey: 'province',
	        ykeys: ['value'],
	        labels: labels,
	        barColors: ['#f0ad4e'],
	        xLabelAngle: 25,
	        hideHover: 'auto',
	        resize: true
	    });
    </script>

</body>

</html>