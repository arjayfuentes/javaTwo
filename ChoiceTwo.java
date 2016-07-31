import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.LineNumberReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Comparator;

public class ChoiceTwo {
  private int numRows;
  private int numCols;
  private List<List<Map<String, String>>> table;
	private List<Map<String, String>> row;
	private Map<String, String> cell;

    public void setRow(int nRows){
    	numRows=nRows;
    }

    public void setCol(int nCols){
    	numCols=nCols;
    }

    public int getRow(){
    	return numRows;
    }

    public int getCol(){
    	return numCols;
    }

    public void createTable(int rows,int cols) {
  		  table = new ArrayList<List<Map<String, String>>>();
  		  numRows=rows;
  		  numCols=cols;
            for (int iRow=0; iRow<numRows; iRow++) {
                row = new ArrayList<Map<String, String>>();
                for (int iCol = 0; iCol < numCols; iCol++) {
                      cell = new LinkedHashMap<String, String>();
                      cell.put(getRandomAscii(), getRandomAscii());
                      row.add(cell);
                }
                table.add(row);
            }
  }

	public void addRow(int plusRow){
		  for (int iRow=0; iRow<plusRow; iRow++) {
          row = new ArrayList<Map<String, String>>();
			    for (int iCol = 0; iCol < numCols; iCol++) {
              cell = new LinkedHashMap<String, String>();
              cell.put(getRandomAscii(), getRandomAscii());
              row.add(cell);
          }
          table.add(row);
    }
	  numRows+=plusRow;
	}

	public void printToText(){
		  try{
			     PrintWriter output = new PrintWriter("filetwo.txt");
			     for (List<Map<String, String>> row : table) {
                StringBuilder sb = new StringBuilder();
                int iCol = 0;
                for (Map<String, String> cell : row) {
                    if (iCol++ > 0) {
                        sb.append("\t");
                    }
                    for (String key : cell.keySet()) {
                        sb.append(key);
                        sb.append("  ");
                        sb.append(cell.get(key));
                    }
                }
                output.println(sb.toString());
          }
			    output.close();
	  }catch(IOException ex){
		      System.out.println("File Not Found");
		}
  }

	public void checkRowFile(){
		  int lines=0;
		  LineNumberReader linenum;
			try {
				    linenum = new LineNumberReader(new FileReader("filetwo.txt"));
				    while(linenum.readLine()!=null){
					         lines++;
				    }
				    numRows=lines;
			} catch (IOException e) {
			     System.out.println("NO FILE");
		  }
	}

	public void checkColFile(){
		  int updateCols=0;
		  try {
			     Scanner input = new Scanner(new FileReader("filetwo.txt"));
			     String[] columns = input.nextLine().split("\t");
			     updateCols=columns.length;
		  }catch (FileNotFoundException e) {
			     System.out.println("NO FILE");
		  }
		  numCols=updateCols;
	}

	public void readFromText(){
		  checkRowFile();
		  checkColFile();
		  try {
			     Scanner input = new Scanner(new FileReader("filetwo.txt"));
			     String keyIn="";
           String valueIn="";
			     loop:while (input.hasNextLine()) {
				         table = new ArrayList<List<Map<String, String>>>();
				         for (int iRow=0; iRow<numRows; iRow++) {
				               row = new ArrayList<Map<String, String>>();
					             String[] columns = input.nextLine().split("\t");
					             for(int i=0;i<columns.length;i++){
						                   cell = new LinkedHashMap<String, String>();
		                           String chars= columns[i];
		                           chars=chars.replaceAll(" ", "");
		                           keyIn=chars.substring(0,(chars.length()/2));
		                           valueIn=chars.substring(chars.length()/2);
		                           cell.put(keyIn, valueIn);
		                           row.add(cell);
		                  }
		                  table.add(row);
	              }
		      }
	    } catch (FileNotFoundException e) {
			     System.out.println("NO FILE");
		  }
	}

	public void printTable() {
		  System.out.println();
          for (List<Map<String, String>> row : table) {
              StringBuilder sb = new StringBuilder();
              int iCol = 0;
              for (Map<String, String> cell : row) {
                  if (iCol++ > 0) {
                      sb.append("\t");
                  }
                  for (String key : cell.keySet()) {
                      sb.append(key);
                      sb.append("  ");
                      sb.append(cell.get(key));
                  }
              }
              System.out.println(sb.toString());
          }
		  System.out.println();
  }

	public void editTable(int rowEdit,int colEdit,String editVal, int keyOrValue){
	     int iRow = 0,sum=0;
		   for (List<Map<String, String>> row : table) {
            int iCol = 0;
            for (Map<String, String> cell : row) {
                  for (String key : cell.keySet()) {
                		  if( iRow==rowEdit && iCol==colEdit ){
							              if(keyOrValue==1){
								                String value=cell.get(key);
								                cell.remove(key);
								                cell.put(editVal,value);
							              }
							              else{
                			          String value =editVal;
							                  cell.put(key,editVal);
							              }
						          }
                	}iCol++;
				    }iRow++;
			}
	}

	public int occurence(String keyValue, String stringKeyValue, String searchStr, int iRow, int iCol){
		  int sum=0;
		  switch(searchStr.length()){
			case 1:	for(int k=0;k<3;k++){
						      if(stringKeyValue.charAt(k)==searchStr.charAt(0)){
						            System.out.printf("Found! %s Coordinate Row=%d Column=%d,\tKeyValue=%s,\tCharacter=%d \n",searchStr,iRow,iCol,keyValue,(k+1));
		    			          sum+=1;
						      }
					    }
					    break;
			case 2: if(searchStr.charAt(0)==searchStr.charAt(1)){
						      if((stringKeyValue.charAt(0)==stringKeyValue.charAt(1))&&(stringKeyValue.charAt(1)==stringKeyValue.charAt(2))){
						              sum=sum+2;
						              System.out.printf("Found! %s Coordinate Row=%d Column=%d,\tKeyValue=%s\t2 times \n",searchStr, iRow,iCol,keyValue);
						      }
						      else{
						              System.out.printf("Found! %s Coordinate Row=%d Column=%d,\tKeyValue=%s \n",searchStr, iRow,iCol,keyValue);
						              sum+=1;
						      }
					    }
					    else{
						          System.out.printf("Found! %s Coordinate Row=%d Column=%d,\tKeyValue=%s \n",searchStr, iRow,iCol,keyValue);
			    		        sum+=1;
					    }
					    break;
			default: System.out.printf("Found! %s Coordinate Row=%d Column=%d,\tKeyValue=%s \n ",searchStr, iRow,iCol,keyValue);
			    	   sum+=1;
					     break;
		 }
		return sum;
	}

	public void search(String searchStr) {
       int iRow = 0;
       int sumOccur=0;
       String keyValue="";
       String stringKeyValue="";
       for (List<Map<String, String>> row : table){
          int iCol = 0;
          for (Map<String, String> cell : row){
              for (String key : cell.keySet()){
                  if (key.contains(searchStr)){
					              keyValue="Key";
					              stringKeyValue=key;
					              sumOccur+=occurence(keyValue,stringKeyValue,searchStr,iRow,iCol);
					        }
                  if(cell.get(key).contains(searchStr)){
					              keyValue="Value";
					              stringKeyValue=cell.get(key);
					              sumOccur+=occurence(keyValue,stringKeyValue,searchStr,iRow,iCol);
					        }
				      }iCol++;
			  }iRow++;
		}
		if(sumOccur==0){
        System.out.println(String.format("Could not find search string \"%s\"", searchStr));
		}
    if(sumOccur>0) {
        System.out.println(searchStr+" found "+sumOccur+" time/s");
    }
  }

  private void sortRow(List<Map<String, String>> row) {
      Collections.sort(row, new CellComparator());
  }

  public void sortTable() {
      for (List<Map<String, String>> row : table) {
            sortRow(row);
      }
      Collections.sort(table, new RowComparator());
  }

  private String getRandomAscii() {
        Random rnd = new Random();
        String result = "";
        for (int i=0;i<3;i++) {
            int value = rnd.nextInt(89) + 33;
            result += (char) value;
        }
        return result;
  }

  class RowComparator implements Comparator<List<Map<String, String>>> {
	   @Override
	   public int compare(List<Map<String, String>> o1, List<Map<String, String>> o2) {
	       for (int i = 0; i < o1.size(); i++) {
	           String key1 = o1.get(i).keySet().iterator().next();
	           String key2 = o2.get(i).keySet().iterator().next();
	           if (!key1.equals(key2)) {
	               return key1.compareTo(key2);
	           }
	       }
	       return 0;
	   }
	}

  class CellComparator implements Comparator<Map<String, String>> {
        @Override
        public int compare(Map<String, String> o1, Map<String, String> o2) {
            String key1 = o1.keySet().iterator().next();
            String key2 = o2.keySet().iterator().next();
            return key1.compareTo(key2);
        }
  }

}
