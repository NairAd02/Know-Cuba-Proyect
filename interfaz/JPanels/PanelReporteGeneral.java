package JPanels;

import java.awt.EventQueue;
import java.sql.DriverManager;
import java.util.HashMap;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.Map;

import javax.sql.DataSource;


import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JInternalFrame;

import utils.ConnectionDataBase;



public class PanelReporteGeneral extends JInternalFrame {
	

	public PanelReporteGeneral(String reportPath, HashMap hm) {
		setBounds(100, 100, 450, 300);
		
		try {
			Class.forName("org.postgresql.Driver");
			JasperReport masterReport = (JasperReport)JRLoader.loadObjectFromFile(reportPath);
			JasperPrint print = JasperFillManager.fillReport(masterReport,hm, ConnectionDataBase.getConnectionDataBase());
			JasperViewer.viewReport(print, false);
		} catch (Exception e) {
			e.printStackTrace();

			System.exit(0);
		}
		
	}
	public PanelReporteGeneral(String reportPath, String subreportPath, HashMap hm) {
		setBounds(100, 100, 450, 300);
		
		try {
			Class.forName("org.postgresql.Driver");
			JasperReport masterReport = (JasperReport)JRLoader.loadObjectFromFile(reportPath);
			JasperReport jasperSubReport = (JasperReport)JRLoader.loadObjectFromFile(subreportPath);
			hm.put("subreportParameter", jasperSubReport);
			System.out.println("Llegue");
			JasperPrint print = JasperFillManager.fillReport(masterReport, hm, ConnectionDataBase.getConnectionDataBase());
			JasperViewer.viewReport(print, false);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	public PanelReporteGeneral(String reportPath, String subreportPath,String subreportPath2, HashMap hm,HashMap hm2) {
		setBounds(100, 100, 450, 300);
		
		try {
			Class.forName("org.postgresql.Driver");
			JasperReport masterReport = (JasperReport)JRLoader.loadObjectFromFile(reportPath);
			JasperReport jasperSubReport = (JasperReport)JRLoader.loadObjectFromFile(subreportPath);
			JasperReport jasperSubReport2 = (JasperReport)JRLoader.loadObjectFromFile(subreportPath2);
			//JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("G:/Escuela/Know-Cuba-Proyect/Reportes/Listado de contratos de servicios "));
			hm2.put("subreportParameter", jasperSubReport2);
			JasperPrint print2 = JasperFillManager.fillReport(jasperSubReport, hm2, ConnectionDataBase.getConnectionDataBase());
			hm.put("subreportParameter", jasperSubReport);
			JasperPrint print = JasperFillManager.fillReport(masterReport, hm, ConnectionDataBase.getConnectionDataBase());
			JasperViewer.viewReport(print, false);
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println(jRException.getMessage());
			System.exit(0);
		}
		
	}
	
	public PanelReporteGeneral(String path) {
		setBounds(100, 100, 450, 300);
		
		try {
			Class.forName("org.postgresql.Driver");
			JasperReport masterReport = (JasperReport)JRLoader.loadObjectFromFile(path);
			JasperPrint print = JasperFillManager.fillReport(masterReport,null, ConnectionDataBase.getConnectionDataBase());
			JasperViewer.viewReport(print, false);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}

}
