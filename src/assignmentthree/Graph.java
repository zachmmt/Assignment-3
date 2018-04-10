/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package assignmentthree;

import java.util.Comparator;

/**
 *
 * @author Zach Miller
 */
public class Graph{
    private int nodeCount;
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
    
    //Prim's algorith
    public static Graph PrimsMST(Graph G){
        Graph T = new Graph();
        
        return T;
    }
    
    //Kruskal's algorithm
    public static Graph KruskalsMST(Graph G){
        //create output graph
        Graph T = new Graph();
        
        //generate cluster
        for(int i=0; i<G.getNodeCount(); i++){
            G.getNodeMatrix()[i].setCluster(new NodeCluster(G.getNodeCount()));
            System.out.println(G.getNodeMatrix()[i].getCluster());
        }
        
        //priority queue
        
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

}
