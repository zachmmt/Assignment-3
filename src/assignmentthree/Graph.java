/*Authors: Ryan Hansen and Zach Miller
* Date: due, submitted 4/10/18
* Overview: This program constructs a graph from an input adjacency
*           matrix in the form of a comma separated file. Inputs can
*           be controlled by editing input\\input.cvs.
*/

package assignmentthree;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Graph{
    private int nodeCount;
    private int edgeCount;
    public Edge [][] edges;
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
                if(edges[i][j].getWeight() != Integer.MAX_VALUE){
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
        //System.out.println("Note: - denotes non-adjacency, 0 denotes (imaginary) self edge");
        System.out.println();
    }
    
    //puts a * before and after the changed edge (step matrix)
    public void printStepMatrix(int row, int column){
        //Title
        System.out.println("Printing step adjacency matrix:");
        
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
                
                //change marker 1
                if(i==row && j==column){
                    System.out.print("*");
                }
                
                //print value
                if(edges[i][j].getWeight() != Integer.MAX_VALUE){
                    System.out.print(edges[i][j].getWeight());
                }else{
                    System.out.print("-");
                }
                
                //change marker 2 and optional comma
                if(i==row && j==column){
                    System.out.print("*");
                }
                if(j<nodeCount-1){
                    System.out.print(",");
                }
            }
            System.out.println();
        }
        
        //Note:
        //System.out.println("Note: - denotes non-adjacency, 0 denotes (imaginary) self edge");
        System.out.println();
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
