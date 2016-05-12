package hillbillies.expressions;


import hillbillies.model.PathFinding;
import hillbillies.scheduler.TaskComponents;

public class BoulderPositionExpression extends CubePositionExpression {

	private PathFinding pathFinding;

	@Override
	public int[] evaluate(TaskComponents taskComponents) {
		System.out.println("BOULDER EXP");
		// TODO Auto-generated method stub
		this.pathFinding.setUnit(taskComponents.getUnit());
		return this.pathFinding.Dijkstra(0);
	}


	@Override
	public boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return false;
	}

}
