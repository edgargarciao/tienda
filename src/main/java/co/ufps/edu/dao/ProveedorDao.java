package co.ufps.edu.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import co.ufps.edu.bd.SpringDbMgr;
import co.ufps.edu.dto.Proveedor;
import co.ufps.edu.dto.Tienda;

public class ProveedorDao {
	private SpringDbMgr springDbMgr;
	
	
	public ProveedorDao(){
		springDbMgr = new SpringDbMgr();
	}
	
	public List<Proveedor> getProveedores() {
		List<Proveedor> proveedores = new ArrayList<>();
		
		SqlRowSet sqlRowSet = springDbMgr.executeQuery("select 		proveedor.codigo					codigoProveedor, "
				                                     + "            proveedor.nit_Empresa               nitProveedor, "
													 + " 	   		proveedor.nomEmpresa				nomEmpresaProveedor, "
													 + "			proveedor.nombreContacto	        nombreContactoProveedor, "
													 + " 			proveedor.telefono			        telefonoProveedor, "
													 + "			proveedor.correoElectronico         correoProveedor "
													 
												     + "from 		proveedor "
													
													 + "ORDER BY 	proveedor.codigo desc ");
		
		while(sqlRowSet.next()){
			// Creamos el proveedor
			Proveedor proveedor = new Proveedor();
			
			// Llenamos el objeto de datos
			proveedor.setCodigo(sqlRowSet.getInt("codigoProveedor"));
			proveedor.setNit_Empresa(sqlRowSet.getInt("nitProveedor"));
			proveedor.setNomEmpresa(sqlRowSet.getString("nomEmpresaProveedor"));
			proveedor.setNombreContacto(sqlRowSet.getString("nombreContactoProveedor"));
			proveedor.setTelefono(sqlRowSet.getString("telefonoProveedor"));
			proveedor.setCorreoElectronico(sqlRowSet.getString("correoProveedor"));
			
			
			// Guardamos la 
			proveedores.add(proveedor);
		}
		
		
		return proveedores;
	}
	
	
	public String registrarProveedor(Proveedor proveedor) {
	    // Agrego los datos del registro (nombreColumna/Valor)

	    MapSqlParameterSource map = new MapSqlParameterSource();
	    map.addValue("nit_Empresa", proveedor.getNit_Empresa());
	    map.addValue("nomEmpresa", proveedor.getNomEmpresa());
	    map.addValue("nombreContacto", proveedor.getNombreContacto());
	    map.addValue("telefono", proveedor.getTelefono());
	    map.addValue("correoElectronico", proveedor.getCorreoElectronico());
	    map.addValue("formaPago", proveedor.getFormaPago());

	    // Armar la sentencia de actualización debase de datos
	    String query =
	        "INSERT INTO proveedor(nit_Empresa,nomEmpresa,nombreContacto,telefono,correoElectronico,formaPago) VALUES(:nit_Empresa,:nomEmpresa,:nombreContacto,:telefono,:correoElectronico,:formaPago)";

	    // Ejecutar la sentencia
	    int result = 0;
	    try {
	      result = springDbMgr.executeDml(query, map);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    // Si hubieron filas afectadas es por que si hubo registro, en caso contrario muestra el mensaje
	    // de error.
	    return (result == 1) ? "Registro exitoso"
	        : "El proveedor no pudo ser registrado. Contacte al administrador";
	  }

    public Map<Integer,String> getlistaProveedores() {
      Map<Integer,String> proveedores = new HashMap<Integer,String>();

      SqlRowSet sqlRowSet = springDbMgr.executeQuery(   "select      proveedor.codigo               codigo, "
                                                      + "            proveedor.nomEmpresa           nombre "
                                                      + "from        proveedor "
                                                      + "ORDER BY    proveedor.codigo desc");

      while (sqlRowSet.next()) {
        proveedores.put(sqlRowSet.getInt("codigo"), sqlRowSet.getString("nombre"));
      }

      return proveedores;
    }
    
    public Proveedor obtenerProveedorPorId(long idProveedor) {
   // Lista para retornar con los datos
      Proveedor proveedor = new Proveedor();

      // Consulta para realizar en base de datos
      MapSqlParameterSource map = new MapSqlParameterSource();
      map.addValue("codigo", idProveedor);
      SqlRowSet sqlRowSet = springDbMgr.executeQuery(" SELECT * FROM proveedor WHERE codigo = :codigo", map);

      // Consulto si la categoria existe
      if (sqlRowSet.next()) {
        // Almaceno los datos de la categoria
        proveedor.setCodigo(sqlRowSet.getInt("codigo"));
        proveedor.setNomEmpresa(sqlRowSet.getString("nomEmpresa"));
        proveedor.setNombreContacto("nombreContacto");
        proveedor.setTelefono(sqlRowSet.getString("telefono"));
        proveedor.setCorreoElectronico(sqlRowSet.getString("correoElectronico"));
        proveedor.setFormaPago(sqlRowSet.getString("formaPago"));
        proveedor.setNit_Empresa(sqlRowSet.getInt("nit_Empresa"));		
      }

      // Retorna la categoria desde base de datos
      return proveedor;
    }


    public String eliminarProveedor(Proveedor proveedor) {

      // Agrego los datos de la eliminación (nombreColumna/Valor)
      MapSqlParameterSource map = new MapSqlParameterSource();
      map.addValue("codigo", proveedor.getCodigo());

      // Armar la sentencia de actualización debase de datos
      String query = "DELETE FROM proveedor WHERE codigo = :codigo";

      // Ejecutar la sentencia
      int result = 0;
      try {
        result = springDbMgr.executeDml(query, map);
      } catch (Exception e) {
        new Exception();
      }
      // Si hubieron filas afectadas es por que si hubo registro, en caso contrario muestra el mensaje
      // de error.
      return (result == 1) ? "Eliminacion exitosa"
          : "El proveedor no puede ser eliminado. Contacte al administrador.";
    }
    
    
    
    public String editarProveedor(Proveedor proveedor) {
        // Agrego los datos del registro (nombreColumna/Valor)

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("codigo", proveedor.getCodigo());
        map.addValue("nit_Empresa", proveedor.getNit_Empresa());
	    map.addValue("nomEmpresa", proveedor.getNomEmpresa());
	    map.addValue("nombreContacto", proveedor.getNombreContacto());
	    map.addValue("telefono", proveedor.getTelefono());
	    map.addValue("correoElectronico", proveedor.getCorreoElectronico());
	    map.addValue("formaPago", proveedor.getFormaPago());
        
        
        // Armar la sentencia de actualización debase de datos
        String query =
            "UPDATE proveedor SET nit_Empresa = :nit_Empresa, nomEmpresa = :nomEmpresa , nombreContacto = :nombreContacto, telefono = :telefono, correoElectronico = :correoElectronico, formaPago = :formaPago  where codigo = :codigo ";

        // Ejecutar la sentencia
        int result = 0;
        try {
          result = springDbMgr.executeDml(query, map);
        } catch (Exception e) {
          e.printStackTrace();
        }
        // Si hubieron filas afectadas es por que si hubo registro, en caso contrario muestra el mensaje
        // de error.
        return (result == 1) ? "Actualizacion exitosa"
            : "El proveedor no pudo ser actualizado. Contacte al administrador";
        // Si hubieron filas afectadas es por que si hubo registro, en caso contrario muestra el mensaje
        // de error.

      }

}
