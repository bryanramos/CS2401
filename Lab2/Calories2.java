import java.util.*;
import java.io.*;
public class Calories2 {
    public static String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    public static int i, j;
    public static void main(String[] args) {
        int[][] mealCalories = new int[7][]; // breakfast, lunch, dinner (ragged)
        readFile("input2.txt", mealCalories);
        averageCaloriesPerDay(mealCalories);
        averageCaloriesPerMealForWeek(mealCalories);
    }
    public static void averageCaloriesPerDay(int[][] mealCalories) {
        double average = 0;
        int sumCalories = 0;
        System.out.println("Average number of calories consumed each day: ");
        for (i = 0; i < mealCalories.length; i++) {
            average = 0; // reset for new line
            sumCalories = 0; // reset for new line
            for (j = 0; j < mealCalories[i].length; j++)
                sumCalories += mealCalories[i][j];
            average = (double) sumCalories / mealCalories[i].length;
            System.out.printf("%s: %.2f\n", days[i], average);
        }
        System.out.println();
    }
    public static void averageCaloriesPerMealForWeek(int[][] mealCalories) {
        int length = lengthOfTheLongestRow(mealCalories); // returns the length of longest row
        int[] sumsOfColumns = new int[length];
        for (i = 0; i < mealCalories.length; i++) {
            for (j = 0; j < mealCalories[i].length; j++)
                sumsOfColumns[j] += mealCalories[i][j];
        }
        int[] elementsInEachColumn = new int[length];
        try {
            int column = 0; // go through each column
            while (column < length) {
                int elementsInColumn = 0;
                for (i = 0; i < mealCalories.length; i++) {
                    if (column < mealCalories[i].length) { 
                        elementsInColumn++; // see if length of row is higher or equal to the column, if so column exists
                    }
                    elementsInEachColumn[column] = elementsInColumn; // store length of each column to be used to divide by later
                }
                column++;
            }
        } catch (ArrayIndexOutOfBoundsException e) { // catch if element does not exist
        }

        int mealNumber = 1; // for printing 
        for (i = 0; i < elementsInEachColumn.length; i++) {
            System.out.printf("Meal %d: %.2f\n", mealNumber, ((double) sumsOfColumns[i] / elementsInEachColumn[i]));
            mealNumber++;
        }
    }
    public static int lengthOfTheLongestRow(int[][] mealCalories) {
        int longest = mealCalories[0].length; // set longest row to first row
        for (i = 1; i < mealCalories.length; i++) {
            if (longest < mealCalories[i].length) 
                longest = mealCalories[i].length;
        }
        return longest;
    }
    public static void readFile(String fileName, int[][] mealCalories) {
        try {
            File file = new File(fileName);
            Scanner input = new Scanner(file); // read in file

            checkForLines(input); // checks for exactly seven lines
            input = new Scanner(file); // reinitialize

            int index = 0; // index of row
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] tokens = line.split(" ");
                mealCalories[index] = new int[tokens.length];
                for (i = 0; i < mealCalories[index].length; i++)
                    mealCalories[index][i] = Integer.parseInt(tokens[i]);
                index++; // goto next row
            }

            input.close();
            
        } catch (FileNotFoundException e) { // exception catch
            System.out.println("File does not exist!");
        }
    }
    public static void checkForLines(Scanner input) {
        int checkLines = 0;
        while (input.hasNextLine()) { // checks for how many lines in file
            checkLines++;
            input.nextLine();
        }
        if (checkLines != 7) { // if the file does not contain seven lines output error & terminate
            System.out.println("Error! The file does not have exactly seven lines!");
            System.exit(0);
        }
    }
}
