
public class Matrix {

	double[][] m = null;
	
	public Matrix(int row, int column) {
		m = new double[row][column];
		for (int i=0; i<row; i++) {
			for (int j=0; j<column; j++) {
				m[i][j] = 0;
			}
		}
	}
	
	public static void print(double[][] mat) {
		for (int i=0; i<mat.length; i++) {
			for (int j=0; j<mat[i].length; j++) {
				System.out.print(mat[i][j]+"\t");
			}
			System.out.println();
		}
	}
}
