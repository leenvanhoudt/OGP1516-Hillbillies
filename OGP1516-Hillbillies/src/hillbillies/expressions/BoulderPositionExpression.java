package hillbillies.expressions;


import hillbillies.model.DijkstraPathFinding;
import hillbillies.scheduler.TaskComponents;

public class BoulderPositionExpression extends CubePositionExpression {

	private DijkstraPathFinding dijkstra = new DijkstraPathFinding();

	@Override
	public int[] evaluatePosition(TaskComponents taskComponents) throws Error{
		System.out.println("BOULDER EXP");
		try{
			this.dijkstra.setUnit(taskComponents.getUnit());
			int[] pos = this.dijkstra.Dijkstra(0);
			return pos;
		} catch(Throwable e){
			throw new Error("no boulder found");
		}
	}


	@Override
	public boolean containSelectedCube() {
		return false;
	}

}
