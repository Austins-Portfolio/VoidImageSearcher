package permutationsearch;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayUtils {

	public static ArrayList<SearchKey> sortSearchKeysAscending(ArrayList<SearchKey> searches) {
		for(int i = 0; i < searches.size(); i++) {
			for(int k = i; k < searches.size(); k++) {
				if(searches.get(k).getPosition() >searches.get(i).getPosition()) {
					Collections.swap(searches, k, i);
				}
			}
		}
		
		Collections.reverse(searches);
		return searches;
	}
	
	public static ArrayList<SearchKey> sortSearchKeysDescending(ArrayList<SearchKey> searches) {
		for(int i = 0; i < searches.size(); i++) {
			for(int k = i; k < searches.size(); k++) {
				if(searches.get(k).getPosition() >searches.get(i).getPosition()) {
					Collections.swap(searches, k, i);
				}
			}
		}
		
		return searches;
	}
	
	public static int[] concat(int[] array1, int[] array2) {
	    int aLen = array1.length;
	    int bLen = array2.length;
	    int[] result = new int[aLen + bLen];

	    System.arraycopy(array1, 0, result, 0, aLen);
	    System.arraycopy(array2, 0, result, aLen, bLen);

	    return result;
	}
	
	 public static int getClosest(int val1, int val2,  int target) { 
		 if (target - val1 >= val2 - target) {
			 return val2;         
		 }else { 
			 return val1;   
		 }
	 } 
	 
	 public static int getClosestPosition(int val1, int val2, int r1, int r2,  int target) { 
		 if (target - val1 >= val2 - target) {
			 return r1;         
		 }else { 
			 return r2;   
		 }
	 } 
	
	 public static int findClosest(int arr[], int target) { 
		 int n = arr.length; 
	     if (target <= arr[0]) { 
	    	 return arr[0];
	     }
	     if (target >= arr[n - 1]) { 
	         return arr[n - 1];
	     }
	     int i = 0, j = n, mid = 0; 
	     while (i < j) { 
	         mid = (i + j) / 2; 
	         if (arr[mid] == target) {
	        	 return arr[mid];
	         }
	         if (target < arr[mid]) { 
	             if (mid > 0 && target > arr[mid - 1])  {
	            	 return getClosest(arr[mid - 1],arr[mid], target);
	             }
	             j = mid;               
	         } else { 
	        	 if (mid < n-1 && target < arr[mid + 1]) { 
	                    return getClosest(arr[mid],  arr[mid + 1], target);
	        	 }
	             i = mid + 1;
	         } 
	     } 
	     return arr[mid];
	 }
	 
	 public static int findClosestPosition(int arr[], int target) { 
		 int n = arr.length; 
	     if (target <= arr[0]) { 
	    	 return 0;
	     }
	     if (target >= arr[n - 1]) { 
	         return n - 1;
	     }
	     int i = 0, j = n, mid = 0; 
	     while (i < j) { 
	         mid = (i + j) / 2; 
	         if (arr[mid] == target) {
	        	 return mid;
	         }
	         if (target < arr[mid]) { 
	             if (mid > 0 && target > arr[mid - 1])  {
	            	 return mid - getClosestPosition(arr[mid - 1],arr[mid], 1, 0, target);
	             }
	             j = mid;               
	         } else { 
	        	 if (mid < n-1 && target < arr[mid + 1]) { 
	        		 return mid + getClosestPosition(arr[mid],arr[mid + 1], 0, 1, target);
	        	 }
	             i = mid + 1;
	         } 
	     } 
	     return mid;
	 }
	
}
