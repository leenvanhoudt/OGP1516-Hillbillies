package hillbillies.expressions;


import hillbillies.model.DijkstraPathFinding;
import hillbillies.scheduler.TaskComponents;

public class LogPositionExpression extends CubePositionExpression {

	private DijkstraPathFinding dijkstra = new DijkstraPathFinding();

	@Override
	public int[] evaluatePosition(TaskComponents taskComponents) throws Error{
		try{
			System.out.println("LOG EXP");
			this.dijkstra.setUnit(taskComponents.getUnit());
			return this.dijkstra.Dijkstra(1);
		} catch(Throwable e){
			throw new Error("no log found");
		}
	}


	@Override
	public boolean containSelectedCube() {
		return false;
	}

}
