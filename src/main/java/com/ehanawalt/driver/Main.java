package com.ehanawalt.driver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {

    /**
     * @param args command lines arguments must include the path to a file containing input for the object
     */
    public static void main(String[] args) {
        if(args.length != 1){
            System.err.println("Error, 1 command line input parameter expected: inputFileName");
            System.exit(1);
        }
        try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
            DriverDataReader obj = new DriverDataReader(stream);
            obj.loadData();
            obj.printSummary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
