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
                    <li>
                        <a href="index"><i class="fa fa-home"></i> Inicio</a>
                    </li>
                    <li class="active">
                        <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-list"></i> Hashtags <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="demo" class="collapse">
                            <li>
                                <a href="hashtag1"><i class="fa fa-hashtag"></i> Hashtag1</a>
                            </li>
                            <li>
                                <a href="hashtag2"><i class="fa fa-hashtag"></i> Hashtag2</a>
                            </li>
                            <li>
                                <a href="hashtag3"><i class="fa fa-hashtag"></i> Hashtag3</a>
                            </li>
                            <li>
                                <a href="hashtag4"><i class="fa fa-hashtag"></i> Hashtag4</a>
                            </li>
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
                        <h1 class="page-header"><i class="fa fa-hashtag"></i><c:out value="${hashtag.nombre}" /><small> detalles</small>
                        <!-- <span class="franja-horaria">Prime-Time</span> -->
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-list"></i>  <a href="index">Hashtags</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-hashtag"></i> Hashtag2
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

                <!-- Timeline -->
                <div class="row">
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
                </div>

                <div class="row">
                    <div class="col-lg-6">
                        <div class="panel panel-green">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-language"></i> Idiomas</h3>
                            </div>
                            <div class="panel-body chart-idiomas">
                                <canvas id="chart-area" width="240" height="240"/>
                            </div>

                            <div class="leyenda">
                                <div><i class="fa fa-square espanol"></i> Español</div>
                                <div><i class="fa fa-square ingles"></i> Inglés</div>
                                <div><i class="fa fa-square frances"></i> Francés</div>
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
                                                <td class="cuenta"><img src="img/${top1.img}"><c:out value="${top1.nombre}" /></td>
                                                <td><c:out value="${top1.seguidores}" /></td>
                                                <td><c:out value="${contador1}" /></td>
                                                <td><a href="${top1.link}">Link</a></td>
                                            </tr>
                                            <tr>
                                                <td class="cuenta"><img src="img/${top2.img}"><c:out value="${top2.nombre}" /></td>
                                                <td><c:out value="${top2.seguidores}" /></td>
                                                <td><c:out value="${contador2}" /></td>
                                                <td><a href="${top2.link}">Link</a></td>
                                            </tr>
                                            <tr>
                                                <td class="cuenta"><img src="img/${top3.img}"><c:out value="${top3.nombre}" /></td>
                                                <td><c:out value="${top3.seguidores}" /></td>
                                                <td><c:out value="${contador3}" /></td>
                                                <td><a href="${top3.link}">Link</a></td>
                                            </tr>
                                            <tr>
                                                <td class="cuenta"><img src="img/${top4.img}"><c:out value="${top4.nombre}" /></td>
                                                <td><c:out value="${top4.seguidores}" /></td>
                                                <td><c:out value="${contador4}" /></td>
                                                <td><a href="${top4.link}">Link</a></td>
                                            </tr>
                                            <tr>
                                                <td class="cuenta"><img src="img/${top5.img}"><c:out value="${top5.nombre}" /></td>
                                                <td><c:out value="${top5.seguidores}" /></td>
                                                <td><c:out value="${contador5}" /></td>
                                                <td><a href="${top5.link}">Link</a></td>
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
                                                <th><i class="fa fa-heart"></i> Favorito</th>
                                                <th><i class="fa fa-retweet"></i> Retweet</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td><c:out value="${favoritos}" /></td>
                                                <td><c:out value="${retweets}" /></td>
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

    <!-- Morris Charts JavaScript -->
    <script src="js/plugins/morris/raphael.min.js"></script>
    <script src="js/plugins/morris/morris.min.js"></script>

    <!-- Charts -->
    <script src="js/plugins/chart/Chart.min.js"></script>
    
    <!-- Area Chart -->
    <script type="text/javascript">
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
    </script>
    
    <script type="text/javascript">
    	var pieData = [
	        {
	            value: "${tweetsEspanol}",
	            color:"#F7464A",
	            highlight: "#E64043",
	            label: "Español"
	        },
	        {
	            value: "${tweetsIngles}",
	            color: "#01DF01",
	            highlight: "#00B200",
	            label: "Inglés"
	        },
	        {
	            value: "${tweetsFrances}",
	            color: "#2251EB",
	            highlight: "#183DB4",
	            label: "Francés"
	        }
	    ];
	
	    window.onload = function(){
	        var ctx = document.getElementById("chart-area").getContext("2d");
	        window.myPie = new Chart(ctx).Pie(pieData);
	    };
    </script>
    
    <!-- Bar Chart -->
    <script type="text/javascript">
    
	    Morris.Bar({
	        element: 'morris-bar-chart',
	        data: [{
	            city: 'Madrid',
	            value: "${tweetsMadrid}"
	        }, {
	            city: 'Barcelona',
	            value: "${tweetsBarcelona}"
	        }, {
	            city: 'Sevilla',
	            value: "${tweetsSevilla}"
	        }, {
	            city: 'Toledo',
	            value: "${tweetsToledo}"
	        }],
	        xkey: 'city',
	        ykeys: ['value'],
	        labels: ['Tweets', 'Tweets', 'Tweets', 'Tweets'],
	        barColors: ['#f0ad4e', '#d9534f'],
	        barRatio: 0.4,
	        xLabelAngle: 35,
	        hideHover: 'auto',
	        resize: true
	    });
    </script>

</body>

</html>