package org.example.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CSVFileReaderWriter {

    public List<String[]> readCSVFile(String filePath) {
        CSVReader reader = null;
        List<String[]> list;
        try {
            list = new ArrayList<>();
            //parsing a CSV file into CSVReader class constructor
            reader = new CSVReader(new FileReader(filePath));
            String[] nextLine;
            //reads one line at a time
            while ((nextLine = reader.readNext()) != null) {
                System.out.print(nextLine);
                System.out.print("\n");
                list.add(nextLine);
            }
        } catch (CsvValidationException ex) {
            throw new RuntimeException(ex);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }


    public void writerCSVFile(List<String[]> lines) throws IOException, CsvValidationException {
        //Instantiating the CSVWriter class
        CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\Snehal Thakur\\IdeaProjects\\Java-Spark-ETL\\src\\main\\resources\\output\\eCommerceOut.csv"));
        //Writing data to a csv file
        String[] nextLine;
        String line1[] = {"InvoiceNo", "StockCode", "Description", "Quantity", "InvoiceDate", "UnitPrice", "CustomerID", "Country"};
        //Writing header data to the csv file
        writer.writeNext(line1);
        for (String[] line : lines) {
            writer.writeNext(line);
        }

//        String line5[] = {"4", "Raghav", "6988", "2012-01-01", "IT"};
//        writer.writeNext(line2);

        //Flushing data from writer to file
        writer.flush();
        System.out.println("CSV file writing successful");
    }

}
