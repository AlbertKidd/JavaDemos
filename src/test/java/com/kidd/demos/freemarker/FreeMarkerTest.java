package com.kidd.demos.freemarker;

import com.itextpdf.text.pdf.BaseFont;
import com.kidd.demos.model.Person;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.testng.annotations.Test;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author Kidd
 */
public class FreeMarkerTest {

    @Test
    public void test() throws Exception{
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setClassForTemplateLoading(FreeMarkerTest.class, "/freemarker");
        configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Person person = new Person();
        person.setName("kidd");
        person.setAge(18);
        person.setAddress("xian");

        Template template = configuration.getTemplate("demo.ftl");
        StringWriter stringWriter = new StringWriter();
        template.process(person, stringWriter);
        String html = stringWriter.toString();

        generatePdf(html);
    }

    private void generatePdf(String content) throws Exception{
        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("C:/Windows/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.setDocumentFromString(content, "file:///D:/demos/out/production/resources/freemarker/");
        renderer.setScaleToFit(true);
        renderer.layout();
        renderer.createPDF(new FileOutputStream("demo.pdf"));
    }

}
