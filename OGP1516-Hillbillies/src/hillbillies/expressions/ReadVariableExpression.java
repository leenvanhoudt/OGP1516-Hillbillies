package hillbillies.expressions;

import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.TaskComponents;

public class ReadVariableExpression extends MyExpression {

	private String varName;

	public ReadVariableExpression(String variableName){
		this.varName = variableName;
	}

	@Override
	public Object evaluate(TaskComponents taskComponents) {
		System.out.println("READVARIABLE EXP");
		// TODO Auto-generated method stub
		return taskComponents.getValue(this.varName);
	}

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		return false;
	}


}
