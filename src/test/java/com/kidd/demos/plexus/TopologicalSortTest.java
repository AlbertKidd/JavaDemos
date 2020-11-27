package com.kidd.demos.plexus;

import org.codehaus.plexus.util.dag.DAG;
import org.codehaus.plexus.util.dag.TopologicalSorter;
import org.testng.annotations.Test;

import java.util.List;

/**
 * 拓扑排序测试
 * @author Kidd
 */
public class TopologicalSortTest {

    @Test
    public void test() throws Exception{
        DAG dag = new DAG();
        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";
        dag.addEdge(a, b);
        dag.addEdge(a, c);
        dag.addEdge(b, c);
        dag.addEdge(b, d);
        List<String> sort = TopologicalSorter.sort(dag);
        System.out.println(sort);
    }
}
