package cn.itcast.oa.domain;

import org.jbpm.api.task.Task;
//用于在待我审批页面显示的数据
public class TaskView {
private Application application;
private Task task;
public TaskView() {
}

public TaskView(Application application, Task task) {
	this.application = application;
	this.task = task;
}

public Application getApplication() {
	return application;
}
public void setApplication(Application application) {
	this.application = application;
}
public Task getTask() {
	return task;
}
public void setTask(Task task) {
	this.task = task;
}
}
