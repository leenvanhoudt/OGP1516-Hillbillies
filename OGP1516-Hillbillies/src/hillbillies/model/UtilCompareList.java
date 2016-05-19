package hillbillies.model;

public class UtilCompareList {
	
	public static boolean compareIntList(int[] i1, int[] i2){
		if (i1[0] == i2[0] && i1[1] == i2[1] && i1[2] == i2[2])
			return true;
		return false;
	}
	
	public static boolean compareDoubleList(double[] d1, double[] d2){
		if (d1[0] == d2[0] && d1[1] == d2[1] && d1[2] == d2[2])
			return true;
		return false;
	}

}
