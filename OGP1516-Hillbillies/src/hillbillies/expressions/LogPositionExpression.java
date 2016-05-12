package hillbillies.expressions;


import hillbillies.model.PathFinding;
import hillbillies.scheduler.TaskComponents;

public class LogPositionExpression extends CubePositionExpression {

	private PathFinding pathFinding;

	@Override
	public int[] evaluate(TaskComponents taskComponents) {
		System.out.println("LOG EXP");
		// TODO Auto-generated method stub
		this.pathFinding.setUnit(taskComponents.getUnit());
		return this.pathFinding.Dijkstra(1);
	}


	@Override
	public boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return false;
	}

}
