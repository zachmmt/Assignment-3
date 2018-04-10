package assignmentthree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*Authors: Ryan Hansen and Zach Miller
* Date: due, submitted 4/11/18
* Overview: This program constructs a graph from an input adjacency
*           matrix in the form of a comma separated file. It then demonstrates
*           Prim's, Kustal's, and Floyd-Warshall's Algorithms.
*/
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
                    while(line != "" && column <6){ //Line still has weights to enter, and we are not out of  bounds of the array
                        
                        //handle text input
                        if(line.indexOf(',') == -1){ //The weightString is just the rest of line
                            weightString = line;
                        }else{ //Weight is only the next number
                            weightString = line.substring(0,line.indexOf(','));
                        }
                        
                        //insert edge into graph
                        if(weightString.equals("-")){ //No adjacency, weight is -1
                            graph.addEdge(new Edge(graph.getNode(row), graph.getNode(column), -1));
                        }else{
                            graph.addEdge(new Edge(graph.getNode(row), graph.getNode(column), Integer.parseInt(weightString)));
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
            
            graph.printMatrix();
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
