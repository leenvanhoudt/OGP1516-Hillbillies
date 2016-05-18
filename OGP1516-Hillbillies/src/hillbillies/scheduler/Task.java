package hillbillies.scheduler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Faction;
import hillbillies.model.Unit;
import hillbillies.statements.AssignmentStatement;
import hillbillies.statements.BreakStatement;
import hillbillies.statements.SequenceStatement;
import hillbillies.statements.WhileStatement;

public class Task {

	private String nameTask;
	private int priority;
	private MyStatement statementactivity;
	private int[] selectedCube;

	
	public Task(String name, int priority, MyStatement activity, int[] selectedCube)throws IllegalArgumentException{
		System.out.println("task class");
		this.nameTask = name;
		this.priority = priority;
		this.statementactivity = activity;
		this.selectedCube = selectedCube;	
		if (!this.isWellFormed())
			throw new IllegalArgumentException();
	}
	
	//TODO fix it
	public boolean isWellFormed(){
		ArrayList<MyStatement> breakList = new ArrayList<MyStatement>();
		ArrayList<MyStatement> readVariableStatementList = new ArrayList<MyStatement>();
		System.out.println("task iswellformed");
		MyStatement current = this.getActivity().getNextWellFormed();
		System.out.println("default execute task");
		if (current == null){
			if (this.getActivity() instanceof BreakStatement)
				breakList.add(this.getActivity());
			if (this.getActivity().containReadVariableExpression())
				readVariableStatementList.add(this.getActivity());
			this.getActivity().setExecutedState(true);
		}
		while (!this.getActivity().isExecuted()){
			if (current.getNextWellFormed()==null || current.getNextWellFormed().isExecuted()){
				try{
					if (current instanceof BreakStatement)
						breakList.add(current);
					if (current.containReadVariableExpression())
						readVariableStatementList.add(current);
					current.setExecutedState(true);
					current = this.getActivity().getNextWellFormed();
				} catch(Throwable e){
					System.out.println("catch exception");
				}
			}else{
				current = current.getNextWellFormed();
			}
		}
		this.getActivity().setExecutedState(false);
		
		for (MyStatement breakStatement : breakList){
			MyStatement parent = breakStatement.getParent();
			while(parent !=null && !(parent instanceof WhileStatement)){
				parent = parent.getParent();
			}
			if (parent == null){
				return false;
			}
		}
		
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
	
	private boolean checkParentRightAssignmentStatement(MyStatement parent, MyStatement variableStatement){
		if (!(parent instanceof AssignmentStatement)){
			return false;
		}else if (variableStatement.getReadVariableExpression().getVariableName() ==
				((AssignmentStatement)parent).getVarName()){
			return true;
		}
		return false;
	}
	
	public Set<Scheduler> getSchedulersForTask(){
		System.out.println("task getschedulersfortask");
		Set<Scheduler> setOfSchedulers = new HashSet<Scheduler>();
		for (Faction faction: this.getAssignedUnit().getWorld().getActiveFactions()){
			if(faction.getScheduler().getScheduledTasks().contains(this)){
				setOfSchedulers.add(faction.getScheduler());
			}
		}
		return setOfSchedulers;
	}
	
	
	public Unit getAssignedUnit(){
		return this.assignedUnit;
	}
	
	public void setAssignedUnit(Unit unit){
		this.assignedUnit = unit;
	}
	
	private Unit assignedUnit;
	
	
	public String getName(){
		return this.nameTask;
	}
	
	public int getPriority(){
		return this.priority;
	}
	
	public void setPriority(int priority){
		if (priority > Integer.MIN_VALUE+100)
			this.priority = priority; 
		else{
			this.priority = Integer.MIN_VALUE;
		}
	}
	
	public MyStatement getActivity(){
		return this.statementactivity;
	}
	
	public int[] getSelectedCube(){
		return this.selectedCube;
	}
	
}
