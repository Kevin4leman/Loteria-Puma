/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteria.bl;

import Modelo.Tbjuegos;
import Modelo.Tbjugada;
import Modelo.Tbjugadadetalle;
import Modelo.Tbsorteos;
import Modelo.Tbsucursales;
import Modelo.Tbticketheader;
import java.math.BigInteger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Chris
 */
public class TableActions {
    
    //TbSorteos Functions
    public int TbSorteosInsert(Tbsorteos Sort)
    {
        int ReturnVal = -1;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        try
        {
            session.save(Sort);
            tx.commit();
            ReturnVal = ((BigInteger)session.createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).intValue();
        }
        catch(Exception ex)
        {
            tx.rollback();
            System.out.println(ex.getMessage());
        }
        finally
        {
         session.close();
        }
        return ReturnVal;
    }
    
    //TBTicketHeader Functions
    public int TbTicketHeaderInsert(Tbticketheader Thead)
    {
        int ReturnVal = -1;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        try
        {
            session.save(Thead);
            tx.commit();
            ReturnVal = ((BigInteger)session.createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).intValue();
        }
        catch(Exception ex)
        {
            tx.rollback();
            System.out.println(ex.getMessage());
        }
        finally
        {
         session.close();
        }
        return ReturnVal;
    }
    
    //TbSorteos Functions
    public int TbJuagadaInsert(Tbjugada jda)
    {
        int ReturnVal = -1;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        try
        {
            session.save(jda);
            tx.commit();
            ReturnVal = ((BigInteger)session.createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).intValue();
        }
        catch(Exception ex)
        {
            tx.rollback();
            System.out.println(ex.getMessage());
        }
        finally
        {
         session.close();
        }
        return ReturnVal;
    }
    
    //TbSorteos Functions
    public int TbJugadaDetalleInsert(Tbjugadadetalle jdet)
    {
        int ReturnVal = -1;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        try
        {
            session.save(jdet);
            tx.commit();
            ReturnVal = ((BigInteger)session.createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).intValue();
        }
        catch(Exception ex)
        {
            tx.rollback();
            System.out.println(ex.getMessage());
        }
        finally
        {
         session.close();
        }
        return ReturnVal;
    }
    
    public int TbJuegosInsert(Tbjuegos jgo)
    {
        int ReturnVal = -1;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        try
        {
            session.save(jgo);
            tx.commit();
            ReturnVal = ((BigInteger)session.createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).intValue();
        }
        catch(Exception ex)
        {
            tx.rollback();
            System.out.println(ex.getMessage());
        }
        finally
        {
         session.close();
        }
        return ReturnVal;
    }
}
