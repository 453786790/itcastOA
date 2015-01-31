package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.Template;

public interface ITemplateService {

	List<Template> findAll();

	void save(Template model);

	void delete(Integer id);

	Template getById(Integer id);

	void update(Template template);

}
