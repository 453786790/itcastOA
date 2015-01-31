package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.dao.ITemplateDao;
import cn.itcast.oa.domain.Template;
import cn.itcast.oa.service.ITemplateService;

@Service
@Transactional
public class TemplateServiceImpl implements ITemplateService {
	@Resource
	private ITemplateDao templateDao;

	public List<Template> findAll() {
		return templateDao.findAll();
	}

	public void save(Template model) {
		templateDao.save(model);
	}

	public void delete(Integer id) {
		templateDao.delete(id);
	}

	public Template getById(Integer id) {
		return templateDao.getById(id);
	}

	public void update(Template template) {
		templateDao.update(template);
	}
}
