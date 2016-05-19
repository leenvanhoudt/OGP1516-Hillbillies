package hillbillies.model;

/**
 * An enumeration of cubeTypes.
 *    In its current definition, the class distinguishes between
 *    air, rock, tree and workshop.
 * @author laura
 *
 */
public enum CubeType {

	AIR{

		@Override
		public int getCubeType() {
			return 0;
		}
		
	},
	
	ROCK{

		@Override
		public int getCubeType() {
			return 1;
		}
		
	},
	
	TREE{

		@Override
		public int getCubeType() {
			return 2;
		}
		
	},
	
	WORKSHOP{

		@Override
		public int getCubeType() {
			return 3;
		}
		
	};
	
	public abstract int getCubeType();
}
