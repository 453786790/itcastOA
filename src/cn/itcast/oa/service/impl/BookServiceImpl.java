package cn.itcast.oa.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.dao.IBookDao;
import cn.itcast.oa.domain.Book;
import cn.itcast.oa.service.IBookService;

@Service
@Transactional
public class BookServiceImpl implements IBookService{
	@Resource
    private IBookDao bookDao;
	public void save(Book k) {
		bookDao.save(k);
	}

}
