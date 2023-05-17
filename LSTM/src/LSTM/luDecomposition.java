//package LSTM;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//
//public class luDecomposition {
//	
//	
//	    public ArrayList<Double> lu_decomposition(List<List<Double>> A, List<Double> b) {
//	        int n = A.size();
//	        double[][] A_array = convert_to_2d_array(A);
//	        double[][] L = create_identity_matrix(n);
//	        double[][] U = create_empty_matrix(n, n);
//
//	        for (int k = 0; k < n; k++) {
//	            // Partial pivoting
//	            int max_row = k;
//	            for (int i = k+1; i < n; i++) {
//	                if (Math.abs(A_array[i][k]) > Math.abs(A_array[max_row][k])) {
//	                    max_row = i;
//	                }
//	            }
//	            if (max_row != k) {
//	                swap_rows(A_array, k, max_row);
//	                swap_rows(L, k, max_row);
//	            }
//
//	            // Gaussian elimination
//	            for (int i = k+1; i < n; i++) {
//	                double m = A_array[i][k] / A_array[k][k];
//	                A_array[i][k] = m;
//	                L[i][k] = m;
//	                for (int j = k+1; j < n; j++) {
//	                    A_array[i][j] -= m * A_array[k][j];
//	                }
//	                b.set(i, b.get(i) - m * b.get(k));
//	            }
//	        }
//
//	        // Extract U from A
//	        for (int i = 0; i < n; i++) {
//	            for (int j = i; j < n; j++) {
//	                U[i][j] = A_array[i][j];
//	            }
//	        }
//
//	        // Solve for x using forward and backward substitution
//	        ArrayList<Double> z = forward_substitution(L, b);
//	        ArrayList<Double> x = backward_substitution(U, z);
//
//	        return x;
//	    }
//
//	    private static double[][] convert_to_2d_array(List<List<Double>> A) {
//	        int n = A.size();
//	        double[][] A_array = new double[n][n];
//	        for (int i = 0; i < n; i++) {
//	            for (int j = 0; j < n; j++) {
//	                A_array[i][j] = A.get(i).get(j);
//	            }
//	        }
//	        return A_array;
//	    }
//
//	    private static double[][] create_identity_matrix(int n) {
//	        double[][] I = new double[n][n];
//	        for (int i = 0; i < n; i++) {
//	            I[i][i] = 1.0;
//	        }
//	        return I;
//	    }
//
//	    private static double[][] create_empty_matrix(int n, int m) {
//	        return new double[n][m];
//	    }
//
//	    private static void swap_rows(double[][] A, int i, int j) {
//	        double[] temp = A[i];
//	        A[i] = A[j];
//	        A[j] = temp;
//	    }
//
//	    private static ArrayList<Double> forward_substitution(double[][] L, List<Double> b) {
//	        int n = L.length;
//	        ArrayList<Double> z = new ArrayList<>(n);
//	        for (int i = 0; i < n; i++) {
//	            double sum = 0.0;
//	            for (int j = 0; j < i; j++) {
//	                sum += L[i][j] * z.get(j);
//	            }
//	            z.add((b.get(i) - sum) / L[i][i]);
//	        }
//	        return z;
//	    }
//	    private static ArrayList<Double> backward_substitution(double[][] U, ArrayList<Double> z) {
//	    	int n = U.length;
//	        ArrayList<Double> x = new ArrayList<>(Collections.nCopies(n, 0.0));
//	        for (int i = n-1; i >= 0; i--) {
//	            double sum = 0.0;
//	            for (int j = i+1; j < n; j++) {
//	                sum += U[i][j] * x.get(j);
//	            }
//	            x.set(i, (z.get(i) - sum) / U[i][i]);
//	        }
//	        return x;
//	    }
//
//	    //private static ArrayList<Double> backward_sub
//	
//
//}
