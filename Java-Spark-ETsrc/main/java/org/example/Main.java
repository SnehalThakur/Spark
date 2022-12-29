package org.example;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.opencsv.exceptions.CsvValidationException;
import org.example.utils.*;

public class Main {
    public static void main(String[] args) throws CsvValidationException, IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "CSV/Text/Excel File", "csv", "txt","xlsx");
        chooser.setFileFilter(filter);
        String filePath = null;
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            filePath = chooser.getSelectedFile().getAbsolutePath();
            System.out.println("You choose to open this file: " +
                    filePath);
        }
        if(filePath.contains(".csv")){
            System.out.println("Parsing CSV File");
            CSVFileReaderWriter readerWriter = new CSVFileReaderWriter();
            List<String[]> csvData = readerWriter.readCSVFile(filePath);
            System.out.println("Writing CSV File");
            readerWriter.writerCSVFile(csvData);
        }else if(filePath.contains(".txt")){
            System.out.println("Parsing Text File");
            TextFileReaderWriter readerWriter = new TextFileReaderWriter();
            FileReader textData = readerWriter.readTextFile(filePath);
            System.out.println("Writing Text File");
            readerWriter.writeTextFile(textData);
        }else if(filePath.contains(".xlsx")){
            System.out.println("Parsing Excel File");
            ExcelFileReaderWriter readerWriter = new ExcelFileReaderWriter();
            System.out.println("Writing Excel File");
            readerWriter.readExcelFile(filePath);
        }

    }

    }