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
                    <c:if test="${not empty insertedTweets}">
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
                    </c:if>
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
                        <h1 class="page-header">
                            Hashtags <small>monitorizados</small>
                        </h1>
   
                        <ol class="breadcrumb">
                            <li class="active">
                                <i class="fa fa-list"></i> Hashtags
                            </li>
                        </ol>
                        
                        <div class="panel panel-purple">
					    	<div class="panel-heading"><i class="fa fa-info-circle"></i> Información de Búsqueda</div>
					    	<div class="panel-body">
					    		<div class="col-lg-4 info_client">
						    		<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"><i class="fa fa-search"></i> Identificador</span>
									  	<input type="text" class="form-control" disabled value="${busqueda.nombre}" aria-describedby="basic-addon1">
									</div>
								</div>
								
					    		<div class="col-lg-4 info_client">
						    		<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"><i class="fa fa-user"></i> Cliente</span>
									  	<input type="text" class="form-control" disabled value="${busqueda.nombreCliente}" aria-describedby="basic-addon1">
									</div>
								</div>
								
								<div class="col-lg-4 info_client">
						    		<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"><i class="fa fa-briefcase"></i> CIF</span>
									  	<input type="text" class="form-control" disabled value="${busqueda.cif}" aria-describedby="basic-addon1">
									</div>
								</div>
								
								<div class="col-lg-6 info_client">
						    		<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"><i class="fa fa-clock-o"></i> Fecha inicio</span>
									  	<input type="text" class="form-control" disabled value="${hashtag1.fechaInicio}" aria-describedby="basic-addon1">
									</div>
								</div>
								
								<div class="col-lg-6 info_client">
						    		<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"><i class="fa fa-clock-o"></i> Fecha fin</span>
									  	<input type="text" class="form-control" disabled value="${hashtag1.fechaFin}" aria-describedby="basic-addon1">
									</div>
								</div>
								
								<!--<div class="col-lg-6 info_client">
									<span class="titulo">Obtener enlace de monitorización:</span>
						    		<button type="button" class="btn btn-info">Enlace</button>
								</div>-->
					  		</div>
						</div>
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
                                    <span class="program_name"><c:out value="${hashtag1.programa}" /></span>
                                </div>
                            </div>
                            <c:if test="${not empty insertedTweets}">
	                            <a href="hashtagDetail?id=${hashtag1.id}">
	                                <div class="panel-footer">
	                                    <span class="pull-left">Ver detalles</span>
	                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
	                                    <div class="clearfix"></div>
	                                </div>
	                            </a>
	                        </c:if>
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
	                                    <span class="program_name"><c:out value="${hashtag2.programa}" /></span>
	                                </div>
	                            </div>
	                            <c:if test="${not empty insertedTweets}">
		                            <a href="hashtagDetail?id=${hashtag2.id}">
		                                <div class="panel-footer">
		                                    <span class="pull-left">Ver detalles</span>
		                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
		                                    <div class="clearfix"></div>
		                                </div>
		                            </a>
		                        </c:if>    
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
	                                    <span class="program_name"><c:out value="${hashtag3.programa}" /></span>
	                                </div>
	                            </div>
	                            <c:if test="${not empty insertedTweets}">
		                            <a href="hashtagDetail?id=${hashtag3.id}">
		                                <div class="panel-footer">
		                                    <span class="pull-left">Ver detalles</span>
		                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
		                                    <div class="clearfix"></div>
		                                </div>
		                            </a>
		                        </c:if>    
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
	                                    <span class="program_name"><c:out value="${hashtag4.programa}" /></span>
	                                </div>
	                            </div>
	                            <c:if test="${not empty insertedTweets}">
		                            <a href="hashtagDetail?id=${hashtag4.id}">
		                                <div class="panel-footer">
		                                    <span class="pull-left">Ver detalles</span>
		                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
		                                    <div class="clearfix"></div>
		                                </div>
		                            </a>
		                        </c:if>
	                        </div>
	                    </div>
	            	</c:if>
                </div>
                <c:if test="${not empty insertedTweets}">            
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
                </c:if>
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
    	var data = [];
    	var tweetsCount = [];
    	var hashtagNames = [];
	    <c:forEach items="${tweetsCount}" var="i">
	    	tweetsCount.push("${i}");
		</c:forEach>
		<c:forEach items="${hashtagNames}" var="i">
			hashtagNames.push("${i}");
		</c:forEach>
		
		for(var i = 0; i < tweetsCount.length; i++) {
			if (hashtagNames[i] != "") {
				data.push({
			    	value: tweetsCount[i],
			        label: "#" + hashtagNames[i]
			    });
			}	
        }
    
	    Morris.Donut({
	        element: 'morris-donut-chart',
	        data: data,
	        resize: true,
	        colors: ['#337AB7', '#088A08', '#FF8000', '#d9534f']
	    });
    </script>
</body>
</html>
