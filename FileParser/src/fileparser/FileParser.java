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
        String[] currentLine;
        String line = "";
        
        boolean correct = true;
        String firstName, lastName, age, number, email, sex;
        boolean incorrectFound = false;
        try{
            File file = new File(vFileName);
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNext()){
                incorrectFound = false;
                line = fileReader.nextLine();
                currentLine = line.split(",");
                
                firstName = currentLine[0].trim();
                lastName = currentLine[1].trim();
                sex = currentLine[2].trim();
                age = currentLine[3].trim();
                number = currentLine[4].trim();
                email = currentLine[5].trim();      

                correct = validateName(firstName);
                if (!correct){
                    System.out.print("Entry " + (vDataArrayIndex + 1) + " is incorrect.");
                    System.out.println(" sIncorrect value " + firstName + " found.");
                    correct = true;

                    System.out.println("--------------------------------------------------");
                    incorrectFound = true;
                }

                correct = validateName(lastName);
                if (!correct){
                    System.out.print("Entry " + (vDataArrayIndex + 1) + " is incorrect.");
                    System.out.println(" Incorrect value " + lastName + " found.");
                    correct = true;
                    System.out.println("-------------------------------------------------");
                    incorrectFound = true;
                }

                correct = validateSex(sex);
                if (!correct){
                    System.out.print("Entry " + (vDataArrayIndex + 1) + " is incorrect.");
                    System.out.println(" Incorrect value " + sex + " found.");
                    correct = true;
                    System.out.println("-------------------------------------------------");
                    incorrectFound = true;
                }
                
                if (!incorrectFound){
                    vDataArray[vDataArrayIndex] = line;
                    vDataArrayIndex++;
                }
            }
                
                
        } catch (FileNotFoundException e){
            System.out.println("File Not Found");
        }
        return vDataArrayIndex;
    }
    
    public static void printArray(String[] vDataArray, int vDataArrayIndex){
        String currentLine = "";
        String[] currentLineData;
      for (int i = 0; i < vDataArrayIndex; i++){
        currentLineData = vDataArray[i].split(",");
        currentLine = String.format("%-10s %-10s %-7s %-4s %-16s %s",
                              currentLineData[0].trim(),
                              currentLineData[1].trim(),
                              currentLineData[2].trim(),
                              currentLineData[3].trim(),
                              currentLineData[4].trim(),
                              currentLineData[5].trim());
          System.out.println(currentLine);
      }
    }
    
    public static void removeValue(String[] vArray, int index){
        for(int i = index + 1; i < vArray.length; i++){
            vArray[i - 1] = vArray[i];
        }
    }
    
    public static boolean validateName(String vName){
        boolean correct = false;
        for (int i = 0; i < vName.length(); i++){
            if (i == 0){
                if (Character.isLetter(vName.charAt(i))){
                    correct = true;
                }
            } else{
                if (!Character.isLetter(vName.charAt(i))){
                    correct = false;
                }
            }
        }
        return correct;
    }
    
    public static boolean validateSex(String vSex){
        boolean correct = false;
        vSex = vSex.toUpperCase();
        if (vSex.equals("MALE") || vSex.equals("FEMALE")){
            correct = true;
        }
        return correct;
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
        // printArray(dataArray, dataArrayIndex);
        // validateData(dataArray, dataArrayIndex);
        printArray(dataArray, dataArrayIndex);
    }
    
}
