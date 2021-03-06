<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
<head>

<%@ include file="../General/Css.jsp"%>


</head>
<body>

<!-- Left Panel -->

	<%@ include file="../General/LeftPanel.jsp"%>

    <!-- Right Panel -->

    <div id="right-panel" class="right-panel">

        <!-- Header-->
        <header id="header" class="header">
            <div class="header-menu">
                <div class="col-sm-7">
                    <a id="menuToggle" class="menutoggle pull-left"><i class="fa fa fa-tasks"></i></a>
                </div>
            </div>
        </header><!-- /header -->
        <!-- Header-->


     

        <div class="content mt-3">
            <div class="animated fadeIn">
                <div class="row">

                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong class="card-title">VENDEDORES</strong>
                        </div>


                           <div class="row">
                              <div class="col text-right">
                              </br>

                                  <button class=" btn btn-secondary" data-toggle="modal" data-target="#largeModal">CREAR NUEVO VENDEDOR</button>
                                 
                              </div>
                            </div>
                    <div class="card-body">
                    
                    <!-- Si hubo un registro exitoso muestra el mensaje-->
							    <c:if test="${not empty result}">
							    	<div class="sufee-alert alert with-close alert-success alert-dismissible fade show">
                                    	<c:out value='${result}' />
                                       	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        	<span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
							    </c:if>
							    <c:if test="${not empty wrong}">		            
		                        	<div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">				                   	
				                    		<c:out value='${wrong}' />
				                    	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
											<span aria-hidden="true">&times;</span>
				                    	</button>
				                   </div>
							    </c:if>
                  <table id="bootstrap-data-table" class="table table-striped table-bordered">
                    <thead>
                      <tr>
                      
                        <th>Nombres</th>
                        <th>Apellidos</th>
                        <th>Direccion</th>
                        <th>Telefono</th>
                        <th>Fecha De Nacimiento</th>
                        <th>Accion</th>

                      </tr>
                    </thead>
                    <tbody>
	                     <c:forEach var="vendedor" items="${vendedores}">
			                      <tr>
			                        <td>${vendedor.nombre}</td>
			                        <td>${vendedor.apellido}</td>
			                        <td>${vendedor.direccion}</td>
			                        <td>${vendedor.telefono}</td>
			                        <td>${vendedor.fechaNacimiento}</td>
			                        
			                        
			                        <td>
										<a href="${contextPath}/actualizarVendedor?codigo=${vendedor.codigo}">
			                          		<button type="button" class="btn btn-success">
			                          			<i class="fa fa-pencil"></i>&nbsp;
			                          		</button>
			                         	</a>
			                         	<a href="${contextPath}/eliminarVendedor?codigo=${vendedor.codigo}">
			                         		<button type="button" class="btn btn-danger">
			                         			<i class="fa fa-trash-o"></i>&nbsp;
			                         		</button>
										</a>
			                        </td>
					                    
			                      </tr>
	                     	</c:forEach>
                     
                    </tbody>
                  </table>
                        </div>
                    </div>
                </div>


                </div>
            </div><!-- .animated -->
        </div><!-- .content -->



    </div><!-- /#right-panel -->

<%@ include file="RegistrarVendedor.jsp" %>
    
 
<%@ include file="../General/Scripts.jsp"%>
  
    <script type="text/javascript">
        $(document).ready(function() {
          $('#bootstrap-data-table-export').DataTable();
        } );
    </script>


</body>
</html>
