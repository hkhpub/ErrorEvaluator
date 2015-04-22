import java.util.ArrayList;
import java.util.Vector;


public class SimpleTester {

	public static void main(String args[]) {
		
		ArrayList<String> label1 = FileUtil.readFile("test1.dat");
		ArrayList<String> label2 = FileUtil.readFile("test2.dat");
		
		Vector<double[]> data1 = new Vector<double[]>();
		Vector<double[]> data2 = new Vector<double[]>();
		
		for (String str : label1) {
			double[] feat = new double[2];
			feat[0] = Double.valueOf(str.split(",")[0]);
			feat[1] = Double.valueOf(str.split(",")[1]);
			data1.add(feat);
		}
		
		for (String str : label2) {
			double[] feat = new double[2];
			feat[0] = Double.valueOf(str.split(",")[0]);
			feat[1] = Double.valueOf(str.split(",")[1]);
			data2.add(feat);
		}
		
		int correct1=0, correct2 = 0;
		for (double[] feat : data1) {
			if (SimpleTester.evaluate(feat)<0) {
				correct1++;
			} else {
			}
		}
		
		for (double[] feat : data2) {
			if (SimpleTester.evaluate(feat)>=0) {
				correct2++;
			} else {
			}
		}
		
		int total = data1.size();
		System.out.println(String.format("%d/(%d): %d", correct1, total, correct1*100/total)+"%");
		System.out.println(String.format("%d/(%d): %d", correct2, total, correct2*100/total)+"%");
	}
	
	public static double evaluate(double[] feat) {
		// test equation
		double x = feat[0];
		double y = feat[1];
//		double value = 3*Math.pow(x, 2)-4*x*y-16*x+12*y+14-2*Math.log10(2);
		
		// with true parameter
//		double value = 0.25*Math.pow(x, 2)-2.5*x-1*x*y+2*y-0.0995;
		
		// with mle parameters
		// -0.2704*x^2+1.0721*x*y+2.9508*x+-2.4586*y+-0.2726
//		double value = 0.2677*Math.pow(x, 2)-1.0616*x*y-2.9214*x+2.4341*y+0.2724;
		
		// mle with new training data
		// 0.3285*x^2+-0.8759*x*y+-2.4765*x+1.8915*y+-0.2014
		// 0.3285*x^2+0.0179*y^2+-0.8759*x*y+-2.4765*x+1.8915*y+-0.2014
//		double value = 0.3285*Math.pow(x, 2)+0.0179*Math.pow(y, 2)-0.8759*x*y-2.4765*x+1.8915*y+0.2014;
		double value = 0.3285*Math.pow(x, 2)-0.8759*x*y-2.4765*x+1.8915*y+0.2014;
		
//		System.out.println(value);
		return value;
	}
}
