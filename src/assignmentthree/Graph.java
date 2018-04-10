/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package assignmentthree;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author Zach Miller
 */
public class Graph{
    private int nodeCount;
    private int edgeCount;
    private Edge [][] edges;
    private Node [] nodes;
    
    public Graph(){
    }
    
    //Sets the size of the graph and initializes its matrices
    public void startGraph(int nodeCount){
        this.nodeCount = nodeCount;
        edges = new Edge [nodeCount][nodeCount];
        nodes = new Node [nodeCount];
    }
    
    //Adds a node to a non-full(!) graph
    public void addNode(Node a){
        int i = 0;
        while(nodes[i] != null){
            i++;
        }
        nodes[i] = a;
        nodes[i].setIndex(i);
    }
    
    //Adds an Edge object to the graph (one directional)
    public void addEdge(Edge edge){
        edges[edge.getTail().getIndex()][edge.getHead().getIndex()] = edge;
    }
    
    //Increases edgeCount by one
    public void incEdgeCount(){
        edgeCount++;
    }
    
    //Prim's algorith
    public static Graph PrimsMST(Graph G){
        Graph T = new Graph();
        
        return T;
    }
    
    //Kruskal's algorithm
    public static Graph KruskalsMST(Graph G){
        //generate cluster
        for(int i=0; i<G.getNodeCount(); i++){
            G.getNodeMatrix()[i].setCluster(new NodeCluster(G.getNodeCount()));
        }
        
        //Initialize priority queue
        Comparator<Edge> comparator = new EdgeComparator();
        PriorityQueue<Edge> EdgePQ = new PriorityQueue<>(comparator);
        
        //fill priority queue
        for(int column=1; column<=G.getNodeCount()-1; column++){
            for(int row=0; row<column; row++){
                if(G.getEdgeMatrix()[row][column].getWeight() != -1){
                    EdgePQ.add(G.getEdgeMatrix()[row][column]);
                }
            }
        }
        
        //create output graph with nodes (no edges)
        Graph T = new Graph();
        T.startGraph(G.getNodeCount());
        for(int i=0; i<G.getNodeCount(); i++){
            T.addNode(G.getNode(i));
        }
        
        //adding nodes and merging clusters
        while(T.getEdgeCount() < T.getNodeCount()-1){
            if(EdgePQ.isEmpty()){ //We have run out of edges, and the graph is still unconnected: no MST
                System.out.println("There is no MST");
                break;
            }else{ //We have an edge and need to check it out
                Edge nextEdge = EdgePQ.poll();
                Node nextTail = nextEdge.getTail();
                Node nextHead = nextEdge.getHead();
                if(nextTail.getCluster() != nextHead.getCluster()){ //These clusters are unconnected. Add edge to MST
                    for(int i=0; i<T.nodeCount; i++){ //move all nodes to new cluster
                        if(nextTail.getCluster().getNode(i) != null){
                            nextTail.getCluster().getNode(i).setCluster(nextHead.getCluster());
                            nextHead.getCluster().addNode(nextTail.getCluster().getNode(i));
                        }
                    }
                    
                    //add edge to graph
                    T.addEdge(nextEdge);
                    Edge reverse = new Edge(nextEdge.getHead(), nextEdge.getTail(), nextEdge.getWeight());
                    T.addEdge(reverse);
                    T.incEdgeCount();
                }
            }
        }
        
        //Fill in empty and self edges
        for(int row=0; row<T.nodeCount; row++){
            for(int column=0; column<T.nodeCount; column++){
                if(T.getEdge(row, column) == null){
                    if(row == column){ //connects node to self: weight=0
                        Edge selfEdge = new Edge(T.getNode(row), T.getNode(row), 0);
                        T.addEdge(selfEdge);
                    }else{
                        Edge falseEdge =  new Edge(T.getNode(row), T.getNode(column), -1);
                        T.addEdge(falseEdge);
                    }
                }
            }
        }
        
        T.printMatrix();
        
        return T;
    }
    
    //Printers
    //Prints a list of the nodes, index & label
    public void printNodes(){
        for(int i=0; i<nodeCount; i++){
            System.out.println("Node " + i + " has label " + nodes[i].getLabel());
        }
    }
    //Prints the adjacency matrix of the graph
    public void printMatrix(){
        //Title
        System.out.println("Printing adjacency matrix:");
        
        //First row (nodes)
        for(int i=0; i<nodeCount; i++){
            System.out.print(nodes[i].getLabel());
            if(i<nodeCount-1){
                System.out.print(",");
            }
        }
        System.out.println();
        
        //Rest of the rows (edges)
        for (int i=0; i<nodeCount; i++){
            for (int j=0; j<nodeCount; j++){
                if(edges[i][j].getWeight() != -1){
                    System.out.print(edges[i][j].getWeight());
                }else{
                    System.out.print("-");
                }
                if(j<nodeCount-1){
                    System.out.print(",");
                }
            }
            System.out.println();
        }
        
        //Note:
        System.out.println("Note: - denotes non-adjacency");
    }
    
    //getters
    public Node getNode(int i){
        return nodes[i];
    }
    public Node [] getNodeMatrix(){
        return this.nodes;
    }
    public int getNodeCount(){
        return nodeCount;
    }
    public Edge [][] getEdgeMatrix(){
        return edges;
    }
    public int getEdgeCount(){
        return edgeCount;
    }
    public Edge getEdge(int row, int column){
        return edges[row][column];
    }

}
