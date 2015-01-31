package cn.itcast.oa.web.action;
import javax.annotation.Resource;

import org.jbpm.api.ProcessEngine;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.dao.IBookDao;
import cn.itcast.oa.domain.Book;

@SuppressWarnings("serial")
@Controller
@Scope(value="prototype")
public class BookAction extends BaseAction<Book>{
	@Resource
	private IBookDao bookDao;
	@Resource
	private ProcessEngine processEngine;
	
	
    public String save(){
//    	bookService.save(model);
    	System.out.println(processEngine);
    	return SUCCESS;
    }
}
