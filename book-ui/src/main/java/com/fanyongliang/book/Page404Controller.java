package com.fanyongliang.book;

/*------------------------------------------------------------------------- 
 * 作者：
 * 版本号：v1.0 
 * 本类主要用途描述： 
 * 404
 -------------------------------------------------------------------------*/



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller

public class Page404Controller {
	/**
	 * 创建logger控制台日志显示对象
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(Page404Controller.class);

	/**
	 * 404错误页
	 * 
	 * @return
	 */
	@RequestMapping("/error")
	public String index(Model model) {
		logger.info("PageNotFound!路径发生错误~");
		return "404";
	}

}
