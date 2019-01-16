import edu.rit.pj2.Vbl;
import edu.rit.pj2.Tuple;
import edu.rit.io.InStream;
import edu.rit.io.OutStream;
import java.io.IOException;
import java.lang.Math;
import java.util.Arrays;
/**
 * Class TriangleMax provides a reduction variable for finding 
 * area of largest triangle
 * It extends the class Tuple
 * It implements Vbl interface provided by the pj2 library.
 * It uses a custom reduction function to get maximum value.
 *
 * @author Arjun Gupta
 * @version 24-Oct-2018
 */
public class TriangleMax extends Tuple implements Vbl {

    double maxA, x1, x2, x3, y1, y2, y3;
    int minP, minQ, minR; 
    double [][]arr;

    /**
     * Create a clone of this shared variable. Required interface function.
     *
     * @return return a new copy of current object
     */
    public Object clone() {
        try{
            TriangleMax vbl = (TriangleMax) super.clone();
            vbl.maxA = this.maxA;
            vbl.minP = this.minP;
            vbl.minQ = this.minQ;
            vbl.minR = this.minR;
            vbl.x1 = this.x1;
            vbl.x2= this.x2;
            vbl.x3 = this.x3;
            vbl.y1 = this.y1;
            vbl.y2= this.y2;
            vbl.y3 = this.y3;
            return vbl;

        } 
        catch (Exception e) {
            throw new RuntimeException("TriangleMax clone error");
        }
    }

    /**
     * Reduces the Vbl object provided and the current object 
     * and stores the maximum. Required interface function.
     *
     * @param vbl Reduction interface object.
     */
    public void reduce(Vbl vbl) {
        TriangleMax newMax = (TriangleMax) vbl;

        //keep the one which is higher.
        if (this.maxA < newMax.maxA || (this.maxA == newMax.maxA && this.minP > newMax.minP) || (this.maxA == newMax.maxA && this.minP == newMax.minP && this.minQ > newMax.minQ) || (this.maxA == newMax.maxA && this.minP == newMax.minP && this.minQ == newMax.minQ && this.minR > newMax.minR)) {
            this.maxA = newMax.maxA;
            this.minP = newMax.minP;
            this.minQ = newMax.minQ;
            this.minR = newMax.minR;
            this.x1 = newMax.x1;
            this.x2 = newMax.x2;
            this.x3 = newMax.x3;
            this.y1 = newMax.y1;
            this.y2 = newMax.y2;
            this.y3 = newMax.y3;
        }
    }

    /**
     * Custom reduce function with two integer input to assign and reduce the
     * shared variable.
     *
     * @param i - index of first point
     * @param j - index of second point
     * @param k - index of third point 
     * @param area - area of triangle    
     */
    public void reduce(int i, double px, double py, int j, double qx, double qy, int k, double rx, double ry, double area) {

        //keep the one which is higher.
        if (this.maxA < area || (this.maxA == area && this.minP > i) || (this.maxA == area && this.minP == i && this.minQ > j) || (this.maxA == area && this.minP == i && this.minQ == j && this.minR > k)) {
            this.maxA = area;
            this.minP = i;
            this.minQ = j;
            this.minR = k;
            this.x1 = px;
            this.x2 = qx;
            this.x3 = rx;
            this.y1 = py;
            this.y2 = qy;
            this.y3 = ry; 
        }
    }

    /**
     * Sets the shared variable to the given shared variable.
     * Required interface function
     *
     * @param vbl Reduction interface object.
     */
    public void set(Vbl vbl) {
        TriangleMax newMaxVbl = (TriangleMax) vbl;
        this.maxA = newMaxVbl.maxA;
        this.minP = newMaxVbl.minP;
        this.minQ = newMaxVbl.minQ;
        this.minR = newMaxVbl.minR;
        this.x1 = newMaxVbl.x1;
        this.x2 = newMaxVbl.x2;
        this.x3 = newMaxVbl.x3;
        this.y1 = newMaxVbl.y1;
        this.y2 = newMaxVbl.y2;
        this.y3 = newMaxVbl.y3;


    }

    /**
     * this write the contents of the class to the outStream to be used as a tuple
     * @param outStream output stream object
     * @throws IOException thrown if error in communication occurs
     */
    public void writeOut(OutStream outStream) throws IOException {
        outStream.writeInt(minP);
        outStream.writeInt(minQ);
        outStream.writeInt(minR);
        outStream.writeDouble(maxA);
        outStream.writeDouble(x1);
        outStream.writeDouble(x2);
        outStream.writeDouble(x3);
        outStream.writeDouble(y1);
        outStream.writeDouble(y2);
        outStream.writeDouble(y3);
        
    }

    /**
     * reads the data from the inStream and puts the data into the object variables
     * @param inStream input stream object
     * @throws IOException thrown if error in communication occurs
     */
    public void readIn(InStream inStream) throws IOException {
        this.minP = inStream.readInt();
        this.minQ = inStream.readInt();
        this.minR = inStream.readInt();
        this.maxA = inStream.readDouble();
        this.x1 = inStream.readDouble();
        this.x2 = inStream.readDouble();
        this.x3 = inStream.readDouble();
        this.y1 = inStream.readDouble();
        this.y2 = inStream.readDouble();
        this.y3 = inStream.readDouble();
    }
    /**
     * Create and return an array with all the points 
     * using a pointspec object.
     * 
     * @param x - PointSpec Object
     *
     * @return arr - Point[] array
     */    
    public double[][] pointArray(PointSpec x){
        this.arr = new double[x.size()][2];
        int i = 0;
        while(x.hasNext()){
            Point p = x.next();
            this.arr[i][0] = p.x;
            this.arr[i][1] = p.y;
            i++;
        
        }
        return this.arr;
    }

    /**
     * Returns area when three sides of triangle are given
     *
     * @param a - first side of triangle
     * 
     * @param b - second side of triangle
     *
     * @param c - third side of triangle
     *
     * @return area of triangle
     *
     */

    public double area(double a, double b, double c){

        double s = (a + b + c)/2;

        return Math.sqrt(s*(s-a)*(s-b)*(s-c));



    }
   
    /**
     * Takes co-ordinates of a triangle as input and returns the area
     *
     * @param px - x co-ordinate of first point
     *
     * @param py - y co-ordinate of first point
     *
     * @param qx - x co-ordinate of second point
     *
     * @param qy - y co-ordinate of second point
     *
     * @param rx - x co-ordinate of third point
     *
     * @param ry - y co-ordinate of third point
     *
     * @return area of the triangle
     */
    public double reductionCycle(double px, double py, double qx, double qy, double rx, double ry){

    
        double a = Math.sqrt(((px - qx) * (px - qx)) + ((py - qy) * (py - qy)));
        double b = Math.sqrt(((qx - rx) * (qx - rx)) + ((qy - ry) * (qy - ry)));
        double c = Math.sqrt(((rx - px) * (rx - px)) + ((ry - py) * (ry - py)));

        return area(a, b, c);
                
              
        
    }

    /**
     *
     * Takes instance reduction class as input and prints the final output
     *
     * @param max - instance of TriangleMax
     *
     */
    public void printOutput(TriangleMax max){

        int i = max.minP;
        int j = max.minQ;
        int k = max.minR;
        int []arr = {i, j, k};
        double x, y;
        int index;
        Arrays.sort(arr);
        for(int a = 0; a < 3; a++){

            if(arr[a] == i){

                index = i;
                x = max.x1;
                y = max.y1;
                

            }

            else if(arr[a] == j){

                index = j;
                x = max.x2;
                y = max.y2;

            }

            else{

                index = k;
                x = max.x3;
                y = max.y3;


            }

            System.out.printf ("%d %.5g %.5g%n", index, x, y);

        }
                
        System.out.printf ("%.5g%n", max.maxA);

    }    
}