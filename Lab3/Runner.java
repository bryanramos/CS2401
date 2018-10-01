import java.util.*;
import java.io.*;

public class Runner {
    static int i = 0; 
    static int cubic = 0;
    static Package[] packages;

    static double cubicAvgVolume(Package[] packages) {
        double sumVolumes = 0;
        for (i = 0; i < packages.length; i++) {
            if (packages[i].isCube())
                sumVolumes += packages[i].getVolume();
        }
        return sumVolumes / cubic;
    }
    static void cubicAttributes(Package[] packages) {
        System.out.print("\nAttributes of cubic packages: \n");
        for (i = 0; i < packages.length; i++) {
            if (packages[i].isCube()) {
                System.out.print("Index: " + i + "\n");
                System.out.printf("Dimensions: \n   Width: %.2f \n   Height: %.2f \n   Length: %.2f\n", packages[i].getWidth(), packages[i].getHeight(), packages[i].getLength());
            }
        }
    }
    static void packageType(Package[] packages) {
        int noncubic = 0;
        for (i = 0; i < packages.length; i++) {
            if (packages[i].isCube()) 
                cubic++;
            else 
                noncubic++;
        }
        System.out.printf("No. of Cubic Packages: %d\nNo. of Non-Cubic Packages: %d\n", cubic, noncubic);
    }
    static void largestPackage(Package[] packages) {
        int index = 0;
        double largest = packages[0].getVolume();
        for (i = 0; i < packages.length; i++) {
            if (largest < packages[i].getVolume()) {
                largest = packages[i].getVolume();
                index = i;
            }
        }
        System.out.printf("Attributes of the largest package:\nIndex: %d\n", index);
        System.out.printf("Dimensions: \n   Width: %.2f \n   Height: %.2f \n   Length: %.2f\n", packages[index].getWidth(), packages[index].getHeight(), packages[index].getLength());
        System.out.printf("Volume: %.2f\n\n", packages[index].getVolume());
    }
    static int checkLines(Scanner input) {
        int lines = 0;
        while (input.hasNextLine()) { 
            lines++;
            input.nextLine();
        }
        return lines;
    }
    public static void readFile(String fileName) {
        try {
            Scanner input = new Scanner(new File(fileName));
            int linesInFile = checkLines(input); // amount of lines in file
            if (linesInFile == 0) { // empty file catch
                System.out.println("File is empty!");
                System.exit(0);
            } 
            packages = new Package[linesInFile];
            input = new Scanner(new File(fileName)); // re-init to prevent bug
            int packageNumber = 0; // start from pkg 0 
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] tokens = line.split(" ");
                double[] packageInfo = new double[tokens.length];
                for (i = 0; i < packageInfo.length; i++)
                    packageInfo[i] = Double.parseDouble(tokens[i]);  
                packages[packageNumber] = new Package(packageInfo[0], packageInfo[1], packageInfo[2]); 
                packageNumber++; // goto next package
            }
            input.close(); // terminate scanner
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        Scanner explorer = new Scanner(System.in);
        System.out.print("Please enter the file name: ");
        String fileName = explorer.nextLine();
        explorer.close(); // terminate scanner
        readFile(fileName);
        largestPackage(packages);
        packageType(packages);
        cubicAttributes(packages);
        System.out.printf("\nAverage volume of cubic packages only: %.2f", cubicAvgVolume(packages));
    }
}
