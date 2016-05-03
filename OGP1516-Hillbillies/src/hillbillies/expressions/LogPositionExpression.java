package hillbillies.expressions;


import hillbillies.model.Log;
import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class LogPositionExpression extends CubePositionExpression {

	private SourceLocation sourceLocation;

	public LogPositionExpression(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}

	@Override
	public int[] evaluate(World world, Unit unit, int[] selectedCube, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		double minimum = world.getNbCubesX() + world.getNbCubesY() + world.getNbCubesZ();
		Log closestLog = new Log();
		for (Log log: world.getLogs()){
			double distance = Math.sqrt(Math.pow(log.getPosition()[0]-unit.getPosition()[0], 2)+
					Math.pow(log.getPosition()[1]-unit.getPosition()[1], 2) +
					Math.pow(log.getPosition()[2]-unit.getPosition()[2], 2));
			if (distance < minimum){
				minimum = distance;
				closestLog = log;
			}
		}
		int[] logCubePosition = {(int)Math.floor(closestLog.getPosition()[0]), 
				(int)Math.floor(closestLog.getPosition()[1]), (int)Math.floor(closestLog.getPosition()[2])};
		return logCubePosition;
	}

}
