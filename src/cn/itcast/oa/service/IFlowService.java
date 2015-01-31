package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.Application;
import cn.itcast.oa.domain.ApproveInfo;
import cn.itcast.oa.domain.TaskView;
import cn.itcast.oa.domain.User;

public interface IFlowService {
	//提交申请
public void submit(Application application);

public List<TaskView> findMyTaskList(User currentUser);

public void approve(ApproveInfo ai, String taskId);

}
