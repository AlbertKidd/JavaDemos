package com.kidd.demos.jasper;

import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.print.JRPrinterAWT;
import org.springframework.util.ReflectionUtils;

import javax.print.PrintService;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.lang.reflect.Field;

/**
 * 自定义的JRPrinterAWT，可指定打印机名称进行打印
 *
 * @author Kidd
 */
@Log4j2
public class CustomJRPrinterAWT extends JRPrinterAWT {

    private JasperPrint jasperPrint;

    /**
     * 指定打印机名称
     */
    private String printerName;

    /**
     * 初始化
     *
     * @param jasperReportsContext
     * @param jasperPrint
     * @param printerName
     * @throws JRException
     */
    public CustomJRPrinterAWT(JasperReportsContext jasperReportsContext, JasperPrint jasperPrint, String printerName) throws JRException {
        super(jasperReportsContext, jasperPrint);
        this.printerName = printerName;
        this.jasperPrint = jasperPrint;
    }

    /**
     * 父类中的打印函数，替换其中的PrinterJob为指定类型的打印机
     * @param firstPageIndex
     * @param lastPageIndex
     * @param withPrintDialog
     * @return
     * @throws JRException
     */
    @Override
    public boolean printPages(int firstPageIndex, int lastPageIndex, boolean withPrintDialog) throws JRException {
        boolean isOK = true;

        if (
                firstPageIndex < 0 || firstPageIndex > lastPageIndex || lastPageIndex >= jasperPrint.getPages().size()
        ) {
            throw
                    new JRException(
                            EXCEPTION_MESSAGE_KEY_INVALID_PAGE_RANGE,
                            new Object[]{firstPageIndex, lastPageIndex, jasperPrint.getPages().size()}
                    );
        }

        Field pageOffsetField = ReflectionUtils.findField(JRPrinterAWT.class, "pageOffset");
        pageOffsetField.setAccessible(true);
        ReflectionUtils.setField(pageOffsetField, this, firstPageIndex);

        PrinterJob printJob = getPrinterJob();

        // fix for bug ID 6255588 from Sun bug database
        initPrinterJobFields(printJob);

        PageFormat pageFormat = printJob.defaultPage();
        Paper paper = pageFormat.getPaper();

        printJob.setJobName("JasperReports - " + jasperPrint.getName());

        switch (jasperPrint.getOrientationValue()) {
            case LANDSCAPE: {
                pageFormat.setOrientation(PageFormat.LANDSCAPE);
                paper.setSize(jasperPrint.getPageHeight(), jasperPrint.getPageWidth());
                paper.setImageableArea(
                        0,
                        0,
                        jasperPrint.getPageHeight(),
                        jasperPrint.getPageWidth()
                );
                break;
            }
            case PORTRAIT:
            default: {
                pageFormat.setOrientation(PageFormat.PORTRAIT);
                paper.setSize(jasperPrint.getPageWidth(), jasperPrint.getPageHeight());
                paper.setImageableArea(
                        0,
                        0,
                        jasperPrint.getPageWidth(),
                        jasperPrint.getPageHeight()
                );
            }
        }

        pageFormat.setPaper(paper);

        Book book = new Book();
        book.append(this, pageFormat, lastPageIndex - firstPageIndex + 1);
        printJob.setPageable(book);
        try {
            if (withPrintDialog) {
                if (printJob.printDialog()) {
                    printJob.print();
                } else {
                    isOK = false;
                }
            } else {
                printJob.print();
            }
        } catch (Exception ex) {
            throw
                    new JRException(
                            EXCEPTION_MESSAGE_KEY_ERROR_PRINTING_REPORT,
                            null,
                            ex);
        }

        return isOK;
    }

    private PrinterJob getPrinterJob(){
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        PrintService printService = getPrintService();
        if (printService != null){
            try {
                printerJob.setPrintService(printService);
            } catch (PrinterException e) {
                log.error("设置指定打印机失败", e);
            }
        }
        return printerJob;
    }

    /**
     * 获取指定名称的打印机服务
     * @return
     */
    private PrintService getPrintService() {

        if (printerName != null) {
            PrintService[] services = PrinterJob.lookupPrintServices();

            for (PrintService service : services) {
                if (service.getName().equalsIgnoreCase(printerName)) {
                    return service;
                }
            }
        }

        log.warn("未找到名称为{}的打印机", printerName);
        return null;
    }

}
