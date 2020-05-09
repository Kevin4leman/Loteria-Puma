/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteria.bl;

import Modelo.Tbjuegos;
import Modelo.Tbsorteos;
import Modelo.Tbusuarios;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Chris
 */
public class LoginServices {
    
    public Tbusuarios Verifyuser(String Username, String Password)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        Criteria query = session.createCriteria(Tbusuarios.class);
        query.add(Restrictions.eq("userName",Username));
        query.add(Restrictions.eq("userPassword",Password));
        Tbusuarios resultado = (Tbusuarios)query.list().get(0);
        resultado.getTbsucursales();
        tx.commit();
        session.close();
        
        return resultado;
    }
}
