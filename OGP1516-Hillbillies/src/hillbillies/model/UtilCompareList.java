package hillbillies.model;

/**
 * Class for comparing 2 int[] with eachother.
 * 
 *  @author Laura Vranken & Leen Van Houdt, 
 * 			2e bach Ingenieurswetenschappen: Objectgericht Programmeren 
 * 			link code repository: https://github.com/leenvanhoudt/OGP1516-Hillbillies
 *
 */
public class UtilCompareList {
	
	/**
	 * Compare every component of an int list.
	 * 
	 * @param i1
	 * 		The first int[] we have to compare.
	 * @param i2
	 * 		The second int[] we have to compare.
	 * @return
	 * 		| Return true if all the components are the same.
	 */
	public static boolean compareIntList(int[] i1, int[] i2) throws IllegalArgumentException{
		if (i1.length != i2.length){
			throw new IllegalArgumentException();
		}
		for (int i=0; i<i1.length;i++){
			if (i1[i] != i2[i])
				return false;
		}
		return true;
	}

}
