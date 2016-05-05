package hillbillies.statements;


import hillbillies.expressions.BooleanExpression;
import hillbillies.model.MyExpression;
import hillbillies.model.MyStatement;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class PrintStatement extends MyStatement{
	
	private MyExpression expressionValue;
	private SourceLocation sourceLocation;

	public PrintStatement(MyExpression value, SourceLocation sourceLocation){
		this.expressionValue = value;
		this.sourceLocation = sourceLocation;
	}

	@Override
	public void execute(World world, Unit unit, int[] selectedCube,SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		System.out.println(this.expressionValue.evaluate(world, unit, selectedCube, sourceLocation));
	}

}
