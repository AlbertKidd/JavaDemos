package com.kidd.demos.spring.controllers;

import com.vladsch.flexmark.ast.Document;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

/**
 * @author Kidd
 * CreateTime 2018/2/2.
 */
@Log4j2
@RestController
public class MarkdownController {

    @RequestMapping("/md/{fileId:.+}")
    public String mdToHtml(@PathVariable String fileId){
        try {
            String path = StringUtils.replace(fileId, ".", "/") + ".md";
            File file = new File(path);
            String fileContent = FileUtils.readFileToString(file, "utf-8");
            Parser parser = Parser.builder().build();
            Document doc = parser.parse(fileContent);

            HtmlRenderer renderer = HtmlRenderer.builder().build();
            return renderer.render(doc);
        } catch (IOException e) {
            throw new RuntimeException(fileId + "文件不存在", e);
        }
    }
}
