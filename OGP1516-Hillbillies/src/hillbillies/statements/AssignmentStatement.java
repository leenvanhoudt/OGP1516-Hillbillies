package hillbillies.statements;


import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class AssignmentStatement extends MyStatement {
	
	private String variableName;
	private MyExpression expressionValue;

	public AssignmentStatement(String variableName, MyExpression value){
		this.variableName = variableName;
		this.expressionValue = value;
	}


	@Override
	public void execute(TaskComponents taskComponents) {
		System.out.println("ASSIGN STATEMENT");
		// TODO Auto-generated method stub
		Object value = this.expressionValue.evaluate(taskComponents);
		taskComponents.addVariable(this.variableName, value);
	}


	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return this.expressionValue.containSelectedCube();
	}

}
