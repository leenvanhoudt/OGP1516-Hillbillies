package hillbillies.model;


/**
 * A class of boulders with their weight and position. Boulders can fall and be picked up.
 * 
 * @author Laura Vranken & Leen Van Houdt, 
 * 			2e bach Ingenieurswetenschappen: Objectgericht Programmeren 
 * 			link code repository: https://github.com/leenvanhoudt/OGP1516-Hillbillies
 * @invar The position of each boulder must be a valid position for any boulder.
 *      | isValidPosition(getPosition())
 */
public class Boulder extends Material{
	
	public Boulder(){
		super();
	}
}
