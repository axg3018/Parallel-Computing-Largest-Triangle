import edu.rit.pj2.Loop;
import edu.rit.pj2.Job;
import edu.rit.util.Instance;
/**
 * Class LargestTriangleSmp extends class Job from pj2 Library and uses 
 * a masterFor loop to get higest area triangle within a range of
 * random points.
 *
 * Usage: <TT>java pj2 workers=<I>K</I> jar=<I>jarfile</I>LargestTriangleSmp <I>pointspec</I></TT>
 * <BR><TT><I>K</I></TT> = Number of workers 
 * <BR><TT><I>jarfile</I></TT> = name of your jar containing compiled classes 
 * <BR><TT><I>pointspec</I></TT> = constructor for an PointSpec object
 * 
 * @author  Arjun Gupta
 * @version 20-October-2018
 */


public class LargestTriangleClu extends Job{



    /**
     * The main program 
     *
     * @param command line arguments - pointspec object constructor
     *
     */

	public void main(final String[] args) {

		try{
		
        	PointSpec d = (PointSpec) Instance.newInstance (args[0]);

        	masterSchedule(leapfrog);

        	masterFor(0, d.size() - 3, WorkerTask.class).args(args);

        	//at finish this reduces the tuples
			rule().atFinish().task(ReduceTask.class).args(args);
        

        	
        }
        catch(Exception e){
        	usage();
        }	

    }


	/**
	 * Print a usage message and exit.
	 */
	private static void usage(){
		
		System.err.println ("Usage: java pj2 workers=<K> jar=<jarfile> LargestTriangleSmp <pointspec>");
		System.err.println ("<K> = Number of workers");
		System.err.println ("<jarfile> = name of your jar containing compiled classes");
		System.err.println ("<pointspec> = constructor for an PointSpec object");
		terminate(1);
	}
	


}	