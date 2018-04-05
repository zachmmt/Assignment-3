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
        String inPath = "input\\input.csv";
        
        //Variables
        String line; //Initial, unmodified line from input file
        boolean firstLine = true; //So we know to handle letters rather than ints
        int nodesIn; //Calculate number of nodes based on line length
        
        
        try {
            //Setting up reader
            FileReader FR = new FileReader(inPath);
            BufferedReader in = new BufferedReader(FR);
            
            while((line=in.readLine()) != null){
                nodesIn = (line.length() + 1)/2;
                if(firstLine){ //Add vertices
                    
                    
                    
                    
                    
                    firstLine = false;
                }else{ //Add edges
                    
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
