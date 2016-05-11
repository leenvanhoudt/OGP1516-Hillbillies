package hillbillies.statements;


import hillbillies.expressions.BooleanExpression;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class IfStatement extends MyStatement {

	private MyExpression expressionCondition;
	private MyStatement statementIfBody;
	private MyStatement statementElseBody;

	public IfStatement(MyExpression condition, MyStatement ifBody, MyStatement elseBody){
		this.expressionCondition = condition;
		this.statementIfBody = ifBody;
		this.statementElseBody = elseBody;
	}
	
	
	@Override
	public void execute(TaskComponents taskComponents) {
		System.out.println("IF STATEMENT");
		// TODO Auto-generated method stub
		if (!(this.expressionCondition instanceof BooleanExpression)){
			throw new Error("geen boolean expression");
		}
		BooleanExpression con = (BooleanExpression) this.expressionCondition;
		
		if (con.evaluate(taskComponents)){
			this.statementIfBody.execute(taskComponents);
		}else if(this.statementElseBody != null){
			System.out.println("ELSE BODY STATEMENT");
			this.statementElseBody.execute(taskComponents);
		}
	}


	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionCondition.containSelectedCube() ||
				this.statementIfBody.containSelectedCube() ||
				(this.statementElseBody != null && this.statementElseBody.containSelectedCube());
	}

}
