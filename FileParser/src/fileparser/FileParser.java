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
        
        int errorCount = 0;
        int lineCount = 1;
        String firstName, lastName, age, number, email, sex;
        try{
            File file = new File(vFileName);
            Scanner fileReader = new Scanner(file);
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
    
    public static int outputError(String vValue, String vType, int vLine, boolean vError){
        if (!vError){
            System.out.print("Line " +  (vLine) +  " is incorrect.");
            System.out.println(" Invalid " + vType + " value " + "\"" + vValue + "\"" + " found.");
            System.out.println("-------------------------------------------------");     
            return 1;
        }
        return 0;
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
        // TODO code application logic here
        String fileName = args[0];
        String[] dataArray = new String[100];
        int dataArrayIndex = 0;
       
        dataArrayIndex = readFile(fileName, dataArray, dataArrayIndex);
        printArray(dataArray, dataArrayIndex);
    }
    
}
