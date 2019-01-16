import edu.rit.pj2.Task;
import edu.rit.util.Instance;
/**
 * This class takes the tuples that match the reduction variables and reduces it
 * and prints the result on the console.
 * <p>
 *
 * @author Arjun Gupta
 * @version 20-October-2018
 */
public class ReduceTask extends Task {

    /**
     * main program to run the task
     * @param args arguments passed as command line. Ignored.
     * @throws Exception throws exception if it fails
     */
    public void main(String[] args) throws Exception {

       
        //reduction variable
        TriangleMax ar = new TriangleMax();
    

     
        //templates to match
        TriangleMax templatemax = new TriangleMax();

        //storage variable
        TriangleMax maxt;

        while ((maxt = tryToTakeTuple(templatemax)) != null)
            ar.reduce(maxt);

        ar.printOutput(ar);
       
    }
}