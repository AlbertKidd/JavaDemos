package com.kidd.demos.jasper;

import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.swing.JRViewerController;
import net.sf.jasperreports.swing.JRViewerToolbar;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Kidd
 */
public class CustomJRViewerToolbar extends JRViewerToolbar {

    public CustomJRViewerToolbar(JRViewerController viewerContext) {
        super(viewerContext);
        initialize();
    }

    public void initialize() {
        ActionListener[] actionListeners = btnPrint.getActionListeners();
        for (ActionListener actionListener : actionListeners) {
            btnPrint.removeActionListener(actionListener);
        }
        btnPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPrintButtonClick(e);
            }
        });
    }

    protected void onPrintButtonClick(ActionEvent e) {
        Thread thread =
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        btnPrint.setEnabled(false);
                        JasperPrintManager.getInstance(viewerContext.getJasperReportsContext()).print(viewerContext.getJasperPrint(), true);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(CustomJRViewerToolbar.this,
                                viewerContext.getBundleString("error.printing"));
                    } finally {
                        btnPrint.setEnabled(true);
                    }
                }
            }
            );

        thread.start();
    }

}
