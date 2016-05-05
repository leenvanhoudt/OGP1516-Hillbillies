package hillbillies.expressions;


import java.util.NoSuchElementException;

import hillbillies.common.internal.controller.CubeSelectionMode.Cube;
import hillbillies.model.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class NextToPositionExpression extends CubePositionExpression{

	
	private MyExpression expressionPosition;
	private SourceLocation sourceLocation;

	public NextToPositionExpression(MyExpression position, SourceLocation sourceLocation){
		this.expressionPosition = position;
		this.sourceLocation = sourceLocation;
	}

	@Override
	public int[] evaluate(World world, Unit unit, int[] selectedCube, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		if (!(this.expressionPosition instanceof CubePositionExpression)){
			throw new Error("no position expression");
		}
		CubePositionExpression position = (CubePositionExpression) this.expressionPosition;
		int[] unitPosition = position.evaluate(world, unit, selectedCube, sourceLocation);
		
		for (int i=-1;i<2;i+=2){
			if ((world.isPassable(unitPosition[0]+i, unitPosition[1], unitPosition[2]) &&
					!(unit.isFallingPosition(unitPosition[0]+i, unitPosition[1], unitPosition[2])))&&
					(unitPosition[0]+i<world.getNbCubesX()) && unitPosition[0]+i>=0){
				return new int[]{unitPosition[0]+i,unitPosition[1],unitPosition[2]};
			}
		}
		
		for (int i=-1;i<2;i+=2){
			if ((world.isPassable(unitPosition[0], unitPosition[1]+i, unitPosition[2]) &&
					!(unit.isFallingPosition(unitPosition[0], unitPosition[1]+i, unitPosition[2])))&&
					(unitPosition[1]+i<world.getNbCubesY()) && unitPosition[1]+i>=0){
				return new int[]{unitPosition[0],unitPosition[1]+i,unitPosition[2]};
			}
		}
		
		for (int i=-1;i<2;i+=2){
			if ((world.isPassable(unitPosition[0], unitPosition[1], unitPosition[2]+i) &&
					!(unit.isFallingPosition(unitPosition[0], unitPosition[1], unitPosition[2]+i)))&&
					(unitPosition[2]+i<world.getNbCubesZ()) && unitPosition[2]+i>=0){
				return new int[]{unitPosition[0],unitPosition[1],unitPosition[2]+i};
			}
		}
		throw new NoSuchElementException();
	}

}
