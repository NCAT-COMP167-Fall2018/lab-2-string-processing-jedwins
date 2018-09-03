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
     * Reads in data from the file and only stores inputs which 
     * are correctly formatted.
     * 
     * @param vFileName The name of the file with the data.
     * @param vDataArray The array to store the lines from the file.
     * @param vDataArrayIndex The current empty index in the array.
     * @return Returns the new empty index in the array.
     */
    public static int readFile(String vFileName,  String[] vDataArray, int vDataArrayIndex){     
        String[] currentLine;
        String line = "";
        
        int errorCount = 0;
        int lineCount = 1;
        String firstName, lastName, age, number, email, sex;
        try{
            File file = new File(vFileName);
            Scanner fileReader = new Scanner(file);
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("                         Reading in Data from File.");
            System.out.println("----------------------------------------------------------------------------");
            while (fileReader.hasNext()){
                errorCount = 0;
                line = fileReader.nextLine();
                currentLine = line.split(",");
                
                firstName = currentLine[0].trim();
                lastName = currentLine[1].trim();
                sex = currentLine[2].trim();
                age = currentLine[3].trim();
                number = currentLine[4].trim();
                email = currentLine[5].trim();      

                errorCount += outputError(firstName, "first name", lineCount, validateName(firstName));
                errorCount += outputError(lastName, "last name", lineCount, validateName(lastName));
                errorCount += outputError(sex, "sex", lineCount, validateSex(sex));
                errorCount += outputError(age, "age", lineCount, validateAge(age));
                errorCount += outputError(number, "phone number", lineCount, validateNumber(number));
                errorCount += outputError(email, "email address", lineCount, validateEmail(email));
                
                lineCount++;
                if (errorCount == 0){
                    vDataArray[vDataArrayIndex] = 
                        String.format("%-10s %-10s %-7s %-4s %-14s %s", firstName, lastName, sex, age, number, email);
                    vDataArrayIndex++;
                }
            }
            System.out.println("                     Finished Reading in Data from File.");
            System.out.println("----------------------------------------------------------------------------");
                
                
        } catch (FileNotFoundException e){
            System.out.println("File Not Found");
        }
        return vDataArrayIndex;
    }
    
    /**
     * Outputs data stored in an array. 
     * 
     * @param vDataArray The array with the data in it.
     * @param vDataArrayIndex The index in the array to print up to.
     */
    public static void printArray(String[] vDataArray, int vDataArrayIndex){
      System.out.println("                         Outputting the final array.");
      System.out.println("----------------------------------------------------------------------------");
      for (int i = 0; i < vDataArrayIndex; i++){
        System.out.println(vDataArray[i]);
      }
      System.out.println("----------------------------------------------------------------------------");
      System.out.println("                     Finished Outputting the final array.");
      System.out.println("----------------------------------------------------------------------------");
    }
    
    /**
     * Outputs whether an error was found in a piece of data.
     * 
     * @param vValue The current value being checked.
     * @param vType  The type of value being checked (for example age, first name, etc...).
     * @param vLine  The current line in the file.
     * @param vError Was an error found with the data. Value is true if so.
     * @return Returns 1 if there is an error and 0 if there is not.
     */
    public static int outputError(String vValue, String vType, int vLine, boolean vError){
        if (!vError){
            System.out.print("Line " +  (vLine) +  " is incorrect.");
            System.out.println(" Invalid " + vType + " value " + "\"" + vValue + "\"" + " found.");
            System.out.println("----------------------------------------------------------------------------");     
            return 1;
        }
        return 0;
    }
    
    /**
     * Checks whether the current name value is correctly formatted.
     * 
     * @param vName The current name value being checked.
     * @return Returns true if the name value is correct and false if not.
     */
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
    
    /**
     * Checks if the current sex value is correctly formatted.
     * 
     * @param vSex The current sex value being checked.
     * @return Returns true if the sex value is correct.
     */
    public static boolean validateSex(String vSex){
        boolean correct = false;
        vSex = vSex.toUpperCase();
        if (vSex.equals("MALE") || vSex.equals("FEMALE")){
            correct = true;
        }
        return correct;
    }
    
    /**
     * Checks if the current age value is correctly formatted.
     * 
     * @param vAge The current age value being checked.
     * @return Returns true if the age value is correct.
     */
    public static boolean validateAge(String vAge){
        boolean correct = true;
        char currChar = 0;
        int age = 0;
        if (vAge.length() == 0){
            return false;
        }
        for(int i = 0; i < vAge.length(); i++){
            currChar = vAge.charAt(i);
            if (!Character.isDigit(currChar)){
                return false;
            }
        }
        if (correct){
            age = Integer.parseInt(vAge);
            if (age < 1 || age > 130){
                return false;
            }
        }
        return correct;
    }
    
    /**
     * Checks if the current phone number is correctly formatted.
     * 
     * @param vNumber The current phone number being validated.
     * @return Returns true if the number is valid and false if not.
     */
    public static boolean validateNumber(String vNumber){
        vNumber = vNumber.replace("(", "");
        vNumber = vNumber.replace(")", "");
        vNumber = vNumber.replace("-", "");
        
        int digits = 0;
        for (int i = 0; i < vNumber.length(); i++){
            if (Character.isDigit(vNumber.charAt(i))){
                digits++;
            }
        }
        
        if (digits == 10){
            return true;
        } else{
            return false;
        }
    }
    
    /**
     * Checks if the current email address is correctly formatted.
     * 
     * @param vEmail The current email address being validated.
     * @return Returns true if the email address is valid and false if not.
     */
    public static boolean validateEmail(String vEmail){
        char currChar;
        
        for (int i = 0; i < vEmail.length(); i++){
            currChar = vEmail.charAt(i);
            if (i == 0){
                if (!Character.isAlphabetic(currChar)){
                    return false;
                }
            } else if (!(Character.isAlphabetic(currChar) || Character.isDigit(currChar) || currChar == '.' || currChar == '@' )){
                System.out.println("Case 2");
                return false;
            }
        }
        return true;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fileName = args[0];
        String[] dataArray = new String[100];
        int dataArrayIndex = 0;
        
        dataArrayIndex = readFile(fileName, dataArray, dataArrayIndex); 
        printArray(dataArray, dataArrayIndex);
        
    }
    
}
