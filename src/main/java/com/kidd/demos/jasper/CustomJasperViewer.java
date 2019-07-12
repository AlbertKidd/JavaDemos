package com.kidd.demos.jasper;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.util.ReflectionUtils;

import javax.swing.JPanel;
import java.lang.reflect.Field;

/**
 * @author Kidd
 */
public class CustomJasperViewer extends JasperViewer {

    public CustomJasperViewer(JasperPrint jasperPrint, boolean isExitOnClose) {
        super(jasperPrint, isExitOnClose);
        init(jasperPrint);
    }

    /**
     * 初始化，将原类中的JRViewer控件替换为CustomJRViewer控件
     * @param jasperPrint
     */
    private void init(JasperPrint jasperPrint){
        Field pnlMainField = ReflectionUtils.findField(JasperViewer.class, "pnlMain", JPanel.class);
        pnlMainField.setAccessible(true);
        JPanel panel = (JPanel) ReflectionUtils.getField(pnlMainField, this);
        panel.removeAll();
        panel.add(new CustomJRViewer(DefaultJasperReportsContext.getInstance(), jasperPrint, null, null));
    }

}

