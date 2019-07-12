package com.kidd.demos.jasper;

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
        String filePath = JasperDemo.class.getResource("/jasper/report.jrxml").getPath();
        JasperReport jasperReport = JasperCompileManager.compileReport(filePath);
        Map<String, Object> map = new HashMap<>();
        map.put("{PAGE_NUMBER}", 1);
        map.put("{PAGE_COUNT}", 1);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map);
        JasperViewer jasperViewer = new CustomJasperViewer(jasperPrint, false);
        jasperViewer.setVisible(true);
        for (;;){
            System.out.println("I'm running");
            Thread.sleep(2 * 1000);
        }
    }
}
