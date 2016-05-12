package hillbillies.expressions;


import java.util.NoSuchElementException;
import hillbillies.scheduler.TaskComponents;

public class NextToPositionExpression<E extends CubePositionExpression> 
	extends CubePositionExpression{

	
	private CubePositionExpression expressionPosition;

	public NextToPositionExpression(CubePositionExpression position){
		this.expressionPosition = position;
	}

	@Override
	public int[] evaluate(TaskComponents taskComponents) throws NoSuchElementException {
		System.out.println("NEXTO EXP");
		// TODO Auto-generated method stub
		int[] unitPosition = this.expressionPosition.evaluate(taskComponents);
		for (int i=-1;i<2;i+=2){
			if ((taskComponents.getWorld().isPassable(unitPosition[0]+i, unitPosition[1], unitPosition[2]) &&
					!(taskComponents.getUnit().isFallingPosition(unitPosition[0]+i, unitPosition[1], unitPosition[2])))&&
					(unitPosition[0]+i<taskComponents.getWorld().getNbCubesX()) && unitPosition[0]+i>=0){
				System.out.println("next to pos " + unitPosition[0]+i + " " +unitPosition[1] + " "+ unitPosition[2]);
				return new int[]{unitPosition[0]+i,unitPosition[1],unitPosition[2]};
			}
		}
		
		for (int i=-1;i<2;i+=2){
			if ((taskComponents.getWorld().isPassable(unitPosition[0], unitPosition[1]+i, unitPosition[2]) &&
					!(taskComponents.getUnit().isFallingPosition(unitPosition[0], unitPosition[1]+i, unitPosition[2])))&&
					(unitPosition[1]+i<taskComponents.getWorld().getNbCubesY()) && unitPosition[1]+i>=0){
				System.out.println("next to pos " + unitPosition[0] + " " +unitPosition[1]+i + " "+ unitPosition[2]);
				return new int[]{unitPosition[0],unitPosition[1]+i,unitPosition[2]};
			}
		}
		
		for (int i=-1;i<2;i+=2){
			if ((taskComponents.getWorld().isPassable(unitPosition[0], unitPosition[1], unitPosition[2]+i) &&
					!(taskComponents.getUnit().isFallingPosition(unitPosition[0], unitPosition[1], unitPosition[2]+i)))&&
					(unitPosition[2]+i<taskComponents.getWorld().getNbCubesZ()) && unitPosition[2]+i>=0){
				System.out.println("next to pos " + unitPosition[0] + " " +unitPosition[1] + " "+ unitPosition[2]+i);
				return new int[]{unitPosition[0],unitPosition[1],unitPosition[2]+i};
			}
		}
		System.out.println("no next to pos");
		throw new NoSuchElementException();
	}

	@Override
	public boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionPosition.containSelectedCube();
	}

}
