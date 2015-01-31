package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.dao.IDeparementDao;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.service.IDeparementService;

@Service
@Transactional
public class DeparementServiceImp implements IDeparementService {
	@Resource
	private IDeparementDao deparementDao;

	public List<Department> findAll() {
		return deparementDao.findAll();
	}

	public void delete(Integer integer) {
		deparementDao.delete(integer);
	}

	public Department getById(Integer parentId) {
		return deparementDao.getById(parentId);
	}

	public void save(Department model) {
		deparementDao.save(model);
	}

	public void update(Department department) {
		deparementDao.update(department);
	}

	public List<Department> findTopList() {
		
		return deparementDao.findTopList();
	}


	public List<Department> findChildrenList(Integer parentId) {
		return deparementDao.findChildrenList(parentId);
	}

}
