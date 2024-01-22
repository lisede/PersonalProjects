package Driver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class arithmeticCalculator{
   
    private static ArrayStack<Double> valstk = new ArrayStack<>();
    private static ArrayStack<String> opstk = new ArrayStack<>();
    private static final int TRUE = 1111111111;
    private static final int FALSE = 1000000000;
    private static final String inputFile = "arithmetics.txt";
    private static final String outputFile = "out.txt";

   
    private static int operatorPrec(String op){
        if (op.equals("^"))
            return 5;
        else if (op.equals("*") || op.equals("/"))
            return 4;
        else if (op.equals("+") || op.equals("-"))
            return 3;
        else if (op.equals(">") || op.equals("<") || op.equals("<=") || op.equals(">="))
            return 2;
        else if (op.equals("==") || op.equals("!=") || op.equals("!") || op.equals("="))
            return 1;
        return 0;
    }   

   
    private static String calculate(double x, double y, String op){
        String result = "";
        if (op.equals("^"))
            result += Operations.power(x, y);
        else if (op.equals("*"))
            result += Operations.multiplication(x, y);
        else if (op.equals("/"))
            result += Operations.division(x, y);
        else if (op.equals("+"))
            result += Operations.addition(x, y);
        else if (op.equals("-"))
            result += Operations.substraction(x, y);
        else if (op.equals("<"))
            result += Operations.lessThan(x, y);
        else if (op.equals(">"))
            result += Operations.moreThan(x, y);
        else if (op.equals("<="))
            result += Operations.lessThanOrEqual(x, y);
        else if (op.equals(">="))
            result += Operations.moreThanOrEqual(x, y);
        else if (op.equals("=="))
            result += Operations.Equals(x, y);
        else if (op.equals("!="))
            result += Operations.notEquals(x, y);
        else{
            System.out.printf("Error, operator not valid");
            System.out.printf("Your program will now terminate");
            System.exit(-1);
        }
        return result;
    }

  
    private static void operation(String op){
        double y = valstk.pop();
        double x = valstk.pop();
        if (op.equals("=")) op = opstk.pop() + op;

        String result = calculate(x, y, op);
        valstk.push(Double.parseDouble(result));
    }

 
    private static void precedenceOp(String input){
        int inner, outer;

        //Makes sure to take into consideration any parentheses
        if (input.equals("(")){
            opstk.push(input);
            return;
        }
       
        if (opstk.isEmpty()) 
        	inner = 0;
        else 
        	inner = operatorPrec(opstk.top());

        outer = operatorPrec(input);
                    
        if (inner < outer) opstk.push(input);
        else { 
            if (input.equals(")") && opstk.top().equals("(")){
                opstk.pop();
                return;
            } 
            else if (input.equals("=") && (opstk.top().equals("=") || opstk.top().equals("!") || opstk.top().equals("<") || opstk.top().equals(">"))){
            	//inserts value in stack and does not perform operation
                opstk.push(input);
                return;
            } 
            else {
                //priority to higher precedence to perform operation
                operation(opstk.pop());
                precedenceOp(input);
            }
        }
    }

 
    public static void main(String[] args){
        //ArrayList just to read through the file and hold each line
        ArrayList<String> lineHolder = new ArrayList<>();


        Scanner sc = null;
        PrintWriter pw = null;

        System.out.printf("This program calculates all the arithmetic equations in \"arithmetics.txt\"\n\n");

        try{
            sc = new Scanner(new FileInputStream(inputFile));
            pw = new PrintWriter(new FileOutputStream(outputFile));   
            
      
            while (sc.hasNextLine()){
                String temp = sc.nextLine();
                lineHolder.add(temp);
            }

            for (String eq : lineHolder){
                char[] eqChar = eq.toCharArray();
                System.out.printf("\nOperation: %s\n", Arrays.toString(eqChar));

                String numHolder = ""; //Acts as a Buffer
                //Iterate through each character
                for (char i : eqChar){
                    if (i == ' ') continue;
                    //Converts a character to a String
                    String holder = String.valueOf(i);

                    //Checks if the character is a number
                    if (holder.matches("^\\d+$"))
                        numHolder += holder;
                    else {
                        //Adds the buffer into the stack
                        if (!numHolder.equals("")){
                            valstk.push(Double.parseDouble(numHolder));
                            numHolder = "";
                        }
                        //Checks if the previous operation should be performed first
                        precedenceOp(holder);
                    }
                }
                //Pushes the remaining number in the buffer
                if (!numHolder.equals(""))
                    valstk.push(Double.parseDouble(numHolder));
                    
                //If there are any operations left, perform them
                while (!opstk.isEmpty())
                    operation(opstk.pop());

                // Prints out the result of the equations to a file
                double finalResult = valstk.pop();

                if (finalResult == TRUE)
                    pw.printf("The result of %s\n is: \t%b\n", eq, true);
                else if (finalResult == FALSE)
                    pw.printf("The result of %s\n is: \t%b\n", eq, false);
                else
                    pw.printf("The result of %s\n is: \t%.2f\n", eq, finalResult);
                
                // Printing the remaining values from the Stack, for Debugging purposes
                while (!valstk.isEmpty()){
                    System.out.printf("ERROR! THE VALUE STACK STILL HAS %.2f\n", valstk.pop());
                }
                while (!opstk.isEmpty()){
                    System.out.printf("ERROR! THE OPERATIONS STACK STILL HAS %s\n", opstk.pop());
                }
                pw.printf("\n");
            }
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());

        } finally {
            if (sc != null) sc.close();
            if (pw != null) pw.close();

            System.out.printf("\nThis is the end of the program\n");
        }
    }
}
