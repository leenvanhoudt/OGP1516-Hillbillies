package hillbillies.statements;

import hillbillies.expressions.BooleanExpression;
import hillbillies.model.MyExpression;
import hillbillies.model.MyStatement;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class WhileStatement extends MyStatement {

	private MyExpression expressionCondition;
	private MyStatement statementBody;
	private SourceLocation sourceLocation;

	public WhileStatement(MyExpression condition, MyStatement body,
			SourceLocation sourceLocation){
		this.expressionCondition = condition;
		this.statementBody = body;
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public void execute(World world, Unit unit,int[] selectedCube) {
		// TODO Auto-generated method stub
		if (!(this.expressionCondition instanceof BooleanExpression)){
			throw new Error("no boolean expression");
		}
		BooleanExpression condition = (BooleanExpression) this.expressionCondition;
		
		while(condition.evaluate(world, unit, selectedCube, this.sourceLocation)){
			this.statementBody.execute(world, unit, selectedCube);
		}
	}

}
