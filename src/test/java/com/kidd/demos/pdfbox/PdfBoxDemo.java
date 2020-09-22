package com.kidd.demos.pdfbox;

import com.itextpdf.text.pdf.PdfDocument;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * @author Kidd
 */
public class PdfBoxDemo {

    public void readPdf(){

    }

    public void writePdf(){

    }

    public void word2pdf(){

    }

    @Test
    public void mergePdfFiles() throws Exception{
        File pdfFolder = new File("C:\\Users\\hasee\\Desktop\\pdfs");
        File pdfFile1 = new File(pdfFolder, "1.pdf");
        File pdfFile2 = new File(pdfFolder, "2.pdf");
        File pdfFile3 = new File(pdfFolder, "3.pdf");

        if (pdfFile3.exists()){
            pdfFile3.delete();
        }
        assert !pdfFile3.exists();

        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
        pdfMergerUtility.addSource(pdfFile1);
        pdfMergerUtility.addSource(pdfFile2);
        pdfMergerUtility.setDestinationFileName(pdfFile3.getAbsolutePath());
        pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
        assert pdfFile3.exists();
    }
}
