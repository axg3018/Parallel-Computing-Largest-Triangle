
import edu.rit.pj2.Task;
import edu.rit.util.Instance;
import edu.rit.pj2.Loop;
/**
 * This class takes the tuple that match the reduction variables and reduces it
 * it and prints the result on the console.
 * <p>
 *
 * @author Arjun Gupta
 * @version 20-October-2018
 */
public class WorkerTask extends Task {

    private TriangleMax max;

    PointSpec worker_point;

    /**
     * the main program generates points for the current worker and sends it to all the threads
     * It the reduces the result using the reduction variable and puts the tuple into tuple space
     * for the ReduceTask to take it and display the results
     *
     * @param args
     */
    public void main(String[] args) throws Exception {

        max = new TriangleMax();
        worker_point = (PointSpec) Instance.newInstance (args[0]);
        final double[][] x = max.pointArray(worker_point);
         

        //worker thread execution
        workerFor().schedule(leapfrog).exec(new Loop() {

            TriangleMax thrmax;

           double px, py, qx, qy, rx, ry;


            public void start() {
                thrmax = threadLocal(max);
            }

            public void run(int i) {

                px = x[i][0];
                py = x[i][1];

                for(int j = i + 1; j < worker_point.size() - 1; j++){
                    qx = x[j][0];
                    qy = x[j][1];
                    for(int k = j + 1; k < worker_point.size(); k++){
                        rx = x[k][0];
                        ry =x[k][1];

                        double ar = thrmax.reductionCycle(px, py, qx, qy, rx, ry);
                        thrmax.reduce(i, px, py, j, qx, qy, k, rx, ry, ar);

                    }
                }

            }
        });



        putTuple(max);
        
    }
    
    
}