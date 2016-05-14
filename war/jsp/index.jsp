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
                    <li class="active">
                        <a href="index"><i class="fa fa-home"></i> Inicio</a>
                    </li>
                    <li>
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
                        <a href="add"><i class="fa fa-fw fa-edit"></i> Añadir Hashtags</a>
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
                        <h1 class="page-header">
                            Hashtags <small>monitorizados</small>
                            <span><c:out value="${fecha}" /></span>
                            <!-- <span class="franja-horaria">Prime-Time</span> -->
                        </h1>
                            
                        <ol class="breadcrumb">
                            <li class="active">
                                <i class="fa fa-list"></i> Hashtags
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

                <!-- List hashtag -->
                <div class="row list-hashtag">
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-hashtag fa-3x"></i>
                                    </div>
                                    <div class="col-xs-9">
                                        <div class="huge"><c:out value="${hashtag1.nombre}" /></div>
                                    </div>
                                </div>
                            </div>
                            <a href="hashtagDetail?id=${hashtag1.id}">
                                <div class="panel-footer">
                                    <span class="pull-left">Ver detalles</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <c:if test="${not empty hashtag2}">
                    	<div class="col-lg-3 col-md-6">
	                        <div class="panel panel-green-black">
	                            <div class="panel-heading">
	                                <div class="row">
	                                    <div class="col-xs-3">
	                                        <i class="fa fa-hashtag fa-3x"></i>
	                                    </div>
	                                    <div class="col-xs-9">
	                                        <div class="huge"><c:out value="${hashtag2.nombre}" /></div>
	                                    </div>
	                                </div>
	                            </div>
	                            <a href="hashtagDetail?id=${hashtag2.id}">
	                                <div class="panel-footer">
	                                    <span class="pull-left">Ver detalles</span>
	                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
	                                    <div class="clearfix"></div>
	                                </div>
	                            </a>
	                        </div>
	                    </div>
					</c:if>
                    <c:if test="${not empty hashtag3}">
	                    <div class="col-lg-3 col-md-6">
	                        <div class="panel panel-orange">
	                            <div class="panel-heading">
	                                <div class="row">
	                                    <div class="col-xs-3">
	                                        <i class="fa fa-hashtag fa-3x"></i>
	                                    </div>
	                                    <div class="col-xs-9">
	                                        <div class="huge"><c:out value="${hashtag3.nombre}" /></div>
	                                    </div>
	                                </div>
	                            </div>
	                            <a href="hashtagDetail?id=${hashtag3.id}">
	                                <div class="panel-footer">
	                                    <span class="pull-left">Ver detalles</span>
	                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
	                                    <div class="clearfix"></div>
	                                </div>
	                            </a>
	                        </div>
	                    </div>
	                </c:if>
	                <c:if test="${not empty hashtag4}">
	                    <div class="col-lg-3 col-md-6">
	                        <div class="panel panel-red">
	                            <div class="panel-heading">
	                                <div class="row">
	                                    <div class="col-xs-3">
	                                        <i class="fa fa-hashtag fa-3x"></i>
	                                    </div>
	                                    <div class="col-xs-9">
	                                        <div class="huge"><c:out value="${hashtag4.nombre}" /></div>
	                                    </div>
	                                </div>
	                            </div>
	                            <a href="hashtagDetail?id=${hashtag4.id}">
	                                <div class="panel-footer">
	                                    <span class="pull-left">Ver detalles</span>
	                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
	                                    <div class="clearfix"></div>
	                                </div>
	                            </a>
	                        </div>
	                    </div>
	            	</c:if>
                </div>
                <!-- /.List hashtag -->

                <!-- Timeline -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-blue-black">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Timeline</h3>
                            </div>
                            <div class="panel-body">
                                <div id="morris-line-chart"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.Timeline -->

                <!-- /.row -->

                <div class="row">
                    <div class="col-lg-6">
                        <div class="panel panel-yellow">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-plus"></i> Tweets totales</h3>
                            </div>
                            <div class="panel-body">
                                <div id="morris-donut-chart"></div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-lg-6">
                        <div class="panel panel-green">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-server"></i> Últimos tweets</h3>
                            </div>
                            <div class="panel-body">
                                <div class="list-group">
                                
                                	<c:forEach items="${lastTweets}" var="tweet">
										<a href="#" class="list-group-item">
	                                        <span class="badge"><c:out value="${tweet.usuario}" /></span>
	                                        <i class="fa fa-fw fa-twitter"></i> <c:out value="${tweet.texto}" />
	                                    </a>
									</c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-red">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-clock-o"></i> Actividad</h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                            <tr>
                                                <th><i class="fa fa-hashtag"></i></th>
                                                <th><i class="fa fa-retweet"></i></th>
                                                <th><i class="fa fa-heart"></i></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td><c:out value="${hashtag1.nombre}" /></td>
                                                <td><c:out value="${retweetsCount[0]}" /></td>
                                                <td><c:out value="${favCount[0]}" /></td>
                                            </tr>
                                            <c:if test="${not empty hashtag2}">
	                                            <tr>
	                                                <td><c:out value="${hashtag2.nombre}" /></td>
	                                                <td><c:out value="${retweetsCount[1]}" /></td>
	                                                <td><c:out value="${favCount[1]}" /></td>
	                                            </tr>
                                            </c:if>
                                            <c:if test="${not empty hashtag3}">
	                                            <tr>
	                                                <td><c:out value="${hashtag3.nombre}" /></td>
	                                                <td><c:out value="${retweetsCount[2]}" /></td>
	                                                <td><c:out value="${favCount[2]}" /></td>
	                                            </tr>
                                            </c:if>
                                            <c:if test="${not empty hashtag4}">
	                                            <tr>
	                                                <td><c:out value="${hashtag4.nombre}" /></td>
	                                                <td><c:out value="${retweetsCount[3]}" /></td>
	                                                <td><c:out value="${favCount[3]}" /></td>
	                                            </tr>
                                            </c:if>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>    
                </div>
                <!-- /.row -->

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

    <!-- Morris Charts JavaScript -->
    <script src="js/plugins/morris/raphael.min.js"></script>
    <script src="js/plugins/morris/morris.min.js"></script>
    
    <!-- Donut Chart -->
    <script type="text/javascript">
	    Morris.Donut({
	        element: 'morris-donut-chart',
	        data: [{
	            label: "#" + "${hashtag1.nombre}",
	            value: "${tweetsCount[0]}"
	        }, {
	            label: "#" + "${hashtag2.nombre}",
	            value: "${tweetsCount[1]}"
	        }, {
	            label: "#" + "${hashtag3.nombre}",
	            value: "${tweetsCount[2]}"
	        }, {
	            label: "#" + "${hashtag4.nombre}",
	            value: "${tweetsCount[3]}"
	        }],
	        resize: true,
	        colors: ['#337AB7', '#088A08', '#FF8000', '#d9534f']
	    });
    </script>
    
    <!-- Line Chart -->
    <script type="text/javascript">
    	var data = [];
    	var intervalos = "${intervalos}";
    	var maxTime = "${maxTime}";
    	var dateFin = "${dateFin}";
    	var minTime = "${minTime}";
    	var dateInicio = "${dateInicio}";
    	var resta = "${resta}";
    	
    	
    	console.log("intervalos", intervalos);
    	console.log("maxTime", maxTime);
    	console.log("dateFin", dateFin);
    	console.log("minTime", minTime);
    	console.log("dateInicio", dateInicio);
    	console.log("resta", resta);
    	
    	
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
    		var tweetsH1 = [];
    		var tweetsH2 = [];
    		var tweetsH3 = [];
    		var tweetsH4 = [];
    		<c:forEach items="${datosH1}" var="dato">
    			var a = "${dato.intervalo}";
    			var b = a.split("CEST");
    			var d = new Date(b[0]);
				fechas.push(d);
    			tweetsH1.push("${dato.tweets}");
			</c:forEach>
			<c:forEach items="${datosH2}" var="dato">
				tweetsH2.push("${dato.tweets}");
			</c:forEach>
			<c:forEach items="${datosH3}" var="dato">
				tweetsH3.push("${dato.tweets}");
			</c:forEach>
			<c:forEach items="${datosH4}" var="dato">
				tweetsH4.push("${dato.tweets}");
			</c:forEach>
    		var format = js_yyyy_mm_dd_hh_mm(fechas[i]);
    		data.push({
    			period: format,
	            hashtag1: tweetsH1[i],
	            hashtag2: tweetsH2[i],
	            hashtag3: tweetsH3[i],
	            hashtag4: tweetsH4[i]
    		});
    	}
    
	    Morris.Line({
	        // ID of the element in which to draw the chart.
	        element: 'morris-line-chart',
	        // Chart data records -- each entry in this array corresponds to a point on
	        // the chart.
	        data: data,
	        // The name of the data record attribute that contains x-visitss.
	        xkey: 'period',
	        // A list of names of data record attributes that contain y-visitss.
	        ykeys: ['hashtag1', 'hashtag2', 'hashtag3', 'hashtag4'], // Valores eje y
	        // Labels for the ykeys -- will be displayed when you hover over the
	        // chart.
	        labels: ["${hashtag1.nombre}", "${hashtag2.nombre}", "${hashtag3.nombre}", "${hashtag4.nombre}"],
	        hideHover: 'auto',
	        lineColors: ['#337AB7', '#088A08', '#FF8000', '#d9534f'],
	        // Disables line smoothing
	        smooth: false,
	        resize: true
	    });
    </script>

</body>

</html>
