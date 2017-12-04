package com.kidd.demos.swing;

import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;

/**
 * @author Kidd
 *         CreateTime 2017/8/22.
 */
public class SwingDemo {

    @Test
    public void test(){
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        JFrame jFrame = new JFrame("更新QR");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(new Dimension(600, 100));
        jFrame.setLocationRelativeTo(null);
        Container contentPane = jFrame.getContentPane();
        JProgressBar jProgressBar = new JProgressBar(JProgressBar.HORIZONTAL);
        jProgressBar.setMinimum(0);
        jProgressBar.setMaximum(100);
        jProgressBar.setValue(0);
        jProgressBar.setStringPainted(true);
        jProgressBar.setPreferredSize(new Dimension(450, 50));
        contentPane.add(jProgressBar, BorderLayout.CENTER);
        jFrame.setVisible(true);

        for (int i = 0; i < 100; i++){
            jProgressBar.setValue(i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
