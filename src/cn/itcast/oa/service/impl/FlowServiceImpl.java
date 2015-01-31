package cn.itcast.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.TaskQuery;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.domain.Application;
import cn.itcast.oa.domain.ApproveInfo;
import cn.itcast.oa.domain.TaskView;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.IApplicationService;
import cn.itcast.oa.service.IApproveInfoService;
import cn.itcast.oa.service.IFlowService;
@Service
@Transactional
public class FlowServiceImpl implements IFlowService {
	@Resource
    private IApplicationService applicationService;
	@Resource
	private IApproveInfoService approveInfoService;
	@Resource
	private ProcessEngine processEngine;
	public void submit(Application application) {
		//保存申请
		applicationService.save(application);
		//启动流程实例
		Map<String ,Application> map=new HashMap<String ,Application>();
		map.put("application", application);
		ProcessInstance pi = processEngine.getExecutionService().startProcessInstanceByKey(application.getTemplate().getProcessKey(), map);
		//处理任务(提交申请)\
		TaskQuery query = processEngine.getTaskService().createTaskQuery();
		query.processInstanceId(pi.getId());
	   Task task = query.uniqueResult();
		processEngine.getTaskService().completeTask(task.getId());
	}
	//查询我的任务列表包装成taskview实体
	@Override
	public List<TaskView> findMyTaskList(User currentUser) {
		List<Task> taskList = processEngine.getTaskService().findPersonalTasks(currentUser.getLoginName());
        
		List<TaskView> list=new ArrayList<TaskView>();
		for(Task t:taskList){
			Application application = (Application)processEngine.getTaskService().getVariable(t.getId(), "application");
			TaskView taskView=new TaskView(application,t);
			list.add(taskView);
		}
		return list;
	}
	//审批处理
	@Override
	public void approve(ApproveInfo ai, String taskId) {
		Task task = processEngine.getTaskService().getTask(taskId);
		String executionId = task.getExecutionId();
		//保存审批信息
		approveInfoService.save(ai);
		//审批处理
		processEngine.getTaskService().completeTask(taskId);
		//重新获取该流程实例
		ProcessInstance pi = processEngine.getExecutionService().findProcessInstanceById(executionId);
		if(ai.getApproval()){//审批同意
			
			if(pi==null){
				ai.getApplication().setStatus(Application.STATUS_APPROVED);
			}
			
		}else{
			//不同意
			//修改申请状态 
			ai.getApplication().setStatus(Application.STATUS_UNAPPROVED);
			//如果 不是最后 一个结点  手动结束当前 流程实例
			if(pi!=null){
				processEngine.getExecutionService().endProcessInstance(pi.getId(), ProcessInstance.STATE_ENDED);
			}
		}
	}

}
