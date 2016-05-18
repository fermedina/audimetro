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

    <title>SB Admin - Bootstrap Admin Template</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/style.css" rel="stylesheet">

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
                    <c:if test="${not empty searchList}">
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
                    </c:if>
                    <c:if test="${not empty hashtags}">
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
                    <li class="active">
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
                            Configuración de búsqueda
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-list-ol"></i>  <a href="index">Hashtags</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-edit"></i> Configuración de búsqueda
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

                <div class="row add-hashtag">
                    <form id="target" action="/index" method="post" acceptcharset="utf-8">
                    	<div class="col-lg-12">
                            <label>Introduzca un identificador de búsqueda</label>
                        </div>
                        
                        <div class="col-lg-6">
                            <div class="form-group input-group">
                                <span class="input-group-addon"><i class="fa fa-search"></i></span>
                                <input type="text" name="busqueda" class="form-control" required placeholder="Identificador de búsqueda">
                            </div>
                        </div>
                        
                        <div class="col-lg-12">
                            <label>Información del cliente</label>
                        </div>
                        
                        <div class="col-lg-4">
                            <div class="form-group input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input type="text" name="name" class="form-control" required placeholder="Nombre">
                            </div>
                        </div>
                        
                        <div class="col-lg-4">
                            <div class="form-group input-group">
                                <span class="input-group-addon"><i class="fa fa-briefcase"></i></span>
                                <input type="text" name="cif" class="form-control" required placeholder="CIF">
                            </div>
                        </div>
                    
                        <div class="col-lg-12">
                            <label>Introduzca al menos un Hashtag a monitorizar</label>
                        </div>
                        
                        <div class="col-lg-12">
	                        <div class="col-lg-5 program">
	                        	<div class="form-group titulo"><i class="fa fa-hashtag"></i> Hashtag 1</div>
	                        	<div class="form-group input-group">
	                                <span class="input-group-addon"><i class="fa fa-television"></i></span>
	                                <input type="text" name="program1" class="form-control" required placeholder="Programa">
	                            </div>
	                            <div class="form-group input-group">
	                                <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
	                                <input type="text" name="hashtag1" class="form-control" required placeholder="Hashtag">
	                            </div>
	                        </div>
	
	                        <div class="col-lg-5 program">
	                        	<div class="form-group titulo"><i class="fa fa-hashtag"></i> Hashtag 2</div>
	                        	<div class="form-group input-group">
	                                <span class="input-group-addon"><i class="fa fa-television"></i></span>
	                                <input type="text" name="program2" class="form-control" placeholder="Programa">
	                            </div>
	                            <div class="form-group input-group">
	                                <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
	                                <input type="text" name="hashtag2" class="form-control" placeholder="Hashtag">
	                            </div>
	                        </div>
	
	                        <div class="col-lg-5 program">
	                        	<div class="form-group titulo"><i class="fa fa-hashtag"></i> Hashtag 3</div>
	                        	<div class="form-group input-group">
	                                <span class="input-group-addon"><i class="fa fa-television"></i></span>
	                                <input type="text" name="program3" class="form-control" placeholder="Programa">
	                            </div>
	                            <div class="form-group input-group">
	                                <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
	                                <input type="text" name="hashtag3" class="form-control" placeholder="Hashtag">
	                            </div>
	                        </div>
	
	                        <div class="col-lg-5 program">
	                        <div class="form-group titulo"><i class="fa fa-hashtag"></i> Hashtag 4</div>
	                        	<div class="form-group input-group">
	                                <span class="input-group-addon"><i class="fa fa-television"></i></span>
	                                <input type="text" name="program4" class="form-control" placeholder="Programa">
	                            </div>
	                            <div class="form-group input-group">
	                                <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
	                                <input type="text" name="hashtag4" class="form-control" placeholder="Hashtag">
	                            </div>
	                        </div>
                        </div>

                        <div class="col-lg-6 franja">

                            <div class="form-group">
                                <label>Búsqueda programada, seleccione la franja horaria</label>
                                <select class="form-control" id="select_franja" name="franja_horaria">
                                    <option></option>
                                    <option value="despertador">Despertador: 7:30 a las 9 h</option>
                                    <option value="matinal">Matinal: 9 a 13 h</option>
                                    <option value="access_sobremesa">Access Sobremesa: 13 a 15 h</option>
                                    <option value="sobremesa">Sobremesa: 15 a 18 h</option>
                                    <option value="tarde">Tarde: 18 a 20 h</option>
                                    <option value="access_prime_time">Access Prime-Time: 20 a 21 h</option>
                                    <option value="prime_time">Prime-Time: 21 a 24 h</option>
                                    <option value="latenight">Latenight : 24 a 2:30 h</option>
                                    <option value="madrugada">Madrugada: 2:30 a 7:30 h</option>
                                </select>
                            </div>
                            
                            <div class="col-lg-4 franja_horas" style="display: none">
                            	<div class="form-group">
	                                <div class="form-group input-group">
	                                    <span class="input-group-addon"><i class="fa fa-clock-o"></i> Inicio</span>
	                                    <input type="datetime-local" id="fecha_inicio" name="fecha_inicio" value="" class="form-control">
	                                </div>
	                            </div>
	                            
	                            <div class="form-group">
	                                <div class="form-group input-group">
	                                    <span class="input-group-addon"><i class="fa fa-clock-o"></i> Fin</span>
	                                    <input type="datetime-local" id="fecha_fin" name="fecha_fin" value="" class="form-control">
	                                </div>
	                            </div>
                            </div>
                            
                        </div>
                        <div class="col-lg-6 franja">
                            <label>Búsqueda inmediata, seleccione la hora de fin <span style="color: #9F6000" title="En Safari o Firefox utilizar el formato YYYY-MM-DDTHH:mm">?</span></label>
                        </div>

                        <div class="col-lg-2">
                            <div class="form-group">
                                <div class="form-group input-group">
                                    <span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
                                    <input type="datetime-local" id="fin" name="hora_fin" value="" class="form-control">
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-lg-12">
                            <div class="form-group">
                                <button type="submit" class="btn btn-success">Guardar</button>
                            </div>
                        </div>
                    </form>
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
    
    <script type="text/javascript">
	    var today = new Date();
	    var dd = today.getDate();
	    var mm = today.getMonth()+1; //January is 0!
	    var yyyy = today.getFullYear();
	
	    if(dd<10) {
	        dd='0'+dd
	    } 
	
	    if(mm<10) {
	        mm='0'+mm
	    } 
	
	    today = yyyy+'-'+mm+'-'+dd;
	    
	    var select = {
	    	"despertador": ["07:30", "09:00"], 
	    	"matinal": ["09:00", "13:00"], 
	    	"access_sobremesa": ["13:00", "15:00"], 
	    	"sobremesa": ["15:00", "18:00"], 
	    	"tarde": ["18:00", "20:00"], 
	    	"access_prime_time": ["20:00", "21:00"], 
	    	"prime_time": ["21:00", "23:59"], 
	    	"latenight": ["00:00", "02:30"], 
	    	"madrugada": ["02:30", "07:30"]
	    };
	    
    	$("#select_franja").change(function() {
    		var franja = $("#select_franja").val();
    		
    		if (franja) {
        		$(".franja_horas").show();
        		$("#fecha_inicio").val(today+"T"+select[franja][0]);
        		$("#fecha_fin").val(today+"T"+select[franja][1]);
        	} else {
        		$(".franja_horas").hide();
        	}
   		});
    
    </script>
    
    <script type="text/javascript">
    	$("#target").submit(function(event) {
    		if($("#fin").val() && $("#select_franja").val()) {
    			alert("Por favor elija sólo un método para iniciar la búsqueda");
    			event.preventDefault();
    		}
    		
    		if(!$("#fin").val() && !$("#select_franja").val()) {
    			alert("Por favor elija al menos un método para iniciar la búsqueda");
    			event.preventDefault();
    		}
    	});
    </script>

</body>

</html>