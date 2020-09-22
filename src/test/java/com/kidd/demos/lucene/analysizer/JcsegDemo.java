package com.kidd.demos.lucene.analysizer;

import org.lionsoul.jcseg.analyzer.JcsegAnalyzer;
import org.lionsoul.jcseg.tokenizer.core.ADictionary;
import org.lionsoul.jcseg.tokenizer.core.DictionaryFactory;
import org.lionsoul.jcseg.tokenizer.core.ISegment;
import org.lionsoul.jcseg.tokenizer.core.IWord;
import org.lionsoul.jcseg.tokenizer.core.JcsegTaskConfig;
import org.lionsoul.jcseg.tokenizer.core.SegmentFactory;
import org.testng.annotations.Test;

import java.io.StringReader;

/**
 * @author Kidd
 */
public class JcsegDemo {

    @Test
    public void testToken() throws Exception{

        JcsegTaskConfig jcsegTaskConfig = new JcsegTaskConfig();
        ADictionary dictionary = DictionaryFactory.createDefaultDictionary(jcsegTaskConfig);
        ISegment iSegment = SegmentFactory.createJcseg(JcsegTaskConfig.COMPLEX_MODE, jcsegTaskConfig, dictionary);
        StringReader stringReader = new StringReader("我想只要是学过数据库的孩纸，不管是mysql，还是sqlsever，一提到查找，本能的想到的便是like关键字，其实去转盘网（分类模式）之前也是采用这种算法，但我可以告诉大家一个很不幸的事情，like匹配其实会浪费大量的有用资源，原因这里不说了请自己想一想，我们还是直接摆事实验证。\n");
        iSegment.reset(stringReader);

        IWord iWord;
        while ((iWord = iSegment.next()) != null){
            System.out.println(iWord.getValue());
        }

    }
}
