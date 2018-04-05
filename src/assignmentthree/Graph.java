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
public class Graph { //This is only for weighted, undirected graphs
    private int nodeCount;
    private Edge [][] edges;
    private Node [] nodes;
    
    public Graph(int numNodes){
        nodeCount = numNodes;
        edges = new Edge [nodeCount][nodeCount];
        nodes = new Node [nodeCount];
    }
    
    public void addNode(Node a){
        int i = 0;
        while(nodes[i] != null){
            i++;
        }
        nodes[i] = a;
    }
    
    public void printNodes(){
        for(int i=0; i<nodeCount; i++){
            System.out.println("Node " + i + " has label " + nodes[i].getLabel());
        }
    }
}
