import java.io.*;
import java.util.*;

class GFG {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		int price[] = { 2, 30, 15, 10, 8, 25, 80 };
		// adding array
		int profit = 0;
		// Initializing variable
		// valley-peak approach
		/*
						80
						/
			30		 /
		/ \		 25
		/ 15	 /
		/	 \	 /
		2	 10 /
				\ /
					8
		*/
		for (int i = 1; i < price.length; i++) {
			// traversing through array from (i+1)th
			// position
			int sub = price[i] - price[i - 1];
			if (sub > 0)
				profit += sub;
		}
		System.out.print("Maximum Profit=" + profit);
	}
}
