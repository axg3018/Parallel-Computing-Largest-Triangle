# Parallel-Computing-Largest-Triangle

Given a group of two-dimensional points, we want to find the largest triangle, namely three distinct points that are the vertices of a triangle with the largest area. The area of a triangle is (s(s − a)(s − b)(s − c))1/2, where a, b, and c are the lengths of the triangle's sides and s = (a + b + c)/2. The length of a triangle's side is the Euclidean distance between the side's two endpoints. 

### Program Input and Output

The program's command line argument is a constructor expression for a PointSpec object from which the program obtains the points' (x,y) coordinates. The first point is at index 0, the second point is at index 1, and so on.

The program must print four lines of output, as in this example:
```
$ java pj2 LargestTriangleSeq "RandomPoints(100,100,142857)"
9 95.951 7.8408
12 98.248 97.938
80 2.3838 77.670
4295.3
```

The first line contains the index of the largest triangle's first vertex point, a space character, the X coordinate of the first vertex point, a space character, the Y coordinate of the first vertex point, and a newline. The line must be printed with this statement:

    System.out.printf ("%d %.5g %.5g%n", index, x, y);

The second line contains the index and coordinates of the largest triangle's second vertex point. The third line contains the index and coordinates of the largest triangle's third vertex point. The first three lines must be printed in ascending order of the index.

The fourth line contains the largest triangle's area. The line must be printed with this statement:

    System.out.printf ("%.5g%n", area);

The program must print the triangle with the largest area. If more than one triangle has the same largest area, the program must print the triangle with the smallest first index. If more than one triangle has the same largest area and smallest first index, the program must print the triangle with the smallest second index. If more than one triangle has the same largest area and smallest first index and smallest second index, the program must print the triangle with the smallest third index. 

### Software Requirements

  The sequential version of the program must be run by typing this command line:

        java pj2 jar=<jarfile> LargestTriangleSeq "<pointspec>"

        <jarfile> is the name of a Java Archive file containing the compiled class files for your project.
        <pointspec> is a constructor expression for a point specification object. 

  Note: This means that the program's class must be named LargestTriangleSeq, this class must not be in a package, and this class must extend class edu.rit.pj2.Task.

  Note: On the tardis computer, you must include the jar= option. For further information, see Parallel Java 2 on the RIT CS Parallel Computers.

  The parallel version of the program must be run by typing this command line:

        java pj2 jar=<jarfile> workers=<K> LargestTriangleClu "<pointspec>"

        <jarfile> is the name of a Java Archive file containing the compiled class files for your project.
        <K> is the number of worker tasks.
        <pointspec> is a constructor expression for a point specification object. 
