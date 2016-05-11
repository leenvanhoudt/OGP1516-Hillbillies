package hillbillies.scheduler;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {

	@Override
	public int compare(Task first, Task second) {
		if (first.getPriority() < second.getPriority())
			return 1;
		else if (first.getPriority() == second.getPriority())
			return 0;
		else {
			return -1;
		}
	}

}

