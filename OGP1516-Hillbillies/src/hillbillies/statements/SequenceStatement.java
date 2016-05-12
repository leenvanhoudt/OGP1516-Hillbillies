package hillbillies.statements;

import java.util.List;

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
		this.setExecutedState(true);
	}

	@Override
	public boolean containSelectedCube() {
		// TODO Auto-generated method stub
		for (MyStatement statement: this.statementList){
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
//		MyStatement parent = this.getParent();
//		if (parent instanceof SequenceStatement && 
//			((SequenceStatement) parent).getListSequence().size()-1 >
//				((SequenceStatement) parent).getListSequence().indexOf(this)){
//			SequenceStatement parentSeq = (SequenceStatement) parent;
//			return parentSeq.getListSequence().get(parentSeq.getListSequence().indexOf(this)+1);
//		}else if(parent != null){
//			return parent.getNext();
//		}else{
//			return null;
//		}
		
		for (MyStatement statement: this.statementList){
			if (statement.isExecuted()== false){
				return statement;
			}
		}
		this.setExecutedState(true);
		return null;
	}

	@Override
	public boolean isExecuted() {
		// TODO Auto-generated method stub
		return this.finished;
	}

	private boolean finished;

	@Override
	public void setExecutedState(boolean state) {
		// TODO Auto-generated method stub
		this.finished = state;
		if (state == false){
			for (MyStatement statement: this.statementList){
				statement.setExecutedState(false);
			}
		}
	}
}
