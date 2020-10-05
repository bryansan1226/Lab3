package lab3;
import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class lab3 {
/*
 * This program reads in the names of the top artists and adds them to a linked list 
 * after making sure that there are no duplicates on the list. It then runs the list through a bubble sort 
 * that sorts the list by rearranging the data within the list's nodes. It finally calls on a printList method 
 * that prints the sorted list to an output file
 */
	public static void main(String[] args)throws Exception {
		LinkedList artistNames= new LinkedList();
	
		Scanner scanner=new Scanner(new File("regional-global-daily-latest.csv"));	//Imports the file to read
		scanner.useDelimiter(","); 	
		scanner.nextLine();	//Skips the first line of the file as it doesn't contain data

		while(scanner.hasNextLine()) {//This loop populates the list 
			scanner.nextLine();
			scanner.next();
			scanner.next();
			String name=scanner.next().replaceAll("^\"|\"$", "");//Removes the quotation marks on some names in preparation for sorting
			if(!artistNames.foundInList(artistNames, name))	//The loop will only add the name to the list if it cannot already find the name 
				artistNames=artistNames.insert(artistNames, name);
		}
			artistNames.sort(artistNames, artistNames.getSize(artistNames));//Calls method to sort the list 
			PrintStream out=new PrintStream(new FileOutputStream("ArtistsSorted-WeekOf09272020.txt"));//Creates output file
			System.setOut(out);//Sets console output to file output so that the method writes directly to the .txt file
			artistNames.printList(artistNames);//Calls the method to print the sorted list to the output file
	}	
}

class LinkedList{
	Node head;
	static class Node
	{
		String data;
		Node next;
		Node(String d)
		{
			data=d;
			next=null;
		}
	}
	public static LinkedList insert(LinkedList list, String data) 
	{
		Node newNode= new Node(data);
		newNode.next=null;
		if (list.head==null)
			list.head=newNode;
		else
		{
			Node last=list.head;
			while (last.next!=null)
				last=last.next;
			last.next=newNode;
		}
		return list;
	}
	/*
	 * Method for printing the list, it visits every node in the list and prints the data
	 */
	public static void printList(LinkedList list)
	{
		Node currNode=list.head;
		while (currNode !=null) 
		{
			System.out.println(currNode.data+" ");
			currNode=currNode.next;			
		}
		System.out.println();
	}
	/*
	 * Method for checking whether a string is already found in the list. It uses the list and a string as input then
	 * it visits every node and returns true if it finds a match. 
	 */
	public static boolean foundInList(LinkedList list, String name) {
		boolean isFound=false;
		Node currNode=list.head;
		while (currNode!=null)
		{
			if(name.equals(currNode.data))
				{isFound=true;
				break;}
			else
				currNode=currNode.next;
		}
		return isFound;
	}
	/*
	 * Method for getting the size of a linked list. It takes a list as in input and visits every node, increasing a counter
	 * by one until it reaches the end of the list. It then returns the counter's value as an int.
	 */
	public static int getSize(LinkedList list) {
		Node currNode=list.head;
		int counter=0;
		if(currNode==null)
				return 0;
		else {
			while(currNode!=null) {
				counter++;
//				System.out.println(currNode.data+counter);
				currNode=currNode.next;
			}
		}
		return counter;
	}
	/*
	 * This method sorts all the contents in the list by using a simple bubble sort on the first letter of each name
	 * If it finds that a swap must be made, a temp string variable is used and the method swaps the data in the two nodes
	 */
	public static void sort(LinkedList list, int size) {
		if (size>1) {
			for (int i=0;i<size;i++) {
				Node currNode=list.head;
				Node nextNode=list.head.next;
				for(int j=0;j<size;j++) {
					if(nextNode.next!=null) {
					if(currNode.data.charAt(0)>nextNode.data.charAt(0)) {
						String temp=currNode.data;
						currNode.data=nextNode.data;
						nextNode.data=temp;
					}
					currNode=nextNode;
					nextNode=nextNode.next;}
				}
			}
		}
	}
}











