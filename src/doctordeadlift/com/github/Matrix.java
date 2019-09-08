package doctordeadlift.com.github;
import java.util.Arrays;

/**
 * This class contains some specific methods for matrix processing 
 * @author doctordeadlift
 */
public class Matrix {
	private int[][] matrix;
	
	/**
	 * @param matrix - specified matrix for processing
	 */
	public Matrix(int[][] matrix) {
		this.matrix = matrix;
	}
	/**
	 * Print specified matrix
	 */
	public void printCurrentMatrix() {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.printf("%4d", matrix[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * Sort specified matrix column by target line
	 * @param start - first element in sort matrix
	 * @param end - last element in sort matrix
	 * @param k - target line
	 */
	public void columnQuickSort(int start, int end, int k) {
		int s = start,
			e = end,
			pivot = matrix[k][(s+e)/ 2];
		
		while(s < e) {
			while(matrix[k][s] < pivot) s++;
			while(matrix[k][e] > pivot) e--;
			
			if(s <= e) {
				for(int i = 0; i < matrix.length; i++) {
					int interim = matrix[i][s];
					matrix[i][s] = matrix[i][e];
					matrix[i][e] = interim;
				}
				s++; e--;
			}
			
			if(start < e) columnQuickSort(start, e, k);
			if(end > s) columnQuickSort(s, end, k);
		}
	}
	/**
	 * Searching largest increase sequence in matrix 
	 * @return - elements quantity of largest increase sequence
	 */
	public int largestIncreaseSequenceSearch() {
		int count = 1,
			x = 0,
			maxS = 1;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				if(j == 0 && i!=0 && matrix[i][j] > x) {
					count++;
				} else if (j == 0 && i!=0 && matrix[i][j] <= x) {
					if(count > maxS) 
						maxS = count;
					count = 1;
					continue;
				}
				if(j > 0 && matrix[i][j] > matrix[i][j-1]) {
					count++;
				} else if(j > 0 && matrix[i][j] <= matrix[i][j-1]) {
					if(count > maxS) 
						maxS = count;
					count = 1;
					continue;
				}
			}
			x = matrix[i][matrix[i].length - 1];
		}
		if(count > maxS) 
			maxS = count;
		return maxS;
	}
	/**
	 * Shift matrix down
	 * @param k - numbers of shift
	 */
	public void matrixDownShift(int k) {
		int[] first = Arrays.copyOf(matrix[matrix.length -1], matrix[matrix.length -1].length);
		for(int i = matrix.length - 1; i > 0; i--) {
			for(int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = matrix[i - 1][j];
			}
		}
		
		matrix[0] = Arrays.copyOf(first, first.length);
		if(k > 1) matrixDownShift(--k);
	}
	/**
	 * Flip matrix to 90 degrees counterclockwise
	 */
	public void flipMatrix90() {
		for(int i = 0; i < matrix.length/2; i++) {
			for(int j = i; j < matrix[i].length - 1 - i; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[j][matrix.length-1-i];
				matrix[j][matrix.length-1-i] = matrix[matrix.length-1-i][matrix.length-1-j];
				matrix[matrix.length-1-i][matrix.length-1-j] = matrix[matrix.length-1-j][i];
				matrix[matrix.length-1-j][i] = tmp; 
			}
		}
	}
	/**
	 * Searching local minimal elements
	 * @param matrix
	 * @return number of local minimal elements
	 */
	public static int localMinSearch(int[][] matrix) {
		int count = 0;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				if(i == 0 && j == 0) {
					if(matrix[i][j] < matrix[i][j+1] && matrix[i][j] < matrix[i+1][j] && matrix[i][j] < matrix[i+1][j+1]) {
						count++;
						continue;
					}
					continue;
				}
				else if(i == 0 && j == matrix[i].length - 1) {
					if(matrix[i][j] < matrix[i][j-1] && matrix[i][j] < matrix[i+1][j] && matrix[i][j] < matrix[i+1][j-1]) {
						count++;
						continue;
					}
					continue;
				}
				else if(i == 0) {
					if(matrix[i][j] < matrix[i][j-1] && matrix[i][j] < matrix[i][j+1] && matrix[i][j] < matrix[i+1][j-1] && matrix[i][j] < matrix[i+1][j+1] && matrix[i][j] < matrix[i+1][j]) {
						count++;
						continue;
					}
					continue;
				}
				else if(j == 0 && i == matrix.length - 1) {
					if(matrix[i][j] < matrix[i-1][j] && matrix[i][j] < matrix[i-1][j+1] && matrix[i][j] < matrix[i][j+1]) {
						count++;
						continue;
					}
					continue;
				}
				else if(j == 0) {
					if(matrix[i][j] < matrix[i][j+1] && matrix[i][j] < matrix[i+1][j] && matrix[i][j] < matrix[i-1][j+1] && matrix[i][j] < matrix[i+1][j+1] && matrix[i][j] < matrix[i-1][j]) {
						count++;
						continue;
					}
					continue;
				}
				else if(i == matrix.length -1 && j == matrix[i].length -1) {
					if(matrix[i][j] < matrix[i-1][j] && matrix[i][j] < matrix[i][j-1] && matrix[i][j] < matrix[i-1][j-1]) {
						count++;
						continue;
					}
					continue;
				}
				else if(i == matrix.length -1) {
					if(matrix[i][j] < matrix[i-1][j] && matrix[i][j] < matrix[i][j-1] && matrix[i][j] < matrix[i-1][j-1] && matrix[i][j] < matrix[i][j+1] && matrix[i][j] < matrix[i-1][j+1]) {
						count++;
						continue;
					}
					continue;
				}
				else if(j == matrix[i].length - 1 && i == 0) {
					if(matrix[i][j] < matrix[i-1][j] && matrix[i][j] < matrix[i-1][j-1] && matrix[i][j] < matrix[i][j-1]) {
						count++;
						continue;
					}
					continue;
				}
				else if(j == matrix[i].length - 1) {
					if(matrix[i][j] < matrix[i-1][j] && matrix[i][j] < matrix[i-1][j-1] && matrix[i][j] < matrix[i][j-1] && matrix[i][j] < matrix[i-1][j] && matrix[i][j] < matrix[i-1][j-1]) {
						count++;
						continue;
					}
					continue;
				} 
				else if(matrix[i][j] < matrix[i-1][j] && matrix[i][j] < matrix[i-1][j-1] && matrix[i][j] < matrix[i][j-1] && matrix[i][j] < matrix[i-1][j] && matrix[i][j] < matrix[i-1][j-1] && matrix[i][j] < matrix[i-1][j+1] && matrix[i][j] < matrix[i][j+1] && matrix[i][j] < matrix[i+1][j+1]) {
					count++;
				}
			}
		}
		return count;
	}
	/**
	 * Group maximal element to matrix diagonal
	 */
	public void groupMaxElem() {
		int maxI = 0,
			maxJ = 0,
			posI = 0,
			posJ = 0;
		
		for(int k = 0; k < matrix.length; k++) {
			for(int i = 0; i < matrix.length; i++) {
				for(int j = 0; j < matrix[i].length; j++) {
					if(matrix[i][j] > matrix[maxI][maxJ]) {
						if(i != j || (i == j && i >=k && j >=k))
						{
							maxI = i;
							maxJ = j;
						}
					}
				}
			}
			
			int tmp = matrix[posI][posJ];
			matrix[posI][posJ] = matrix[maxI][maxJ];
			matrix[maxI][maxJ] = tmp;
			posI++;
			posJ++;
			maxI=posI;
			maxJ=posJ;
		}
	}
	/**
	 * Group matrix column by decrease values of characteristics
	 * @param start
	 * @param end
	 */
	public void quickSortMatrixColumn(int start, int end) {
		int s = start,
			e = end;
		int pivot = 0;
		
		for(int i = 0; i < matrix.length; i++) {
			pivot+=Math.abs(matrix[i][(s+e)/2]);
		}
		
		while(s < e) {
			while(true) {
				int sumS = 0;
				for(int i = 0; i < matrix.length; i++) {
					sumS+=Math.abs(matrix[i][s]);
				}
				if(pivot >= sumS || s == matrix.length - 1) {
					break;
				} else {
					s++;
				}
			}
			
			while(true) {
				int sumE = 0;
				for(int i = 0; i < matrix.length; i++) {
					sumE+=Math.abs(matrix[i][e]);
				}
				if(pivot <= sumE || e == 0) {
					break;
				} else {
					e--;
				}
			}
			
			if(s <= e) {
				for(int i = 0; i < matrix.length; i++) {
					int tmp = matrix[i][s];
					matrix[i][s] = matrix[i][e];
					matrix[i][e] = tmp;
				}
				s++;
				e--;
			}
		}
		
		if(start < e) quickSortMatrixColumn(start, e);
		if(end > s) quickSortMatrixColumn(s, end);
	}
	/**
	 * Display column values of characteristics
	 */
	public void columnCheck() {
		int count = 0;
		for(int i = 0; i < matrix[0].length; i++) {
			for(int k = 0; k < matrix.length; k++) {
				count+=Math.abs(matrix[k][i]);
			}
			System.out.printf("%4d", count);
			System.out.print(" ");
			count = 0;
		}
		System.out.println('\n');
	}
}
