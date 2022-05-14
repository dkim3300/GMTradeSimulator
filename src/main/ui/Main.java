package ui;

import java.io.FileNotFoundException;

// This is where the main application will be run
public class Main {
    public static void main(String[] args) {
        try {
            new FranchiseApp();
        } catch (FileNotFoundException e) {
            System.out.println("Not able to run franchise application: file not found");
        }
    }
}
