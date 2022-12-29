package org.example;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateSampleData {

    public static void main(String[] args) {
        createSampleData();
    }

    private static void createSampleData() {

        BufferedWriter bw = null;
        try
        {
            //create a folder in LFS using the command: "mkdir -p /home/clearurdoubt/examples/"
            bw = new BufferedWriter(new FileWriter(new File("src/main/resources/samplefeedback.csv")));


            for(int i = 1, j = 1000; i <= 100000; i++, j++)
            {
                //employee_id, feedback1, feedback2, feedback3, feedback4, feedback5, client_id
                bw.write(String.format("%08d", i) + "," + (int)(Math.random() * 100) + "," + (int)(Math.random() * 100)
                        + "," + (int)(Math.random() * 100) + "," + (int)(Math.random() * 100)
                        + "," + (int)(Math.random() * 100) + "," + (j + 1));
                bw.write("\n");

                if(j >= 1999)
                {
                    j = 1000;
                }
            }

        }
        catch(IOException ioe)
        {
            System.out.println("An IOException has been raised. " + ioe.getMessage());
            ioe.printStackTrace();
        }
        finally
        {
            try
            {
                System.out.println("Data Preparation is completed.");
                bw.close();
            }
            catch(IOException e)
            {
                System.out.println("Unable to close the BufferedWriter object.");
            }
        }
    }

}