/*
 * Assignment Number 2
 * The purpose of this program is to read from a file (that is a 1000 by 8)
 * that contains numbers seperated by commas.
 * This program will create some weird formating that the prof wants done with it
 * For more info see http://research.cs.queensu.ca/home/cisc124w/Fall2016/Assn2/Assignment2.html
 * 
 * First save 2016/10/17
 * Last Updated: 2016/10/20
 * Tom Szendrey, 10187030, 14tjs5@queensu.ca
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Assignment2FinalSave {
	
	public static void main(String[] args){
		double[][] data = new double[8][1000];	
		readFile("C:\\Programing\\Uni\\Assignment2\\124Ass2Data.txt",data);
		for (int monitor = 1; monitor < 8; monitor++){
			outputMonitor(monitor);
			boolean emptyCheck = findTimeStamps(monitor,data);
			if (emptyCheck){
				outputEmptyMonitor();
			}//ends if
		}//ends for
		System.out.println("Done");
	}//closes main
	
	//This function will be used to read the data into a 2D array
	public static void readFile(String fileName,double[][] data){
		File file = new File(fileName);
		try{
			Scanner input = new Scanner(file);		
			for (int i = 0; i < 1000; i++){
				String temp = input.nextLine(); //Read the line into a temporary variable
				String[] temporary = temp.split(","); //Convert that line into an array of Strings using split(",")
				for (int j = 0; j < 8; j++){
					//Convert the array of temporary strings into doubles, and place them into the 2D array
					data[j][i] = Double.parseDouble(temporary[j]); 
				}
			}
			input.close();
		}catch (FileNotFoundException ex){
			System.out.println("ERROR");
		}
	}//Close readFile
	
	//This will be used to find the average amps given 2 specified times and the data array
	public static double average(int startTime,int endTime, int monitor, double[][] data){
		double runningTotal = 0;
		for (int time =  startTime; time < endTime; time++){
			runningTotal += data[monitor][time];
		}
		double average = runningTotal/(endTime-startTime);
		return(average);
	}//closes average
	
	//This will be used to output the information required to a file.
	//This will use an array of all the start times, end times, and averages for a monitor, 
	//and the monitor number itself.
	public static void outputData(int startTime,int endTime,double average){
		//This if statement is because on my last call to this function for every monitor it will be given the times 999, and 998 due to the while loop. 
		try{
			FileWriter outputFile = new FileWriter("Output File.txt", true);
			PrintWriter output = new PrintWriter(outputFile);
			//This is to check which message will be displayed
			if (average < 8){
				output.println(average + " amps, Starting at " + startTime + " seconds, to " + endTime + " seconds.");
			}
			else{
				output.println("***Current Exceeded!: " + average + " amps, Starting at " + startTime + " seconds, to " + endTime + " seconds.");
			}
			output.close();
		}catch (IOException ex){
			System.out.println("ERROR");
		}
	}//closes outputData function
	
	//This function will be used every time that the monitor data is being switched from 
	//one monitor to the next to display which monitor the data is for
	public static void outputMonitor(int monitor){
		try{
			FileWriter outputFile = new FileWriter("Output File.txt", true);
			PrintWriter output = new PrintWriter(outputFile);
			output.println();
			output.println("Monitor: " + monitor);
			output.close();
		}catch (IOException ex){
			System.out.println("ERROR");
		}
	}//closes outputMonitor function
	
	//If a monitor is never turned on, this function will be called to inform the reader that it wasnt turned on.
	public static void outputEmptyMonitor(){
		try{
			FileWriter outputFile = new FileWriter("Output File.txt", true);
			PrintWriter output = new PrintWriter(outputFile);
			output.println("This monitor was not turned on during the last 1000 seconds");
			output.close();
		}catch (IOException ex){
			System.out.println("ERROR");
		}
	}
	
	//This function will be used to find the start and end times for the monitors
	//For simplicity it also uses average() and outputData()
	//The return for this function lets me know if the monitor was ever on or not.
	public static boolean findTimeStamps(int monitor, double[][] data){
		boolean emptyCheck = true;
		int time = 0;
		while (time < 999){
			//find the start time of when its turned on
			while (data[monitor][time] <= 1 & time < 999){
				time += 1;
			}
			int startTime = time; //mark the start time
			//find the end time
			while (data[monitor][time] > 1 & time < 999){
				time += 1;
			}
			int endTime = time - 1; //mark the end time, (take 1 off because the while loop will add an extra)
			double average = average(startTime, endTime, monitor, data);
			if (average > 1){
				emptyCheck = false;
				outputData(startTime, endTime, average);			
			}
			//outputData(startTime, endTime, average);			
		}//ends while time < 1000
		return(emptyCheck);
	}//close findTimeStamps function
}//closes class

