package hillbillies.scheduler;

import hillbillies.model.Unit;

public class TaskData {
	public String nameTask;
	public int priority;
	public MyStatement statementactivity;
	public int[] selectedCube;
	public Unit assignedUnit;

	public TaskData() {
	}
}