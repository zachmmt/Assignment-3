/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package assignmentthree;

/**
 *
 * @author Zach Miller
 */
public class Graph {
    private int nodeCount;
    private int [][] edges;
    private Node [] nodes;
    
    public Graph(){
        
    }
    
    public void setGraph(int nodeCount){
        this.nodeCount = nodeCount;
        edges = new int [nodeCount][nodeCount];
        nodes = new Node [nodeCount];
    }
    
    public void addNode(Node a){
        int i = 0;
        while(nodes[i] != null){
            i++;
        }
        nodes[i] = a;
        nodes[i].setIndex(i);
    }
    
    public void printNodes(){
        for(int i=0; i<nodeCount; i++){
            System.out.println("Node " + i + " has label " + nodes[i].getLabel());
        }
    }
    
    public Node getNode(int i){
        return nodes[i];
    }
    
    public void setEdge(int row, int column, int weight){
        edges[row][column] = weight;
    }
    
    public int getEdge(int row, int column){
        return edges[row][column];
    }
    
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
                System.out.print(edges[i][j]);
                if(j<nodeCount-1){
                    System.out.print(",");
                }
            }
            System.out.println();
        }
        
        System.out.println("-1 denotes non-adjacency");
    }
}
