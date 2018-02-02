package com.kidd.demos.others;

import com.vladsch.flexmark.ast.Document;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.FileReader;

/**
 * @author Kidd
 * CreateTime 2018/1/31.
 */
public class FlexMarkDemo {

    @Test
    public void test() throws Exception{
        Parser parser = Parser.builder().build();
        HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();

        String s = IOUtils.toString(new FileReader("README.md"));
        Document document = parser.parse(s);
        String render = htmlRenderer.render(document);
        System.out.print(render);
    }
}
