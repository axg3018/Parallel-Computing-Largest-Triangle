import edu.rit.util.Instance;

/**
 * Class LargestTriangleSeq extends class Task from pj2 Library and uses 
 * nested while  loops to get higest area triangle within a range of
 * random points.
 *
 * Usage: <TT>java pj2 jar=<I>jarfile</I>LargestTriangleSeq <I>pointspec</I></TT>
 * <BR><TT><I>jarfile</I></TT> = name of your jar containing compiled classes 
 * <BR><TT><I>pointspec</I></TT> = constructor for an PointSpec object
 * 
 * @author  Arjun Gupta
 * @version 20-October-2018
 */


public class LargestTriangleSeq extends edu.rit.pj2.Task{

    /**
     * The main program 
     *
     * @param command line arguments - lower bound and upper bound
     *
     */

	public void main(final String[] args) {

		try{
		
        	PointSpec d = (PointSpec) Instance.newInstance (args[0]);

        	

        	double px, py, qx, qy, rx, ry;
        	TriangleMax max = new TriangleMax();
            double[][] z = max.pointArray(d);

			for(int i = 0; i < d.size() - 2; i++){
        		px = z[i][0];
                py = z[i][1];

                for(int j = i + 1; j < d.size() - 1; j++){
                    qx = z[j][0];
                    qy = z[j][1];
                    for(int k = j + 1; k < d.size(); k++){
                        rx = z[k][0];
                        ry = z[k][1];

                        double ar = max.reductionCycle(px, py, qx, qy, rx, ry);
                        max.reduce(i, px, py, j, qx, qy, k, rx, ry, ar);

                    }
                }

        		
        	}

            max.printOutput(max);
            
        	
        }
        catch(Exception e){
            usage();
        }	

    }
    

	/**
	 * Print a usage message and exit.
	 */
	private static void usage(){
		
		System.err.println ("Usage: java pj2 jar=<jarfile> LargestTriangleSeq <pointspec>");
        System.err.println ("<jarfile> = name of your jar containing compiled classes");
        System.err.println ("<pointspec> = constructor for an PointSpec object");
        terminate(1);
	}


}	