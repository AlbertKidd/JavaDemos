package com.kidd.demos.jgrapht;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.TopologicalOrderIterator;
import org.testng.annotations.Test;

/**
 * @author Kidd
 */
public class JgraphtTest {

    @Test
    public void simpleDirectedGraph(){
        Graph<String, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);
        fillGraph(graph);
        TopologicalOrderIterator<String, DefaultEdge> iterator = new TopologicalOrderIterator<>(graph);
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
    }

    @Test
    public void directedAcyclicGraph(){
        DirectedAcyclicGraph<String, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);
        fillGraph(graph);
        for (String s : graph) {
            System.out.println(s);
        }
    }

    private void fillGraph(Graph<String, DefaultEdge> graph){

        graph.addVertex("大学");
        graph.addVertex("中专");
        graph.addVertex("大专");
        graph.addVertex("小学");
        graph.addVertex("初中");
        graph.addVertex("高中");

        graph.addEdge("小学", "初中");
        graph.addEdge("初中", "高中");
        graph.addEdge("高中", "大学");

        graph.addEdge("初中", "中专");
        graph.addEdge("中专", "大专");
        graph.addEdge("中专", "大学");
        graph.addEdge("大专", "大学");

    }
}
