/* CoevolutionProgram.java
   Written By: Jason Parsons and Alex Gordon
   This is the main driver program for the coevolution program.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class CoevolutionProgram {
	public static void main(String[] args) throws FileNotFoundException {

		//read in filename from user and open file for reading
		System.out.print("Please enter file name: ");
		Scanner keyboard = new Scanner(System.in);
		String filename = keyboard.nextLine();
		filename = "C:/Users/JP/workspace/DataMiningProject/src/" + filename;
		File file1 = new File(filename);
		Scanner inputFile1 = new Scanner(file1);

		//read the file into an arraylist and keep a count of rows and columns while doing so
		int rows = 0;
		int columns = 0;
		ArrayList<String> list = new ArrayList<String>();
		String str;

		while (inputFile1.hasNext()) {
			str = inputFile1.nextLine();
			StringTokenizer strTokenizer = new StringTokenizer(str, " ,\t");
			columns = strTokenizer.countTokens();
			for (int i = 0; i < columns; i++) {
				list.add(strTokenizer.nextToken());
			}
			rows++;
		}

		//copy first row into an attribute array
		String[] aArray = new String[columns];
		int iterator = 0;  //used to iterate through the arraylist
		for (int i = 0; i < columns; i++) {
			aArray[i] = list.get(i);
			iterator++;
		}

		//copy remaining elements of arraylist into a 2D array as Element objects for ease of use when rule mining
		Element[][] dArray = new Element[rows][columns];
		for (int i = 0; i < rows-1; i++)
			for (int j = 0; j < columns; j++) {
				Element it = new Element(list.get(iterator), aArray[j], i, j);
				dArray[i][j] = it;
				iterator++;
			}

		//Prompt user to choose a target attribute
		System.out.println("Please choose a target attribute (by number): ");
		int attributeChoiceNum = 0;
		for (int i = 0; i < aArray.length; i++)
			System.out.println("\t"+(i+1)+" "+aArray[i]);
		System.out.print("Attribute: ");
		boolean validChoice = false;  //used to ensure user is entering a valid option
		while (validChoice == false) {  //check that user enters valid option
			attributeChoiceNum = keyboard.nextInt();	
			if (attributeChoiceNum < 1 || attributeChoiceNum > aArray.length)
				System.out.print("Invalid choice! Please choose again: ");
			else
				validChoice = true;
		}
		String attributeChoiceName = aArray[attributeChoiceNum-1];
		System.out.println("Target attribute is: "+ attributeChoiceName);

		//add all possible attribute values to an arraylist with a null element 
		//dividing values belonging to each attribute
		ArrayList<String> attributeList = new ArrayList<String>();
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows-1; j++) {
				if (!attributeList.contains(dArray[j][i].getElementName()))
					attributeList.add(dArray[j][i].getElementName());
			}
			attributeList.add("NULL");
		}

		System.out.println("Algorithm finished! The results are in the file Result.txt.");

		//begin writing to output file Rules
		PrintWriter outputFile = new PrintWriter("C:/Users/JP/workspace/DataMiningProject/src/Result.txt");
		outputFile.printf("Summary:\n");
		outputFile.printf("\n");
		outputFile.printf("The number of rows in the original data set is: "+rows+"\n");
		outputFile.printf("The number of columns in the original data set is: "+columns+"\n");
		//outputFile.printf("\n");
		outputFile.printf("The attributes of the data set are: ");
		for (int i = 0; i < aArray.length; i++)
			outputFile.printf(aArray[i]+" ");
		outputFile.printf("\n");
		outputFile.printf("\n");
		outputFile.printf("\n");
		outputFile.printf("Possible values for each attribute: \n\n");
		int count = 0;
		boolean flag = false;
		for (int i = 0; i < attributeList.size(); i++) {
			if (flag == false)
				outputFile.printf(aArray[count]+": ");
			flag = true;
			if (!attributeList.get(i).equals("NULL")) 
				outputFile.printf(attributeList.get(i)+" ");
			else {
				count++;
				flag = false;
				outputFile.printf("\n");
			}
		}

		outputFile.printf("\nThe target attribute chosen is: "+attributeChoiceName+"\n\n");
		outputFile.printf("---------------------------------------------------------\n");

		//close input and output files and the keyboard scanner object
		outputFile.close();
		inputFile1.close();
		keyboard.close();
	}
}