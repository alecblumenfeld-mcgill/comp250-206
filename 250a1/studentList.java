import java.io.*;    
import java.util.*;

class studentList {
    int studentID[];
    int numberOfStudents;
    String courseName;
    
    // A constructor that reads a studentList from the given fileName and assigns it the given courseName
    public studentList(String fileName, String course) {
	String line;
	int tempID[]=new int[4000000]; // this will only work if the number of students is less than 4000000.
	numberOfStudents=0;
	courseName=course;
	BufferedReader myFile;
	try {
	    myFile = new BufferedReader(new FileReader( fileName ) );	

	    while ( (line=myFile.readLine())!=null ) {
		tempID[numberOfStudents]=Integer.parseInt(line);
		numberOfStudents++;
	    }
	    studentID=new int[numberOfStudents];
	    for (int i=0;i<numberOfStudents;i++) {
		studentID[i]=tempID[i];
	    }
	} catch (Exception e) {System.out.println("Can't find file "+fileName);}
	
    }
    

    // A constructor that generates a random student list of the given size and assigns it the given courseName
    public studentList(int size, String course) {
	int IDrange=2*size;
	studentID=new int[size];
	boolean[] usedID=new boolean[IDrange];
	for (int i=0;i<IDrange;i++) usedID[i]=false;
	for (int i=0;i<size;i++) {
	    int t;
	    do {
		t=(int)(Math.random()*IDrange);
	    } while (usedID[t]);
	    usedID[t]=true;
	    studentID[i]=t;
	}
	courseName=course;
	numberOfStudents=size;
    }
    
    // Returns the number of students present in both lists L1 and L2
    public static int intersectionSizeNestedLoops(studentList L1, studentList L2) {
    int	intersection =0;
	for(int x =0; x<L1.numberOfStudents; x++  ){
		for (int y=0; y< L1.numberOfStudents;y++ ){
			if(L1.studentID[x]==L2.studentID[y]){
				intersection++;
			}
			
		}
	}
	return intersection;
    }
    
    
    // This algorithm takes as input a sorted array of integers called mySortedArray, the number of elements it contains, and the student ID number to look for
    // It returns true if the array contains an element equal to ID, and false otherwise.
    public static boolean myBinarySearch(int mySortedArray[], int numberOfStudents, int ID) {
	/* For question 2, Write your implementation of the binary search algorithm here */
    	
    	int left = 0;
    	int right = numberOfStudents;
    	//
    	while(right > left + 1){
    		//checks to see if the check value is less than or greater then the two binarys, splits 
    		int	mid = (left+right)/2;
    	    
    		if(mySortedArray[mid] > ID)
    			right = mid;
    		else 
    			left = mid;
    	
    	}

        if(mySortedArray[left] == ID){
        	return true;    	

        }
        	
    	return false;
    }
    
    
    public static int intersectionSizeBinarySearch(studentList L1, studentList L2) {
	/* Write your code for question 2 here */
    int intersection =0;
    Arrays.sort(L2.studentID);
    int studentCount = L1.numberOfStudents;
    //pipes l1 & l2 into my binarryy search and iterates when returns true
    for(int i = 0; i < studentCount; i++)
		if(myBinarySearch(L2.studentID, L2.numberOfStudents, L1.studentID[i]))
			intersection++;
	
    return intersection	;
    }
    
    
    public static int intersectionSizeSortAndParallelPointers(studentList L1, studentList L2) {
	/* Write your code for question 3 here */
	return 0;
    }
    
    
    public static int intersectionSizeMergeAndSort(studentList L1, studentList L2) {
	/* Write your code for question 4 here */
	return 0;
    }
    
    
    
    /* The main method */
    /* Write code here to test your methods, and evaluate the running time of each. 2014 */
    /* This method will not be marked */
    public static void main(String args[]) throws Exception {
	
	studentList firstList;
	studentList secondList;
	
	// This is how to read lists from files. Useful for debugging.
	
	//	firstList=new studentList("COMP250.txt", "COMP250 - Introduction to Computer Science");
	//	secondList=new studentList("MATH240.txt", "MATH240 - Discrete Mathematics");
	
	// get the time before starting the intersections
	long startTime = System.nanoTime();
	
	// repeat the process a certain number of times, to make more accurate average measurements.
	for (int rep=0;rep<2;rep++) {
	    
	    // This is how to generate lists of random IDs. 
	    // For firstList, we generate 16000 IDs
	    // For secondList, we generate 16000 IDs
	    
	    firstList=new studentList(16000 , "COMP250 - Introduction to Computer Science"); 
	    secondList=new studentList(16000 , "MATH240 - Discrete Mathematics"); 
	    
	    // run the intersection method
	    int intersection=studentList.intersectionSizeNestedLoops(firstList,secondList);
	    System.out.println("The intersection size is: "+intersection);
	}
	
	// get the time after the intersection
	long endTime = System.nanoTime();
	
	
	System.out.println("Running time: "+ (endTime-startTime)/1000.0 + " nanoseconds:  Nested Loops");
	//binarysort
	 startTime = System.nanoTime();
	
	// repeat the process a certain number of times, to make more accurate average measurements.
	for (int rep=0;rep<2;rep++) {
	    
	    // This is how to generate lists of random IDs. 
	    // For firstList, we generate 16000 IDs
	    // For secondList, we generate 16000 IDs
	    
	    firstList=new studentList(16000 , "COMP250 - Introduction to Computer Science"); 
	    secondList=new studentList(16000 , "MATH240 - Discrete Mathematics"); 
	    
	    // run the intersection method
	    int intersection=studentList.intersectionSizeBinarySearch(firstList,secondList);
	    System.out.println("The intersection size is: "+intersection);
	}
	
	// get the time after the intersection
	 endTime = System.nanoTime();
	
	
	System.out.println("Running time: "+ (endTime-startTime)/1000.0 + " nanoseconds: Binary Search");
    }
    
}


