/*Authors: Ryan Hansen and Zach Miller
* Date: due, submitted 4/10/18
* Overview: This program constructs a graph from an input adjacency
*           matrix in the form of a comma separated file. It then demonstrates
*           Prim's, Kustal's, and Floyd-Warshall's Algorithms. Controls
*           for the program are found at Main.java line 102. Inputs can
*           be controlled by editing input\\input.cvs.
*/

package assignmentthree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        
        //Variables
        Graph graph = new Graph();
        String inPath = "input\\input.cvs";
        //String manipulation
        String line; //Initial, unmodified line from input file
        String weightString;
        
        //Node manipulation; I had trouble with data actually getting into its object
        char labelChar;
        Node nextNode;
        
        //Coordinates for edges matrix; needed to add weights
        int row = 0;
        int column;
        
        //misc
        boolean firstLine = true; //So we know to handle letters rather than ints
        int nodesIn; //Calculate number of nodes based on line length
        
        try {
            //Setting up reader
            FileReader FR = new FileReader(inPath);
            BufferedReader in = new BufferedReader(FR);
            
            //Setting up graph
            while((line=in.readLine()) != null){
                if(firstLine){                              //Make graph, add vertices
                    nodesIn = (line.length() + 1)/2;
                    graph.startGraph(nodesIn);
                    while(line.length() > 0){
                        nextNode = new Node();
                        labelChar = line.charAt(0);
                        nextNode.setLabel(labelChar);
                        graph.addNode(nextNode);
                        if(line.length()>2){
                            line = line.substring(2,line.length());
                        }else{
                            line = "";
                        }
                    }
                    firstLine = false;
                }else{                                      //Add edges
                    column = 0;
                    while(line != "" && column <graph.getNodeCount()){ //Line still has weights to enter, and we are not out of  bounds of the array
                        
                        //handle text input
                        if(line.indexOf(',') == -1){ //The weightString is just the rest of line
                            weightString = line;
                        }else{ //Weight is only the next number
                            weightString = line.substring(0,line.indexOf(','));
                        }
                        
                        //insert edge into graph
                        if(weightString.equals("-")){ //No adjacency, weight is Integer.MAX_VALUE
                            graph.addEdge(new Edge(graph.getNode(row), graph.getNode(column), Integer.MAX_VALUE));
                        }else{  //Are adjacent, add Edge
                            graph.addEdge(new Edge(graph.getNode(row), graph.getNode(column), Integer.parseInt(weightString)));
                            graph.incEdgeCount();
                        }
                        
                        //test to see if that was the last column
                        if(line.indexOf(',') == -1){
                            line = "";
                        }else{
                            line = line.substring(line.indexOf(',')+1);
                        }
                        
                        column ++;
                    }
                    row ++;
                }
            }
            
            
            
            //RUN TESTS HERE
            
            /*
            The algorithm methods probably need somewhat nice graphs put in.
            They definitely need to be formatted correctly, as in the current input file.
            Inputs can be controlled by editing input\\input.cvs.
            Comment lines 119-121 to stop testing Prim's.
            Uncomment lines 124-126 to test Kruskal's.
            Uncomment line 129 to test Floyd-Warshall's.
            */
            
            Graph MST;
            Graph FW;
            System.out.println("Printing input graph.");
            graph.printMatrix();
            
            //Test Prims
            MST = Graph.PrimsMST(graph);
            System.out.println("Printing Prim's MST of input graph");
            MST.printMatrix();
            
            //Test Kruskals
            //MST = Graph.KruskalsMST(graph);
            //System.out.println("Printing Kruskal's MST of input graph");
            //MST.printMatrix();
            
            //Test Floyd-Warshall
            //FW = Graph.floydWarshallsSP(graph);
            
            
            //Directions output:
            System.out.println("TO TEST OTHER ALGORITHMS, CONTROLS ARE FOUND AT MAIN.JAVA LINE 102.");
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
