package hillbillies.scheduler;

import java.util.ArrayList;

import hillbillies.statements.AssignmentStatement;
import hillbillies.statements.BreakStatement;
import hillbillies.statements.SequenceStatement;
import hillbillies.statements.WhileStatement;

/**
 * A class which checks the wellformedness of a task.
 * 
 * @author Laura Vranken & Leen Van Houdt, 
 * 			2e bach Ingenieurswetenschappen: Objectgericht Programmeren 
 * 			link code repository: https://github.com/leenvanhoudt/OGP1516-Hillbillies
 *
 */
public class IsWellFormed {
	
	/**
	 * Check if a task is wellformed. This is the case when every breakStatement can break the
	 * while statement wherein he is used. Also every variable used in ReadVariableExpression,
	 * has to be assigned before it can be used.
	 * 
	 * @param task
	 * 		The task on which the wellformedness is checked.
	 * @post ...
	 * 		| the task is set to the given task.
	 * 		| new.task == task
	 */
	public IsWellFormed(Task task){
		this.task = task;
	}
	
	/**
	 * Initializing an object of the class Task.
	 */
	private Task task;
	
	/**
	 * Return true if the task is wellformed. This is the case when every breakStatement 
	 * can break the while statement wherein he is used. Also every variable used 
	 * in ReadVariableExpression, has to be assigned before it can be used.
	 * 
	 * @return ...
	 *  		Return true if the task is wellformed.
	 * @effect ...
	 * 		| check if the breakStatements are wellformed.
	 * 		| this.checkBreakStatements(breakList)
	 * @effect ...
	 * 		| check if the ReadVariableExpressions are wellformed.
	 * 		| this.checkReadVariableExpressions(readVariableExpressionList)
	 */
	public boolean CheckWellFormedness(){
		ArrayList<MyStatement> breakList = new ArrayList<MyStatement>();
		ArrayList<MyStatement> readVariableExpressionList = new ArrayList<MyStatement>();
		MyStatement current = this.task.getActivity();
		if (current.getNextWellFormed() == null){
			if (this.task.getActivity() instanceof BreakStatement)
				breakList.add(this.task.getActivity());
			if (this.task.getActivity().containReadVariableExpression())
				readVariableExpressionList.add(this.task.getActivity());
			this.task.getActivity().setExecutedState(true);
		}
		else{
			this.findBreakAndReadVariableStatements(current, breakList, readVariableExpressionList);
		}
		this.task.getActivity().setExecutedState(false);
		
		if (!this.checkBreakStatements(breakList))
			return false;
		
		if (!this.checkReadVariableExpressions(readVariableExpressionList))
			return false;
	
		return true;
	}
	
	/**
	 * Save all BreakStatements and all statements where ReadVariable is used in, 
	 * in an ArrayList.
	 * 
	 * @param current
	 * 		The current statement that we are checking.
	 * @param breakList
	 * 		The list where all BreakStatements are saved in.
	 * @param readVariableExpressionList
	 * 		The list where all statements which contain a readVariable, are saved in.
	 * @effect ...
	 * 		| add statements if they are an instance of BreakStatement.
	 * 		| breakList.add(current)
	 * @effect ...
	 * 		| add statements which contain a readVariableExpression.
	 * 		| readVariableExpressionList.add(current)
	 */
	private void findBreakAndReadVariableStatements(MyStatement current, 
			ArrayList<MyStatement> breakList, ArrayList<MyStatement> readVariableExpressionList){
		while (!this.task.getActivity().isExecuted() && current != null){
			if (current.getNextWellFormed()==null || current.getNextWellFormed().isExecuted()){
				if (current instanceof BreakStatement){
					breakList.add(current);
				}
				if (current.containReadVariableExpression()){
					readVariableExpressionList.add(current);
				}
				current.setExecutedState(true);
				current = this.task.getActivity();
			}else{
				current = current.getNextWellFormed();
			}
		}
	}
	
	/**
	 * Check if all breakStatements have a parent that is a WhileStatement.
	 * 
	 * @param breakList
	 * 		The list where all BreakStatements are saved in.
	 * @return ...
	 * 		| Return true if every BreakStatement has a WhileStatement parent.
	 * 		| Return false if no parent is found.
	 */
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
	
	/**
	 * Check if all variables of ReadVariableExpression that are used in statements, are
	 * assigned before use.
	 * 
	 * @param readVariableExpressionList
	 * 		The list where all statements which contain a readVariable, are saved in.
	 * @return ...
	 * 		| Return true if every variable is assigned first.
	 */
	private boolean checkReadVariableExpressions(ArrayList<MyStatement> readVariableExpressionList){
		for (MyStatement variableStatement: readVariableExpressionList){
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
	
	/**
	 * Check if variable has an AssignStatement as parent and that it is the right one, 
	 * saving the right variable.
	 * 
	 * @param parent
	 * 		The parent of which is checked of it is the right AssignmentStatement.
	 * @param variableStatement
	 * 		The statement which contains the variable.
	 * @return ...
	 * 		| Return true if the right AssignmentStatement is found.
	 */
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
