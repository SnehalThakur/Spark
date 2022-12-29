package org.example.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileReaderWriter {

    public FileReader readTextFile(String filePath) {
        FileReader reader = null;
        try {
            //parsing a CSV file into CSVReader class constructor
            reader = new FileReader(filePath);
            String[] nextLine;
            int i;
            //reads one line at a time
            while ((i = reader.read()) != -1)

                // Print all the content of a file
                System.out.print((char)i);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return reader;
    }


    public void writeTextFile(FileReader reader){

        // Try block to check if exception occurs
        try {
            // Create a FileWriter object
            // to write in the file
            FileWriter fWriter = new FileWriter(
                    "C:\\Users\\Snehal Thakur\\IdeaProjects\\Java-Spark-ETL\\src\\main\\resources\\output\\techOut.txt");
            int i;
            // Writing into file
            //reads one line at a time
            while ((i = reader.read()) != -1)
                fWriter.write((char)i);

            // Printing the contents of a file
            System.out.println((char)i);

            // Closing the file writing connection
            fWriter.close();

            // Display message for successful execution of
            // program on the console
            System.out.println(
                    "File is created successfully with the content.");
        }

        // Catch block to handle if exception occurs
        catch (IOException e) {

            // Print the exception
            System.out.print(e.getMessage());
        }
    }
}
