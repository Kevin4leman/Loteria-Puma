/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteria.bl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Chris
 */
public class LotoServices {
    public ArrayList<Sucursales> obtenerSucursaless() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        
        Criteria query = session.createCriteria(Sucursales.class);
        List<Sucursales> resultado = query.list();
        
        tx.commit();
        session.close();
        
        return new ArrayList<>(resultado);
    }

    public ArrayList<Sucursales> obtenerSucursaless(String buscar) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        
        Criteria query = session.createCriteria(Sucursales.class);
        query.add(Restrictions.like("nombreSucursal", buscar, MatchMode.ANYWHERE));
        List<Sucursales> resultado = query.list();
        tx.commit();
        session.close();
        
        return new ArrayList<>(resultado);
    }
    
    public String guardar(Sucursales Sucursal) { 
        String resultado = validarSucursales(Sucursal);
        
        if (resultado.equals("")) 
        {                   
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            Transaction tx = session.beginTransaction();
            
            try
            {
                session.saveOrUpdate(Sucursal);
                tx.commit();
            }
            catch (Exception ex)
            {
                tx.rollback();
                return ex.getMessage();
            }
            finally
            {
                session.close();
            }
            
        } 
        
        return resultado;
    }
    
    public void eliminar(Sucursales Sucursal) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            
            try
            {
                session.delete(Sucursal);
                tx.commit();
            }
            catch (Exception ex)
            {
                tx.rollback();
                System.out.println(ex.getMessage());
            }
            finally
            {
                session.close();
            }
    }
    

    public Sucursales Clonar(Sucursales sucur)
    {
        Sucursales clonado = new Sucursales();
        
        clonado.setCodigo(sucur.getCodigo());
        clonado.setRTN(sucur.getRTN());
        clonado.setDireccion(sucur.getDireccion());
        clonado.setResponsable(sucur.getResponsable());
        clonado.setNombreSucursal(sucur.getNombreSucursal());
        
        return clonado;
    }
    
    private String validarSucursales(Sucursales Sucursal) {
        if (Sucursal.getCodigo() < 0 ) {
            return "Ingrese Codigo";
        }
        if (Sucursal.getRTN() < 0) {
            return "ingrese RTN";
        }
        if (Sucursal.getNombreSucursal() == null || 
                Sucursal.getNombreSucursal().equals("") ) {
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
