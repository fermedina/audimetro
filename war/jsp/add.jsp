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
                    <c:if test="${not empty hashtags}">
						<li>
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
					</c:if>
                    <li class="active">
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
                            Añadir Hashtags
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-list-ol"></i>  <a href="index">Hashtags</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-edit"></i> Añadir Hashtags
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

                <div class="row add-hashtag">
                    <form action="/index" method="post" acceptcharset="utf-8">
                        <div class="col-lg-12">
                            <label>Seleccione cuatro Hashtag a monitorizar</label>
                        </div>
                        
                        <div class="col-lg-6">
                            <div class="form-group input-group">
                                <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                <input type="text" name="hashtag1" class="form-control" required placeholder="Hashtag">
                            </div>
                        </div>

                        <div class="col-lg-6">
                            <div class="form-group input-group">
                                <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                <input type="text" name="hashtag2" class="form-control" required placeholder="Hashtag">
                            </div>
                        </div>

                        <div class="col-lg-6">
                            <div class="form-group input-group">
                                <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                <input type="text" name="hashtag3" class="form-control" required placeholder="Hashtag">
                            </div>
                        </div>

                        <div class="col-lg-6">
                            <div class="form-group input-group">
                                <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                <input type="text" name="hashtag4" class="form-control" required placeholder="Hashtag">
                            </div>
                        </div>

                        <div class="col-lg-6 franja">

                            <div class="form-group">
                                <label>Seleccione la franja horaria</label>
                                <select class="form-control">
                                    <option></option>
                                    <option>Despertador: 7:30 a las 9 h</option>
                                    <option>Matinal: 9 a 13 h</option>
                                    <option>Access Sobremesa: 13 a 15 h</option>
                                    <option>Sobremesa: 15 a 18 h</option>
                                    <option>Tarde: 18 a 20 h</option>
                                    <option>Access Prime-Time: 20 a 21 h</option>
                                    <option>Prime-Time: 21 a 24 h</option>
                                    <option>Latenight : 24 a 2:30 h</option>
                                    <option>Madrugada: 2:30 a 7:30 h</option>
                                </select>
                            </div>

                            <button type="submit" disabled class="btn btn-success">Guardar</button>

                        </div>
                        <div class="col-lg-6 franja">
                            <label>Seleccione la hora de fin</label>
                        </div>

                        <div class="col-lg-2">
                            <div class="form-group">
                                <div class="form-group input-group">
                                    <span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
                                    <input type="datetime-local" name="hora_fin" required value="" class="form-control">
                                </div>
                            </div>

                            <button type="submit" class="btn btn-info">Activar</button>
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

</body>

</html>