/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteria.bl;

import java.util.ArrayList;

/**
 *
 * @author Chris
 */
public class LotoServices {
    
      private final ArrayList<Sucursales> listadeSucursales;
    
    public LotoServices() {
        listadeSucursales = new ArrayList<>();
    }

    public ArrayList<Sucursales> obtenerSucursaless() {
        return listadeSucursales;
    }

    public ArrayList<Sucursales> obtenerSucursaless(String buscar) {
        
        if (buscar == null && buscar.equals("")) {
            return listadeSucursales;
        }
        
        String buscarMinuscula = buscar.toLowerCase();
        ArrayList<Sucursales> resultado = new ArrayList<>();
        
        listadeSucursales.forEach(Sucursal -> {
            if (Sucursal.getNombre().toLowerCase().contains(buscar) == true) {
                resultado.add(Sucursal);
            }
        });
        
        return resultado;
    }
    
    public String guardar(Sucursales Sucursal) { 
        String resultado = validarSucursales(Sucursal);
        
        if (resultado.equals("")) {                   

            if (Sucursal.getCodigo() == 0) {
                Integer id = obtenerSiguienteId();

                Sucursal.setCodigo(id);

                listadeSucursales.add(Sucursal);
            } else {
                listadeSucursales.forEach(SucursalExistente -> {
                    if (SucursalExistente.getCodigo() == Sucursal.getCodigo()) 
                    {
                        SucursalExistente.setDireccion(Sucursal.getDireccion());
                        SucursalExistente.setRTN(Sucursal.getRTN());
                        SucursalExistente.setNombre(Sucursal.getNombre());
                        SucursalExistente.setResponsable(Sucursal.getResponsable());
                    }            
                });
            }
            return "";
        } 
        
        return resultado;
    }
    
    public void eliminar(Sucursales Sucursal) {
        listadeSucursales.remove(Sucursal);
    }
    

    private Integer obtenerSiguienteId() {
        Integer maxId = 1;
        for(Sucursales Sucursal: listadeSucursales) {
            if (Sucursal.getCodigo()>= maxId) {
                maxId = Sucursal.getCodigo()+ 1;
            }
        }
        return maxId;
    }

    private String validarSucursales(Sucursales Sucursal) {
        if (Sucursal.getCodigo() < 0 ) {
            return "Ingrese Codigo";
        }
        if (Sucursal.getRTN() < 0) {
            return "ingrese RTN";
        }
        if (Sucursal.getNombre() == null || 
                Sucursal.getNombre().equals("") ) {
            return "Ingrese un Nombre";
        }
        if (Sucursal.getDireccion() == null || 
                Sucursal.getDireccion().equals("")) {
            return "Ingrese una direccion";
        }
        
        if (Sucursal.getResponsable()== null || 
                Sucursal.getResponsable().equals("")) {
            return "Ingrese una repsonsable";
        }
        
        return "";
    }
}
