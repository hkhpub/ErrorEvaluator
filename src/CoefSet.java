
public class CoefSet {

	public String id = "";
	
	double coef_x1_2 = 0;
	double coef_x2_2 = 0;
	double coef_x1x2 = 0;
	double coef_x1 = 0;
	double coef_x2 = 0;
	double coef_const = 0;
	
	public static void print(CoefSet set) {
		System.out.println("--Coefficient: "+set.id+" --");
		System.out.println(String.format("x1^2: %f", set.coef_x1_2));
		System.out.println(String.format("x2^2: %f", set.coef_x2_2));
		System.out.println(String.format("x1*x2: %f", set.coef_x1x2));
		System.out.println(String.format("x1: %f", set.coef_x1));
		System.out.println(String.format("x2: %f", set.coef_x2));
		System.out.println(String.format("const: %f", set.coef_const));
		System.out.println();
	}
	
	/**
	 * set2 - set1
	 * @param set1
	 * @param set2
	 * @return
	 */
	public static CoefSet minus(CoefSet set1, CoefSet set2) {
		CoefSet set = new CoefSet();
		set.coef_x1_2 = set2.coef_x1_2 - set1.coef_x1_2;
		set.coef_x2_2 = set2.coef_x2_2 - set1.coef_x2_2;
		set.coef_x1x2 = set2.coef_x1x2 - set1.coef_x1x2;
		set.coef_x1 = set2.coef_x1 - set1.coef_x1;
		set.coef_x2 = set2.coef_x2 - set1.coef_x2;
		set.coef_const = set2.coef_const - set1.coef_const;
		return set;
	}
	
	public static void printSolution(CoefSet set) {
		StringBuffer sb = new StringBuffer();
		if (set.coef_x1_2 != 0) {
			sb.append(String.format("%.4f", set.coef_x1_2)+"*x^2");
		}
		if (set.coef_x2_2 != 0) {
			sb.append("+"+String.format("%.4f", set.coef_x2_2)+"*y^2");
		}
		if (set.coef_x1x2 != 0) {
			sb.append("+"+String.format("%.4f", set.coef_x1x2)+"*x*y");
		}
		if (set.coef_x1 != 0) {
			sb.append("+"+String.format("%.4f", set.coef_x1)+"*x");
		}
		if (set.coef_x2 != 0) {
			sb.append("+"+String.format("%.4f", set.coef_x2)+"*y");
		}
		if (set.coef_const != 0) {
			sb.append("+"+String.format("%.4f", set.coef_const));
		}
		System.out.println(sb.toString());
	}
}
