package JPanels;

import java.awt.EventQueue;

import java.sql.DriverManager;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JInternalFrame;

import utils.ConnectionDataBase;

public class PanelReporteGeneral extends JInternalFrame {

	public PanelReporteGeneral(String path, HashMap hm) {
		setBounds(100, 100, 450, 300);
		
		try {
			Class.forName("org.postgresql.Driver");
			JasperReport report = (JasperReport)JRLoader.loadObjectFromFile(path);
			JasperPrint print = JasperFillManager.fillReport(report,hm, ConnectionDataBase.getConnectionDataBase());
			JasperViewer.viewReport(print, false);
			//getContentPane().add(new JRViewer(print));
			//System.out.println("Abrio");
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println(jRException.getMessage());
			System.exit(0);
		}
		
	}

}
