package com.kidd.demos.jasper;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.swing.JRViewerToolbar;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Kidd
 */
public class CustomJRViewer extends JRViewer {

    public CustomJRViewer(JasperReportsContext jasperReportsContext, JasperPrint jrPrint, Locale locale,
                              ResourceBundle resBundle){
        super(jasperReportsContext, jrPrint, locale, resBundle);
    }

    @Override
    protected JRViewerToolbar createToolbar() {
        return new CustomJRViewerToolbar(viewerContext);
    }
}
