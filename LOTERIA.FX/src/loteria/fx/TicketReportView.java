/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteria.fx;

import Modelo.Tbticketheader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import loteria.bl.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

/**
 *
 * @author Chris
 */
public class TicketReportView extends JFrame {
    
    public JRViewer views;
    public JasperReport report;
    Map parameters;
    public void ShowReport(Tbticketheader TicketH) throws JRException {

            parameters = new HashMap<>();
            parameters.put("HeaderId", TicketH.getTkheId());
            String file = "/loteria/fx/ReporteTicket.jasper";
            InputStream stream = getClass().getResourceAsStream(file);
            report = (JasperReport) JRLoader.loadObject(stream);
            //report.setProperty("HeaderId", Integer.toString(TicketH.getTkheId()));
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            session.doWork(new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    try {
                        JasperPrint print = JasperFillManager.fillReport(report, parameters,cnctn);
                        session.close();
                        views = new JRViewer(print);
                    } catch (JRException ex) {
                        Logger.getLogger(TicketReportView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            views.setOpaque(true);
            views.setVisible(true);
            views.setVisible(true);

            this.add(views);
            this.setSize(500, 500);
            this.setVisible(true);
    }
}
