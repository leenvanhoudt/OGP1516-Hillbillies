package hillbillies.model;

import java.util.Random;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of logs with their weight and position. A log can fall and be picked up.
 *
 * @author Laura Vranken & Leen Van Houdt, 
 * 			2e bach Ingenieurswetenschappen: Objectgericht Programmeren 
 * 			link code repository: https://github.com/leenvanhoudt/OGP1516-Hillbillies
 * 
 * @invar The position of each log must be a valid position for any log.
 *      | isValidPosition(getPosition())
 */
public class Log extends CarriedItem{
	
	public Log(){
		super();
	}
}
