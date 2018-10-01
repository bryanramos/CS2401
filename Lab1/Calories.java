import java.util.*;
import java.io.*;
public class Calories {
    public static String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"}; // days of the week array (global)
    public static void main(String[] args) {
        try { // execute code if exceptions aren't triggered
            int[] breakfast = new int[7];
            int[] lunch = new int[7];
            int[] dinner = new int[7];
            
            File file = new File("input.txt"); // read in file
            Scanner input = new Scanner(file);

            checkForLines(input); // checks for exactly seven lines
            input = new Scanner(file); // reinitialize
            checkForNumbers(input); // checks for exactly three numbers per line
            input = new Scanner(file); // reinitialize
            
            int index = 0; // index for three arrays
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] tokens = line.split(" ");
                int[] numbers = new int[tokens.length];
                for (int i = 0; i < numbers.length; i++)
                    numbers[i] = Integer.parseInt(tokens[i]);
                breakfast[index] = numbers[0];
                lunch[index] = numbers[1];
                dinner[index] = numbers[2];
                index++; // increment index
            }
            listOfCaloriesPerDay(breakfast, lunch, dinner);
            System.out.println();
            moreCaloriesThanRequired(breakfast, lunch, dinner);
            System.out.println();
            System.out.println();
            averageCaloriesEachDay(breakfast, lunch, dinner);
            System.out.println();
            averageCaloriesPerMealCombined(breakfast, lunch, dinner);
        } catch (IOException e) { // IOException statement
            System.out.println("The file does not exist!");
            System.exit(0);
        } catch (Exception e) { // General exception
            System.out.println("Exception occurred!");
            System.exit(0);
        }
    }
    public static void listOfCaloriesPerDay(int[] breakfast, int[] lunch, int[] dinner) {
        System.out.println("List of total calories consumed each day of the week: ");
        int sumPerDay = 0; // sum of calories per day
        for (int i = 0; i < days.length; i++) {
            sumPerDay = breakfast[i] + lunch[i] + dinner[i];
            System.out.println(days[i] + ": " + sumPerDay);
        }
    }
    public static void moreCaloriesThanRequired(int[] breakfast, int[] lunch, int[] dinner) {
        System.out.println("Days when more calories than required (2250 calories) are consumed: ");
        int totalCalories = 0;
        for (int i = 0; i < days.length; i++) {
            totalCalories = breakfast[i] + lunch[i] + dinner[i];
            if (totalCalories > 2250)
                System.out.print(days[i] + " ");
        }
    }
    public static void averageCaloriesEachDay(int[] breakfast, int[] lunch, int[] dinner) {
        System.out.println("Average calories consumed for all of the meals during each day:");
        double avgCalories = 0;
        for (int i = 0; i < days.length; i++) {
            avgCalories = (double)(breakfast[i] + lunch[i] + dinner[i]) / 3;
            System.out.printf("%s: %.2f\n", days[i], avgCalories);
        }
    }
    public static void averageCaloriesPerMealCombined(int[] breakfast, int[] lunch, int[] dinner) {
        System.out.println("Average calories consumed for each of the three meals for all days combined: ");
        int sumBreakfast = 0;
        int sumLunch = 0;
        int sumDinner = 0;
        for (int i = 0; i < days.length; i++) {
            sumBreakfast += breakfast[i];
            sumLunch += lunch[i];
            sumDinner += dinner[i];
        }
        System.out.printf("Breakfast: %.2f\n", (double)sumBreakfast / days.length);
        System.out.printf("Lunch: %.2f\n", (double)sumLunch / days.length);
        System.out.printf("Dinner: %.2f\n", (double)sumDinner / days.length);
    }
    public static void checkForLines(Scanner input) {
        int checkLines = 0;
        while (input.hasNextLine()) { // checks for how many lines in file
            checkLines++;
            input.nextLine();
        }
        if (checkLines != 7) {  // if file does not have seven lines output error & terminate
            System.out.println("Error! The file does not have exactly seven lines!"); 
            System.exit(0);
        }
    }
    public static void checkForNumbers(Scanner input) {   
        while(input.hasNextLine()) { // checks for exactly 3 numbers per line
            String line = input.nextLine(); // reads in one line of file
            String[] tokens = line.split(" ");   
            if (tokens.length != 3) { // checks for exactly three numbers per line
                System.out.println("Error! Each line must contain exactly three numbers!");
                System.exit(0);
            }
        }
    }
}
