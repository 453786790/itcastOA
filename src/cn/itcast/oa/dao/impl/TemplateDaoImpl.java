package cn.itcast.oa.dao.impl;

import java.io.File;

import org.springframework.stereotype.Repository;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.dao.ITemplateDao;
import cn.itcast.oa.domain.Template;

@Repository
public class TemplateDaoImpl extends BaseDaoImpl<Template> implements
		ITemplateDao {

	public void delete(Integer id) {
		Template model = super.getById(id);
		String filePath = model.getFilePath();
		super.delete(id);
		// 删除文件
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
	}
}
