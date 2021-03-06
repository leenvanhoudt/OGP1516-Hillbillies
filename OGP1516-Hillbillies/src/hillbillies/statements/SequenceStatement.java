package hillbillies.statements;

import java.util.List;

import hillbillies.expressions.ReadVariableExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.TaskComponents;

public class SequenceStatement extends MyStatement {

	private List<MyStatement> statementList;

	public SequenceStatement(List<MyStatement> statements){
		this.statementList = statements;
		for (MyStatement statement: statements){
			statement.setParent(this);
		}
	}
	
	@Override
	public void execute(TaskComponents taskComponents) {
		this.setExecutedState(true);
	}

	@Override
	public boolean containSelectedCube() {
		for (MyStatement statement: this.getListSequence()){
			if (statement.containSelectedCube()){
				return true;
			}
		}
		return false;
	}

	public List<MyStatement> getListSequence(){
		return this.statementList;
	}
	
	@Override
	public MyStatement getNext(TaskComponents taskComponents) {		
		for (MyStatement statement: this.getListSequence()){
			if (!statement.isExecuted()){
				return statement;
			}
		}
		this.setExecutedState(true);
		return null;
	}
	
	@Override
	public MyStatement getNextWellFormed() {
		for (MyStatement statement: this.getListSequence()){
			if (!statement.isExecuted()){
				return statement;
			}
		}
		return null;
	}

	@Override
	public boolean isExecuted() {
		return this.finished;
	}

	private boolean finished;

	@Override
	public void setExecutedState(boolean state) {
		this.finished = state;
		for (MyStatement statement: this.getListSequence()){
			statement.setExecutedState(state);
		}
	}

	@Override
	public boolean containReadVariableExpression() {
		return false;
	}

	@Override
	public ReadVariableExpression getReadVariableExpression() {
		return null;
	}

}
