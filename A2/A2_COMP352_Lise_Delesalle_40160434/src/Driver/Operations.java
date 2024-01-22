package Driver;

public class Operations {
	
	 public static double addition(double x, double y){
	        return x + y;
	    }

	    public static double substraction(double x, double y){
	        return x - y;
	    }


	    public static double multiplication(double x, double y){
	        return x * y;
	    }

	 
	    public static double division(double x, double y){
	        return x / y;
	    }

	    public static double power(double x, double y){
	        if (y == 0){
	            return 1;
	        }
	        return x * power(x, y - 1);
	    }

	
	    public static double lessThan(double x, double y){
	        boolean temp = x < y;
	        if (temp) return 1111111111;
	        return 1000000000;
	    }

	    public static double lessThanOrEqual(double x, double y){
	        boolean temp = x <= y;
	        if (temp) return 1111111111;
	        return 1000000000;
	    }

	
	    public static double moreThan(double x, double y){
	        boolean temp = x > y;
	        if (temp) return 1111111111;
	        return 1000000000;
	    }

	  
	    public static double moreThanOrEqual(double x, double y){
	        boolean temp = x >= y;
	        if (temp) return 1111111111;
	        return 1000000000;
	    }

	 
	    public static double Equals(double x, double y){
	        boolean temp = x == y;
	        if (temp) return 1111111111;
	        return 1000000000;
	    }

	   
	    public static double notEquals(double x, double y){
	        boolean temp = x != y;
	        if (temp) return 1111111111;
	        return 1000000000;
	    }

	

}
