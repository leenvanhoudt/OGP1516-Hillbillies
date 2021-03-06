package hillbillies.expressions;


import java.util.NoSuchElementException;
import hillbillies.scheduler.TaskComponents;

@SuppressWarnings("hiding")
public class NextToPositionExpression<E extends CubePositionExpression, ReadVariableExpression> 
	extends CubePositionExpression{
	
	private CubePositionExpression expressionPosition;
	private ReadVariableExpression expressionVariablePosition;
	
	public NextToPositionExpression(CubePositionExpression position){
		this.expressionPosition = position;
	}
	
	public NextToPositionExpression(ReadVariableExpression position){
		this.expressionVariablePosition = position;
	}

	@Override
	public int[] evaluatePosition(TaskComponents taskComponents) throws NoSuchElementException {
		int[] unitPosition;
		if (this.expressionVariablePosition != null)
			unitPosition = ((ICubePositionExpression) this.expressionVariablePosition)
				.evaluatePosition(taskComponents);
		else
			unitPosition = this.expressionPosition.evaluatePosition(taskComponents);
		
		for (int i=-1;i<2;i+=2){
			if ((taskComponents.getWorld().isPassable(unitPosition[0]+i, unitPosition[1], unitPosition[2]) &&
					!(taskComponents.getUnit().isFallingPosition(unitPosition[0]+i, unitPosition[1], unitPosition[2])))&&
					(unitPosition[0]+i<taskComponents.getWorld().getNbCubesX()) && unitPosition[0]+i>=0){
				return new int[]{unitPosition[0]+i,unitPosition[1],unitPosition[2]};
			}
		}
		
		for (int i=-1;i<2;i+=2){
			if ((taskComponents.getWorld().isPassable(unitPosition[0], unitPosition[1]+i, unitPosition[2]) &&
					!(taskComponents.getUnit().isFallingPosition(unitPosition[0], unitPosition[1]+i, unitPosition[2])))&&
					(unitPosition[1]+i<taskComponents.getWorld().getNbCubesY()) && unitPosition[1]+i>=0){
				return new int[]{unitPosition[0],unitPosition[1]+i,unitPosition[2]};
			}
		}
		
		for (int i=-1;i<2;i+=2){
			if ((taskComponents.getWorld().isPassable(unitPosition[0], unitPosition[1], unitPosition[2]+i) &&
					!(taskComponents.getUnit().isFallingPosition(unitPosition[0], unitPosition[1], unitPosition[2]+i)))&&
					(unitPosition[2]+i<taskComponents.getWorld().getNbCubesZ()) && unitPosition[2]+i>=0){
				return new int[]{unitPosition[0],unitPosition[1],unitPosition[2]+i};
			}
		}
		throw new NoSuchElementException();
	}

	@Override
	public boolean containSelectedCube() {
		if (this.expressionVariablePosition != null)
			return ((CubePositionExpression) this.expressionVariablePosition)
					.containSelectedCube();
		return this.expressionPosition.containSelectedCube();
	}

}
