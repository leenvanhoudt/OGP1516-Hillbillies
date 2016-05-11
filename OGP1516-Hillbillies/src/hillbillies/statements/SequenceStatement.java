package hillbillies.statements;

import java.util.List;

import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class SequenceStatement extends MyStatement {

	private List<MyStatement> statementList;

	public SequenceStatement(List<MyStatement> statements){
		this.statementList = statements;
	}
	
	@Override
	public void execute(TaskComponents taskComponents) {
		System.out.println("SEQUENCE");
		for (MyStatement statement: this.statementList){
			statement.execute(taskComponents);
		}
	}

	@Override
	public Boolean containSelectedCube() {
		// TODO Auto-generated method stub
		for (MyStatement statement: this.statementList){
			if (statement.containSelectedCube()){
				return true;
			}
		}
		return false;
	}

}
