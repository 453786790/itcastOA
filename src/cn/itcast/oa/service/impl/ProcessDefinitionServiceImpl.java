package cn.itcast.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.service.IProcessDefinitionService;

@Service
@Transactional
public class ProcessDefinitionServiceImpl implements IProcessDefinitionService {
	@Resource
	private ProcessEngine processEngine;

	@Override
	public List<ProcessDefinition> findLastList() {
		ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
		query.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION);//版本升
		List<ProcessDefinition> list = query.list();
		Map<Object ,ProcessDefinition> map=new HashMap<Object, ProcessDefinition>();
		for(ProcessDefinition pd:list){
			map.put(pd.getKey(), pd);
			}
		return new ArrayList<ProcessDefinition>(map.values());
	}

	@Override
	public void deploy(File uploadFile) {
		NewDeployment deployment = processEngine.getRepositoryService().createDeployment();
		ZipInputStream zipInputStream=null;
		try {
			zipInputStream = new ZipInputStream(new FileInputStream(uploadFile));
			deployment.addResourcesFromZipInputStream(zipInputStream);
			deployment.deploy();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(zipInputStream!=null){
				try {
					zipInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void deleteByKey(String key) {
		ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(key);
		List<ProcessDefinition> list = query.list();
		for(ProcessDefinition pd:list){
			processEngine.getRepositoryService().deleteDeployment(pd.getDeploymentId());	
		}
	}

	@Override
	public InputStream getImageStream(String id) {
		ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
		query.processDefinitionId(id);//过滤条件
	    ProcessDefinition processDefinition = query.uniqueResult();
		return processEngine.getRepositoryService().getResourceAsStream(processDefinition.getDeploymentId(),processDefinition.getImageResourceName());
	}
}
