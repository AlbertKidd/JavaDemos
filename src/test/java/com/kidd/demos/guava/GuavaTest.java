package com.kidd.demos.guava;

import com.google.common.graph.ElementOrder;
import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import org.testng.annotations.Test;

import java.util.Set;

/**
 * @author Kidd
 */
public class GuavaTest {

    @Test
    public void testGraph(){

        MutableGraph<String> graph = GraphBuilder
                .directed()
                .nodeOrder(ElementOrder.stable())
                .build();
        graph.putEdge("a", "b");
        graph.putEdge("a", "c");
        graph.putEdge("c", "b");
        graph.putEdge("c", "d");
        Set<String> nodes = graph.nodes();
        System.out.println(graph);
    }
}
