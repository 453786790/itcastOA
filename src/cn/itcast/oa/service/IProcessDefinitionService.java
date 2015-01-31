package cn.itcast.oa.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.jbpm.api.ProcessDefinition;

public interface IProcessDefinitionService {

	List<ProcessDefinition> findLastList();

	void deploy(File uploadFile);

	void deleteByKey(String key);

	InputStream getImageStream(String id);

}
