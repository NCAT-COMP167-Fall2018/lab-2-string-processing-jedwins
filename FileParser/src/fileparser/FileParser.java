/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Justin
 */
public class FileParser {
    
    /**
     * 
     * @param vFileName The name of the file.
     * @param vDataArray The array to store the lines from the file.
     * @param vDataArrayIndex The current empty index in the array.
     * @return Returns the new empty index in the array.
     */
    public static int readFile(String vFileName,  String[] vDataArray, int vDataArrayIndex){
        try{
            File file = new File(vFileName);
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNext()){
                vDataArray[vDataArrayIndex] = fileReader.nextLine();
                vDataArrayIndex++;
            }
        } catch (FileNotFoundException e){
            System.out.println("File Not Found");
        }
        return vDataArrayIndex;
    }
    
    public static void printArray(String[] vDataArray, int vDataArrayIndex){
      for (int i = 0; i < vDataArrayIndex; i++){
          System.out.println(vDataArray[i]);
      }
    }
    
    public static int validateData(String[] vDataArray, int vDataArrayIndex){
        String[] currentLine;
        String line = "";
        
        for(int i = 0; i < vDataArrayIndex; i++){
            line = vDataArray[i];
            currentLine = line.split(",");
            for (String thing: currentLine){
                thing = thing.trim();
                System.out.println(thing);
            }
            System.out.println("---------------------------------");
        }
        
        return vDataArrayIndex;
    }
    
    public static boolean validateName(String vName){
        System.out.println("Name Checking Fucntion");
        return true;
    }
    
    public static boolean validateSex(String vSex){
        System.out.println("Sex Checking Fucntion");
        return true;
    }
    
    public static boolean validateNumber(String vNumber){
        System.out.println("Name Checking Fucntion");
        return true;
    }
    
    public static boolean validateEmail(String vEmail){
        System.out.println("Email Checking Function");
        return true;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String fileName = args[0];
        String[] dataArray = new String[100];
        int dataArrayIndex = 0;
       
        dataArrayIndex = readFile(fileName, dataArray, dataArrayIndex);
        printArray(dataArray, dataArrayIndex);
        
        
    }
    
}
