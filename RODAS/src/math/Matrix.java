/*
 * This class contains matrix math operations
 */

package math;

public class Matrix {
	
	int m;				// number of rows
	int n;				// number of columns
	public double[][] data;	// m-by-n array for internal storage of elements
	
	// create M-by-N matrix of 0's
	public Matrix(int m, int n) {
		this.m = m;
		this.n = n;
		data = new double[m][n];
	}
	
	public Matrix transponse() {
		Matrix T = new Matrix(n, m);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				T.data[i][j] = this.data[j][i];
			}
		}
		return T;
	}
	
	// return C = A * B
	public static Matrix times(Matrix A, Matrix B) {
		if (A.n != B.m) throw new RuntimeException("Illegal matrix dimensions.");
		Matrix C = new Matrix(A.m, B.n);
		for (int i = 0; i < C.m; i++)
			for (int j = 0; j < C.n; j++)
				for (int k = 0; k < A.n; k++)
					C.data[i][j] += (A.data[i][k] * B.data[k][j]);
		return C;
	}
}
