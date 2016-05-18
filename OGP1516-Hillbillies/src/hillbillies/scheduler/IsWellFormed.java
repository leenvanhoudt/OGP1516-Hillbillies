package hillbillies.scheduler;

import java.util.ArrayList;

import hillbillies.statements.AssignmentStatement;
import hillbillies.statements.BreakStatement;
import hillbillies.statements.SequenceStatement;
import hillbillies.statements.WhileStatement;

public class IsWellFormed {
	
	private Task task;

	public IsWellFormed(Task task){
		this.task = task;
	}
	
	public boolean CheckWellFormedness(){
		ArrayList<MyStatement> breakList = new ArrayList<MyStatement>();
		ArrayList<MyStatement> readVariableStatementList = new ArrayList<MyStatement>();
		MyStatement current = this.task.getActivity().getNextWellFormed();
		if (current == null){
			if (this.task.getActivity() instanceof BreakStatement)
				breakList.add(this.task.getActivity());
			if (this.task.getActivity().containReadVariableExpression())
				readVariableStatementList.add(this.task.getActivity());
			this.task.getActivity().setExecutedState(true);
		}
		else{
			this.findBreakAndReadVariableStatements(current, breakList, readVariableStatementList);
		}
		this.task.getActivity().setExecutedState(false);
		
		if (!this.checkBreakStatements(breakList))
			return false;
		
		if (!this.checkReadVariableStatements(readVariableStatementList))
			return false;
	
		return true;
	}
	
	private void findBreakAndReadVariableStatements(MyStatement current, 
			ArrayList<MyStatement> breakList, ArrayList<MyStatement> readVariableStatementList) throws Error{
		while (!this.task.getActivity().isExecuted() && current != null){
			System.out.println(current);
			if (current.getNextWellFormed()==null || current.getNextWellFormed().isExecuted()){
				try{
					if (current instanceof BreakStatement)
						breakList.add(current);
					if (current.containReadVariableExpression())
						readVariableStatementList.add(current);
					current.setExecutedState(true);
					current = this.task.getActivity().getNextWellFormed();
				} catch(Throwable e){
					System.out.println("catch exception");
					throw new Error("error");
				}
			}else{
				current = current.getNextWellFormed();
			}
		}
	}
	
	private boolean checkBreakStatements(ArrayList<MyStatement> breakList){
		for (MyStatement breakStatement : breakList){
			MyStatement parent = breakStatement.getParent();
			while(parent !=null && !(parent instanceof WhileStatement)){
				parent = parent.getParent();
			}
			if (parent == null){
				return false;
			}
		}
		return true;
	}
	
	private boolean checkReadVariableStatements(ArrayList<MyStatement> readVariableStatementList){
		for (MyStatement variableStatement: readVariableStatementList){
			MyStatement parent = variableStatement;
			MyStatement checking = variableStatement;
			while (parent != null && !this.checkParentRightAssignmentStatement(parent, variableStatement)){
				parent = parent.getParent();
				if (parent instanceof SequenceStatement){
					for (int i = ((SequenceStatement) parent).getListSequence().indexOf(checking);
							i>=0; i--){
						if (((SequenceStatement) parent).getListSequence().get(i) instanceof AssignmentStatement &&
								variableStatement.getReadVariableExpression().getVariableName() ==
								((AssignmentStatement)((SequenceStatement) parent).getListSequence().get(i)).getVarName()){
							parent = ((SequenceStatement) parent).getListSequence().get(i);
							break;
						}
					}
				}
			}
			if (parent == null){
				return false;
			}
			
		}
		return true;
	}
	
	private boolean checkParentRightAssignmentStatement(MyStatement parent, 
			MyStatement variableStatement){
		if (!(parent instanceof AssignmentStatement)){
			return false;
		}else if (variableStatement.getReadVariableExpression().getVariableName() ==
				((AssignmentStatement)parent).getVarName()){
			return true;
		}
		return false;
	}
}
