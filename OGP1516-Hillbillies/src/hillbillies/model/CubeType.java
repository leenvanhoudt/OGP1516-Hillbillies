package hillbillies.model;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * An enumeration of cubeTypes.
 *    In its current definition, the class distinguishes between
 *    air, rock, tree and workshop.
 *    
 * @author Laura Vranken & Leen Van Houdt, 
 * 			2e bach Ingenieurswetenschappen: Objectgericht Programmeren 
 * 			link code repository: https://github.com/leenvanhoudt/OGP1516-Hillbillies
 *
 */
public enum CubeType {

	AIR{

		/**
		 * Return the number corresponding with the cubeType of air.
		 */
		@Override @Basic
		public int getCubeType() {
			return 0;
		}
		
	},
	
	ROCK{

		/**
		 * Return the number corresponding with the cubeType of rock.
		 */
		@Override @Basic
		public int getCubeType() {
			return 1;
		}
		
	},
	
	TREE{

		/**
		 * Return the number corresponding with the cubeType of tree.
		 */
		@Override @Basic
		public int getCubeType() {
			return 2;
		}
		
	},
	
	WORKSHOP{

		/**
		 * Return the number corresponding with the cubeType of workshop.
		 */
		@Override @Basic
		public int getCubeType() {
			return 3;
		}
		
	};
	
	/**
	 * Return the number corresponding with the cubeType.
	 */
	public abstract int getCubeType();
}
