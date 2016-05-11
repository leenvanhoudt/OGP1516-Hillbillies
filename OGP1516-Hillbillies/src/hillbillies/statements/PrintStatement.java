package hillbillies.statements;


import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class PrintStatement extends MyStatement{
	
	private MyExpression expressionValue;

	public PrintStatement(MyExpression value){
		this.expressionValue = value;
	}

	@Override
	public void execute(TaskComponents taskComponents) {
		System.out.println("PRINT STATEMENT");
		// TODO Auto-generated method stub
		System.out.println(this.expressionValue.evaluate(taskComponents));
	}

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionValue.containSelectedCube();
	}

}
