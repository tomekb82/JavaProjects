package pl.qualent.allianz;

import java.util.Comparator;

import org.jbpm.task.query.TaskSummary;

public class TaskSummaryComparator implements Comparator<TaskSummary> {

	@Override
	public int compare(TaskSummary o1, TaskSummary o2) {
		return o2.getCreatedOn().compareTo(o1.getCreatedOn());
	}

}
