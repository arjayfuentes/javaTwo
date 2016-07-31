import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExerciseTwo {

	private static int minRowCol=1;
	private static int maxRowCol=9;

	public static void main(String[] args) {
    	int choice=4;
			int nRows=0;
		  int nCols=0;
			Scanner read = new Scanner(System.in);
			ChoiceTwo table = new ChoiceTwo();
			checkFileFound();
			table.readFromText();
			System.out.print("\nTable from file : filetwo.txt");
			table.printTable();
			loop:while(choice!=7){
				nRows=table.getRow();
				nCols=table.getCol();
				choice=checkChoice();

				switch(choice){
					case 1: String search="";
									System.out.print("Enter String or Character to search: ");
									search=read.next();
						    	table.search(search);
									break;
					case 2:	int editRow=checkRow(0,nRows-1);
									int editCol=checkColumn(0,nCols-1);
									int keyOrValue=chooseKeyOrValue();
									String editVal=" ";
									do{
										System.out.print("Enter 3 Characters, No space , No Tab :");
										editVal=read.nextLine();
									}while(editVal.length()!=3||editVal.contains("\t")||editVal.contains(" "));
									table.editTable(editRow,editCol,editVal,keyOrValue);
									break;
					case 3: table.readFromText();
									table.printTable();
									break;
					case 4: nRows=checkRow(minRowCol,maxRowCol);
						  		nCols=checkColumn(minRowCol,maxRowCol);
						  		table.createTable(nRows,nCols);
									break;
					case 5: int plusRow=0;
									System.out.println("Enter rows to be added: ");
									plusRow=checkRow(0,10);
									table.addRow(plusRow);
									break;
					case 6: table.sortTable();
									break;
					case 7: break loop;
					default:break;
				}
				System.out.println();
				table.printToText();
			}
	}

	public static void checkFileFound(){
		boolean found=false;
		int nRows=0;
		int nCols=0;
		while(found==false){
			try{
				FileReader file = new FileReader("filetwo.txt");
				found=true;
			}catch (FileNotFoundException e) {
				System.out.println("File not found! We will create one with file name: filetwo.txt");
				nRows=checkRow(minRowCol,maxRowCol);
				nCols=checkColumn(minRowCol,maxRowCol);
				ChoiceTwo table = new ChoiceTwo();
				table.setRow(nRows);
				table.setCol(nCols);
				table.createTable(nRows,nCols);
				File file = new File("filetwo.txt");
				table.printToText();
			}
		}
	}

	public static int chooseKeyOrValue(){
		int keyOrValue=0;
		Scanner read= new Scanner(System.in);
		boolean correct = false;
		while(true)
		{
			System.out.print("Edit the 1.Key or 2.Value: ");
			try{
				keyOrValue=Integer.parseInt(read.next());
				if(keyOrValue>0&&keyOrValue<3)
				{
					break;
				}
				System.out.println("Not Valid! Reenter");
			}
			catch(NumberFormatException ignore){
				System.out.println("Invalid: not a number");
			}
		}
		return keyOrValue;
	}

	public static int checkChoice(){
		int choice;
		Scanner read=new Scanner(System.in);
		while(true)
		{
			System.out.println("Choose from the following: \n1.Search  2.Edit  3.Print  4.Reset  5.Add Row  6.Sort  7.Exit");
			System.out.println();
			System.out.print("Enter Choice: ");
			try{
				choice=Integer.parseInt(read.next());
				if(choice>0&&choice<8)
				{
					break;
				}
				System.out.println("Not Valid: Reenter choice");
			}
			catch(NumberFormatException ignore){
				System.out.println("Invalid: not a number");
			}
		}
		return choice;
	}

	public static int checkRow(int min, int max){
		Scanner read = new Scanner(System.in);
		int row=0;
		while(true)
			{
			try{
				System.out.print("Enter row: ");
				row=Integer.parseInt(read.next());
				if(row>=min&&row<=max)
					{
						break;
					}
					System.out.println("Not in range");
				}
			catch(NumberFormatException ignore)
				{
				System.out.println("Not a number");
				}
			}
			return row;
		}

	public static int checkColumn(int min, int max){
		Scanner read = new Scanner(System.in);
		int column=0;
		while(true){
			try{
				System.out.print("Enter column: ");
				column=Integer.parseInt(read.next());
				if(column>=min&&column<=max){
						break;
				}
				System.out.println("Not in range");
			}catch(NumberFormatException ignore){
					System.out.println("Not a number");
			}
		}
		return column;
	}

}
