package com.kidd.demos.awt;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * @author Kidd
 */
public class SystemTrayDemo {

    public static void main(String[] args)
    {
        // 判断是否支持系统托盘
        if (SystemTray.isSupported())
        {
            // 获取图片所在的URL
            URL url = SystemTrayDemo.class.getResource("/icon.png");
            // 实例化图像对象
            ImageIcon icon = new ImageIcon(url);
            // 获得Image对象
            Image image = icon.getImage();
            // 创建托盘图标
            TrayIcon trayIcon = new TrayIcon(image);
            // 为托盘添加鼠标适配器
            trayIcon.addMouseListener(new MouseAdapter()
            {
                // 鼠标事件
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    // 判断是否双击了鼠标
                    if (e.getClickCount() == 2)
                    {
                        JOptionPane.showMessageDialog(null, "这是双击后的弹窗");
                    }
                }
            });
            // 添加工具提示文本
            trayIcon.setToolTip("这里是鼠标聚焦时显示的文字\nHello, Kidd");

            // 创建弹出菜单
            PopupMenu popupMenu = new PopupMenu();
            popupMenu.add(new MenuItem("菜单A-1"));
            popupMenu.add(new MenuItem("菜单A-2"));
            popupMenu.add(new MenuItem("菜单A-3"));
            popupMenu.addSeparator();
            popupMenu.add(new MenuItem("菜单B"));
            popupMenu.addSeparator();
            popupMenu.add(new MenuItem("菜单C"));
            // 为托盘图标加弹出菜弹
            trayIcon.setPopupMenu(popupMenu);

            // 获得系统托盘对象
            SystemTray systemTray = SystemTray.getSystemTray();
            try
            {
                // 为系统托盘加托盘图标
                systemTray.add(trayIcon);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "当前系统不支持应用图标托盘");
        }
    }

}
