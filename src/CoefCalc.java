

public class CoefCalc {
	
	
	public static void main(String args[]) {
		
		double[] mu1 = {-1, 1};
		double[] mu2 = {1, 0};
		
		// sigma: {{a b}, {c d}}
		double[][] sigma1 = {{1, 0}, {0, 1}};
		double[][] sigma2 = {{2, 2}, {2, 3}};
		
		// original
//		double[] mu1 = {-1.0198, 0.9001};
//		double[] mu2 = {1.2543, 0.1943};
//		
//		// sigma: {{a b}, {c d}}
//		double[][] sigma1 = {{0.9506, 0.0567}, {0.0567, 0.7909}};
//		double[][] sigma2 = {{2.4361, 2.5306}, {2.5306, 3.5421}};
//		
		// another training data
//		double[] mu1 = {-0.9530, 1.0437};
//		double[] mu2 = {0.9459, 0.0651};
//		
//		// sigma: {{a b}, {c d}}
//		double[][] sigma1 = {{1.0516, 0.1050}, {0.1050, 1.1071}};
//		double[][] sigma2 = {{1.5863, 1.6185}, {1.6185, 2.7065}};
//		
		double[][] inv1 = {{0, 0}, {0, 0}};
		double[][] inv2 = {{0, 0}, {0, 0}};
		
		double det1 = 0f;
		double det2 = 0f;
		
		// calculate determinant of matrix
		det1 = CoefCalc.det(sigma1);
		det2 = CoefCalc.det(sigma2);
		
		inv1 = CoefCalc.inverse(sigma1);
		inv2 = CoefCalc.inverse(sigma2);
		
		System.out.println("determinant");
		System.out.println(det1);
		System.out.println(det2);
		System.out.println("\ninverse matrix");
		Matrix.print(inv1);
		Matrix.print(inv2);
		
		CoefSet set1 = CoefCalc.calcCoefficient(mu1, det1, inv1, 1/2f);
		CoefSet set2 = CoefCalc.calcCoefficient(mu2, det2, inv2, 1/2f);
		set1.id = "coef1";
		set2.id = "coef2";
		CoefSet.print(set1);
		CoefSet.print(set2);
		
		CoefSet set = CoefSet.minus(set2, set1);
		set.id = "coef_final";
		CoefSet.print(set);
		
		CoefSet.printSolution(set);
	}
	
	public static double det(double[][] mat) {
		return mat[0][0] * mat[1][1] - mat[0][1] * mat[1][0];
	}
	
	public static double[][] inverse(double[][] mat) {
		double[][] inv = {{0, 0}, {0, 0}};
		double det = det(mat);
		inv[0][0] = 1/det * mat[1][1];
		inv[0][1] = -1/det * mat[1][0];
		inv[1][0] = -1/det * mat[0][1];
		inv[1][1] = 1/det * mat[0][0];
		return inv;
	}
	
	/*
	 * mu: 1x2 matrix
	 * det: scalar
	 * inv: 2x2 matrix
	 */
	public static CoefSet calcCoefficient(double[] mu, double det, double[][] inv, double prior) {
		CoefSet coefSet = new CoefSet();
		double a = inv[0][0];
		double b = inv[0][1];
		double c = inv[1][0];
		double d = inv[1][1];

		// coef of x1^2
		coefSet.coef_x1_2 = -1/2f * a;
		
		// coef of x2^2
//		coefSet.coef_x2 = -1/2f * d;
		coefSet.coef_x2_2 = -1/2f * d;
		
		// coef of x1*x2
		coefSet.coef_x1x2 = -1/2f * (c + b);
		
		// coef of x1
		coefSet.coef_x1 = -1/2f * (-2 * a*mu[0] - (c+b)*mu[1]);
		
		// coef of x2
		coefSet.coef_x2 = -1/2f * (-2 * d*mu[1] - (c+b)*mu[0]);
		
		// coef of constant
		coefSet.coef_const = -1/2f * (a*Math.pow(mu[0], 2) + c*mu[0]*mu[1] + d*Math.pow(mu[1], 2))
				-1/2f*Math.log10(det) + Math.log10(prior);
		
		return coefSet;
	}
}
