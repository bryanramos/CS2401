import java.util.*;
import java.io.*;
public class Calories1 {
    public static int i, j;
    public static String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    public static void main(String[] args) {
        int[][] mealCalories = new int[7][3]; // breakfast, lunch, dinner 
        readFile("input1.txt", mealCalories);
        listOfCaloriesPerDay(mealCalories);
        averageCaloriesPerDay(mealCalories);
        averageCaloriesPerMeal(mealCalories);
        maxCaloriesEachDay(mealCalories);
        maxCaloriesPerMeal(mealCalories);
    }
    public static void readFile(String fileName, int[][] mealCalories) { 
        try { // execute if no exceptions occur
            File file = new File(fileName);
            Scanner input = new Scanner(file); // read in file

            i = 0; // initialize to 0 to start @ row 0

            while(input.hasNextLine()) {
                String line = input.nextLine();
                String[] tokens = line.split(" ");
                for (j = 0; j < mealCalories[i].length; j++)
                    mealCalories[i][j] = Integer.parseInt(tokens[j]);
                i++; // goto next row 
            }

            input.close();
        } catch (FileNotFoundException e) { // exception catch
            System.out.println("File does not exist!");
        }
    }
    public static void listOfCaloriesPerDay(int[][] mealCalories) {
        int sumOfDay = 0;
        System.out.println("Total list of calories consumed each day from Monday to Sunday:");
        for (i = 0; i < mealCalories.length; i++) {
            System.out.print(days[i] + ": ");
            int totalCalories = 0;
            for (j = 0; j < mealCalories[i].length; j++) 
                totalCalories += mealCalories[i][j];
            System.out.print(totalCalories);
            System.out.println();
        }
        System.out.println();
    }
    public static void averageCaloriesPerDay(int[][] mealCalories) {
        double average = 0; 
        int sumCalories = 0;
        System.out.println("Average number of calories consumed each day:");
        for (i = 0; i < mealCalories.length; i++) {
            System.out.print(days[i] + ": ");
            for (j = 0; j < mealCalories[i].length; j++) 
                sumCalories += mealCalories[i][j];
            average = (double) sumCalories / mealCalories[i].length;
            System.out.printf("%.2f\n", average);
        }
        System.out.println();
    }
    public static void averageCaloriesPerMeal(int[][] mealCalories) {
        String[] meals = {"Breakfast", "Lunch", "Dinner"};
        System.out.println("Average number of calories (average over a week) consumed in each of the meals:");
        for (i = 0; i < mealCalories[i].length; i++) {
            int sumCalories = 0; 
            double average = 0;
            for (j = 0; j < mealCalories.length; j++) 
                sumCalories += mealCalories[j][i];
            average = (double) sumCalories / mealCalories.length;
            System.out.printf("%s: %.2f\n", meals[i], average);
        }
        System.out.println();
    }
    public static void maxCaloriesEachDay(int[][] mealCalories) {
        int maxCalories = 0;
        System.out.println("Maximum number of calories consumed each day: ");
        for (i = 0; i < mealCalories.length; i++) {
            System.out.print(days[i] + ": ");
            maxCalories = mealCalories[i][0];
            for (j = 1; j < mealCalories[i].length; j++) {
                if (maxCalories < mealCalories[i][j])
                    maxCalories = mealCalories[i][j];
            }
            System.out.print(maxCalories + "\n");
        }
        System.out.println();
    }
    public static void maxCaloriesPerMeal(int[][] mealCalories) {
        int maxCalories = 0; 
        String[] meals = {"Breakfast", "Lunch", "Dinner"};
        System.out.println("Maximum number of calories consumed in each meal type:");
        for (i = 0; i < mealCalories[0].length; i++) {
            maxCalories = mealCalories[0][i];
            for (j = 0; j < mealCalories.length; j++) {
                if (maxCalories < mealCalories[j][i])
                    maxCalories = mealCalories[j][i];
            }
            System.out.println(meals[i] + ": " + maxCalories);
        }
        System.out.println();
    }
}
