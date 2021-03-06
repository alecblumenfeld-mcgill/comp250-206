import java.io.*;    
import java.lang.reflect.Array;
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
		for (int y=0; y< L2.numberOfStudents;y++ ){
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
    int intersection = 0;
    Arrays.sort(L1.studentID);
    Arrays.sort(L2.studentID);
    int pointerA=0;
    int pointerB=0;
    while(pointerA<L1.numberOfStudents&&pointerB<L2.numberOfStudents){
    	if(L1.studentID[pointerA] == L2.studentID[pointerB]){
    		intersection++;
    		pointerA++;
    		pointerB++;
    	}
    	else if(L1.studentID[pointerA] < L2.studentID[pointerB]){
    		pointerA++;
    		
    	}
    	else{
    		pointerB++;
    	}
    }
	return intersection;
    }
    
    
    public static int intersectionSizeMergeAndSort(studentList L1, studentList L2) {
	int intersection =0;
	int[] c = new int[L1.studentID.length+L2.studentID.length];
	for(int i=0; i< L1.studentID.length-1;i++ ){
		c[i]=L1.studentID[i];
	}
	for(int i=0;i< L2.studentID.length-1; i++){
		c[L1.studentID.length+i]= L2.studentID[i];
	}
	Arrays.sort(c);
	int pointer =0;
	while(pointer<c.length-1){
		if(c[pointer]==c[pointer+1]){
			intersection++;
			pointer+=2;
		}
		else{
			pointer++;
		}
	}
	return intersection;
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
	int[] classSizes= {2,4,8,16,32,64,128,256,512,1000,2000,4000,8000,16000,32000,64000,128000,256000,512000,1024000}; 
	int classPtr =0;

	// repeat the process a certain number of times, to make more accurate average measurements.
	for (int rep=0;rep<100;rep++) {
		long startTime = System.nanoTime();

	    // This is how to generate lists of random IDs. 
	    // For firstList, we generate 16000 IDs
	    // For secondList, we generate 16000 IDs
	    firstList=new studentList(  1024000 , "COMP250 - Introduction to Computer Science"); 
	    
	    //System.out.println(listGenTime);

	    secondList=new studentList( 32000 , "MATH240 - Discrete Mathematics"); 
	    
	    // run the intersection method
	    int intersection=studentList.intersectionSizeNestedLoops(firstList,secondList);
	   // System.out.println("The intersection size is: "+intersection);
		long endTime = System.nanoTime();
		System.out.println( ((endTime-startTime)/1000.0)+"    Matches "+ intersection);
		

	}
	long listGenTimeBefore = System.nanoTime();

//	for (int rep=0;rep<classSizes.length;rep++){
//		
//	    firstList=new studentList(classSizes[classPtr] , "COMP250 - Introduction to Computer Science"); 
//	    long listGenTimeAfter = System.nanoTime();
//	    long listGenTime= (listGenTimeAfter -listGenTimeBefore)*2;
//	    System.out.println(listGenTime/1000.0 );
//	    classPtr++;
//	}

	// repeat the process a certain number of times, to make more accurate average measurements.
    }
}


