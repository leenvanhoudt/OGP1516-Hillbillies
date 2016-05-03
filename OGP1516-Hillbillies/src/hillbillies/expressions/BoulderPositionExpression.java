package hillbillies.expressions;


import hillbillies.model.Boulder;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class BoulderPositionExpression extends CubePositionExpression {

	private SourceLocation sourceLocation;

	public BoulderPositionExpression(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public int[] evaluate(World world, Unit unit, int[] selectedCube, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		double minimum = world.getNbCubesX() + world.getNbCubesY() + world.getNbCubesZ();
		Boulder closestBoulder = new Boulder();
		for (Boulder boulder: world.getBoulders()){
			double distance = Math.sqrt(Math.pow(boulder.getPosition()[0]-unit.getPosition()[0], 2)+
					Math.pow(boulder.getPosition()[1]-unit.getPosition()[1], 2) +
					Math.pow(boulder.getPosition()[2]-unit.getPosition()[2], 2));
			if (distance < minimum){
				minimum = distance;
				closestBoulder = boulder;
			}
		}
		int[] boulderCubePosition = {(int)Math.floor(closestBoulder.getPosition()[0]), 
				(int)Math.floor(closestBoulder.getPosition()[1]), (int)Math.floor(closestBoulder.getPosition()[2])};
		return boulderCubePosition;
	}

}
