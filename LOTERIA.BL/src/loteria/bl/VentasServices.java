/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteria.bl;

import Modelo.Tbjuegos;
import Modelo.Tbjugadadetalle;
import Modelo.Tbsorteos;
import Modelo.Tbsorteosresultados;
import Modelo.Tbticketheader;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Chris
 */
public class VentasServices {
    
    public ArrayList<Tbjuegos> ObtenerJuegos()
    {
    Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        
        Criteria query = session.createCriteria(Tbjuegos.class);
        List<Tbjuegos> resultado = query.list();
        
        tx.commit();
        session.close();
        
        return new ArrayList<>(resultado);
    }
    
    public String GenerarSerie(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "0123456789"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
            int index = (int)(AlphaNumericString.length() * Math.random()); 

            sb.append(AlphaNumericString.charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
    
    public Tbsorteos GetLastSorteo(Tbjuegos jgo)
    {
    Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        System.out.println(jgo.getJgoId());
        Criteria query = session.createCriteria(Tbsorteos.class);
        query.add(Restrictions.eq("tbjuegos",jgo));
        query.addOrder(Order.desc("sortId"));
        
        Tbsorteos resultado = (Tbsorteos)query.list().get(0);
        
        tx.commit();
        session.close();
        
        return resultado;
    }
    
    public boolean CheckIfSorteoIsValid(Tbsorteos sorteo)
    {
        boolean Valido = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        
        Criteria query = session.createCriteria(Tbsorteosresultados.class).add(Restrictions.eq("sort_Id", sorteo.getSortId()));
        List<Tbsorteosresultados> resultado = query.list();
        
        tx.commit();
        session.close();
        
        if(resultado.isEmpty())
        {
            Valido = true;
        }
        
        return Valido;
    }
    
    public String JugadaToString(ArrayList<Tbjugadadetalle> Jugada)
    {
        String Retorno = "";
        for(Tbjugadadetalle jda : Jugada)
        {
            Retorno += jda.getJdetNumero() + " ";
        }
        return Retorno;
    }
    
    
//    public int tbTicketHeaderInsert(Tbticketheader Thead)
//    {
//        
//    }
//    
//    public int tbJuegadaInsert(Tbjuegos Jgo)
//    {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = session.beginTransaction();
//        
//        try
//        {
//            session.createSQLQuery("INSERT INTO tbJudada");
//        }
//        catch(Exception ex)
//        {
//        
//        }
//        finally
//        {
//        
//        }
//    }
    
}
