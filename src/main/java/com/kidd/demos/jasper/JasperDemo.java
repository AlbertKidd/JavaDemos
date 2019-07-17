package com.kidd.demos.jasper;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kidd
 */
public class JasperDemo {

    public static void main(String[] args) throws Exception{
        testPrint("Microsoft Print to PDF");
    }

    private static JasperPrint getJasperPrint() throws JRException {
        String filePath = JasperDemo.class.getResource("/jasper/report.jrxml").getPath();
        JasperReport jasperReport = JasperCompileManager.compileReport(filePath);
        Map<String, Object> map = new HashMap<>();
        map.put("{PAGE_NUMBER}", 1);
        map.put("{PAGE_COUNT}", 1);
        return JasperFillManager.fillReport(jasperReport, map);
    }


    private static void testPrint(String printerName) throws JRException{
        JasperPrint jasperPrint = getJasperPrint();
        CustomJRPrinterAWT printerAWT = new CustomJRPrinterAWT(DefaultJasperReportsContext.getInstance(), jasperPrint, printerName);
        printerAWT.printPages(0, jasperPrint.getPages().size() - 1, false);
    }

    private static void testPreview() throws JRException{
        JasperPrint jasperPrint = getJasperPrint();
        JasperViewer jasperViewer = new CustomJasperViewer(jasperPrint, false);
        jasperViewer.setVisible(true);
    }
}
