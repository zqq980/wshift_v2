package science_project;
 
import java.util.Random;
 
public class NumberGenerator1 {
	
	 static void shuffleArray(int[] ar)
	  {
	    Random rnd = new Random();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      int a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	  }
	
    public static void main(String[] args) {
    	
    	int nn = 500;
    	int[] array = new int[nn];
    	
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        
        int[] arrayReverse = new int[array.length];
        for (int i = 0; i < array.length; i++) {
        	arrayReverse[i] = array.length - i;
        }
    	
        shuffleArray(array);
    	
        for (int ii = 0; ii < array.length; ii++) {
            System.out.println(array[ii]);
        }

        
        Random r = new Random();
 

        // generate a uniformly distributed int random numbers
        int[] integers = new int[nn];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = r.nextInt();
        }
        
        for (int i : integers) {
            System.out.print(i + ", ");
        }
        System.out.println("");
        System.out.println(" ========= ");
        
 
        // generate some random boolean values        
        boolean[] booleans = new boolean[10];
        for (int i = 0; i < booleans.length; i++) {
            booleans[i] = r.nextBoolean();
        }
 
        for (boolean b : booleans) {
            System.out.print(b + ", ");
        }
        System.out.println("");
        
        // generate a uniformly distributed float random numbers
        float[] floats = new float[10];
        for (int i = 0; i < floats.length; i++) {
            floats[i] = r.nextFloat();
        }
 
        for (float f : floats) {
            System.out.print(f + ", ");
        }
        System.out.println("");
 
        // generate a Gaussian normally distributed random numbers
        double[] gaussians = new double[10];
        for (int i = 0; i < gaussians.length; i++) {
            gaussians[i] = r.nextGaussian();
        }
 
        for (double d : gaussians) {
            System.out.print(d + ", ");
        }
    }
}