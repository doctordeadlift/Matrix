package doctordeadlift.com.github;

public class Test {
	public static void main(String[] args) {
		int[][] arr = {{7, 12, 4}, {5, 0, 11}, {23, 10, 2}};
		Matrix matrix = new Matrix(arr);
		matrix.printCurrentMatrix();
		matrix.columnQuickSort(0, arr.length - 1, 2);
		matrix.printCurrentMatrix();
		System.out.println(matrix.largestIncreaseSequenceSearch());
		matrix.matrixDownShift(1);
		matrix.printCurrentMatrix();
		matrix.flipMatrix90();
		matrix.printCurrentMatrix();
		System.out.println(matrix.localMinSearch(arr));
		matrix.quickSortMatrixColumn(0, arr.length - 1);
		matrix.printCurrentMatrix();
		matrix.columnCheck();
		matrix.groupMaxElem();
		matrix.printCurrentMatrix();
	}
}

