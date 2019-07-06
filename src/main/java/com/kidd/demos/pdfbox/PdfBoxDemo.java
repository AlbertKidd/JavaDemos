package com.kidd.demos.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPrintable;

import java.awt.print.PrinterJob;
import java.io.File;

/**
 * @author Kidd
 */
public class PdfBoxDemo {

    public static void main(String[] args) throws Exception{
        PDDocument document = PDDocument.load(new File("E:\\OneDrive\\Books\\Android\\Android编程权威指南.pdf"));
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(new PDFPrintable(document));
        printerJob.print();
    }
}
