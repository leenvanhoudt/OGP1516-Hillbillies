package hillbillies.statements;


import hillbillies.expressions.BooleanExpression;
import hillbillies.model.MyExpression;
import hillbillies.model.MyStatement;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class IfStatement extends MyStatement {

	private MyExpression expressionCondition;
	private MyStatement statementIfBody;
	private MyStatement statementElseBody;
	private SourceLocation sourceLocation;

	public IfStatement(MyExpression condition, MyStatement ifBody, MyStatement elseBody, 
			SourceLocation sourceLocation){
		this.expressionCondition = condition;
		this.statementIfBody = ifBody;
		this.statementElseBody = elseBody;
		this.sourceLocation = sourceLocation;
	}
	
	
	@Override
	public void execute(World world, Unit unit,int[] selectedCube) {
		// TODO Auto-generated method stub
		if (!(this.expressionCondition instanceof BooleanExpression)){
			throw new Error("geen boolean expression");
		}
		BooleanExpression con = (BooleanExpression) this.expressionCondition;
		
		if (con.evaluate(world, unit, selectedCube, sourceLocation)){
			this.statementIfBody.execute(world, unit, selectedCube);
		}else{
			this.statementElseBody.execute(world, unit, selectedCube);
		}
	}

}
