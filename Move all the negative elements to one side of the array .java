/*
// Java program to put all negative
// numbers before positive numbers
import java.io.*;
 
class GFG {
 
    static void rearrange(int arr[], int n)
    {
        int j = 0, temp;
        for (int i = 0; i < n; i++) {
            if (arr[i] < 0) {
                if (i != j) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
                j++;
            }
        }
    }
 
    // A utility function to print an array
    static void printArray(int arr[], int n)
    {
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
    }
 
    // Driver code
    public static void main(String args[])
    {
        int arr[] = { -1, 2, -3, 4, 5, 6, -7, 8, 9 };
        int n = arr.length;
 
        rearrange(arr, n);
        printArray(arr, n);
    }
}
*/
// Java program of the above
// approach
import java.io.*;

class GFG{

// Function to shift all the
// negative elements on left side
static void shiftall(int[] arr, int left,
					int right)
{
	
	// Loop to iterate over the
	// array from left to the right
	while (left <= right)
	{
		
		// Condition to check if the left
		// and the right elements are
		// negative
		if (arr[left] < 0 && arr[right] < 0)
			left++;

		// Condition to check if the left
		// pointer element is positive and
		// the right pointer element is negative
		else if (arr[left] > 0 && arr[right] < 0)
		{
			int temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++;
			right--;
		}

		// Condition to check if both the
		// elements are positive
		else if (arr[left] > 0 && arr[right] > 0)
			right--;
		else
		{
			left++;
			right--;
		}
	}
}

// Function to print the array
static void display(int[] arr, int right)
{
	
	// Loop to iterate over the element
	// of the given array
	for(int i = 0; i <= right; ++i)
		System.out.print(arr[i] + " ");
		
	System.out.println();
}

// Drive code
public static void main(String[] args)
{
	int[] arr = { -12, 11, -13, -5,
				6, -7, 5, -3, 11 };
					
	int arr_size = arr.length;

	// Function Call
	shiftall(arr, 0, arr_size - 1);
	display(arr, arr_size - 1);
}
}

// This code is contributed by dhruvgoyal267

 