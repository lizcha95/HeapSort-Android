/* Class HeapSort */
public class HeapSort 
{    
	private static int N;
	/* Sort Function */
	public static void sort(int arr[])
	{       
		heapify(arr);        
		for (int i = N; i > 0; i--)
		{
			swap(arr,0, i);
			N = N-1;
			maxheap(arr, 0);
		}
	}     
	/* Function to build a heap */   
	public static void heapify(int arr[])
	{
		N = arr.length-1;
		for (int i = N/2; i >= 0; i--)
			maxheap(arr, i);        
	}
	/* Function to swap largest element in heap */        
	public static void maxheap(int arr[], int i)
	{ 
		int left = 2*i ;
		int right = 2*i + 1;
		int max = i;
		if (left <= N && arr[left] > arr[i])
			max = left;
		if (right <= N && arr[right] > arr[max])        
			max = right;
 
		if (max != i)
		{
			swap(arr, i, max);
			maxheap(arr, max);
		}
	}    
	/* Function to swap two numbers in an array */
	public static void swap(int arr[], int i, int j)
	{
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp; 
	}    
	/* Main method */
	public static void main(String[] args) 
	{
		int i = 0;
		int n = 10;
		int[] intsArray;
		intsArray = new int[10];
		   
		intsArray[0] = 2345;
		intsArray[1] = 1;
		intsArray[2] = 300;
		intsArray[3] = 0;
		intsArray[4] = 8;
		intsArray[5] = 45;
		intsArray[6] = 32;
		intsArray[7] = 8;
		intsArray[8] = 56;
		intsArray[9] = 9;
		
		sort(intsArray);
		System.out.println("Success");       
	}    
}