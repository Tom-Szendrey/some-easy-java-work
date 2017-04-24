/*
 * Assignment Number 2
 * The purpose of this program is to read from a file (that is a 1000 by 7, but that shouldnt matter)
 * that contains numbers seperated by commas.
 * This program will create some weird formating that the prof wants done with it
 * For more info see http://research.cs.queensu.ca/home/cisc124w/Fall2016/Assn2/Assignment2.html
 * 
 * First save 2016/10/17
 * Last Updated: 2016/10/19
 * Tom Szendrey, 10187030, 14tjs5@queensu.ca
 */


/*
 * How i want to approach this: 
 * Step 1: read file into 2D array
 * Step 2: Create function to avg using the first and last time,
 * and the amps for all of these time stamps 
 * Step 3: Create a File output function (uses, which monitor, avg amps, and startTime,endTime)
 * Step 4: Create a function that reads the amps, and determines if monitor is on or off at that time.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Assignment2FirstSave {
	
	public static void main(String[] args){
		double[][] data = new double[8][1000];	
		readFile("C:\\Programing\\Uni\\Assignment2\\124Ass2Data.txt",data);
		//for (int monitor = 1; monitor < 8; monitor++){
		//	findTimeStamps(monitor,data);
		//	outputMonitor(monitor);
		//}
		findTimeStamps(1,data);
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
	
	//This will be used to find the average amps from the start to the end times.
	//**** HASNT BEEN TESTED YET *****
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
		if (average > 1){ 
			File outputFile = new File("Output File.txt");
			try{
				PrintWriter output = new PrintWriter(outputFile);
				output.println(average + " amps, Starting at " + startTime + " seconds, to " + endTime + " seconds.");
				output.close();
			}catch (IOException ex){
				System.out.println("ERROR");
			}
		}
	}//closes outputData function
	
	//This function will be used every time that the monitor data is being switched from 
	//one monitor to the next.
	public static void outputMonitor(int monitor){
		File outputFile = new File("Output File.txt");
		try{
			PrintWriter output = new PrintWriter(outputFile);
			output.println(monitor);
			output.close();
		}catch (IOException ex){
			System.out.println("ERROR");
		}
	}//closes outputMonitor function
	
	public static void findTimeStamps(int monitor, double[][] data){
		int time = 0;
		while (time < 999){
			while (data[monitor][time] <= 1 & time < 999){
				time += 1;
			}
			int startTime = time;
			while (data[monitor][time] > 1 & time < 999){
				time += 1;
			}
			int endTime = time - 1;
			double average = average(startTime, endTime, monitor, data);
			outputData(startTime, endTime, average);
			//System.out.println(startTime);
			//System.out.println(endTime);
			
		}//ends while time < 1000
	
	}//close findTimeStamps function
}//closes class

























