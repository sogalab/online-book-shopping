package com.fanyongliang.book;

/*------------------------------------------------------------------------- 
 * 作者：
 * 版本号：v1.0 
 * 本类主要用途描述： 
 * 管理员首页滚动广告控制器
 -------------------------------------------------------------------------*/


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fanyongliang.book.beans.AdvPhotoBean;
import com.fanyongliang.book.beans.UserInfoBean;
import com.fanyongliang.book.bll.AdvPhotoBll;
import com.fanyongliang.book.dao.prop.SimpleProperties;



@Controller
@RequestMapping("/admin")
public class AdminAdvertController {
	private AdvPhotoBll advPhotoBll = new AdvPhotoBll();
	/**
	 * 创建logger控制台日志显示对象
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(AdminAdvertController.class);

	/**
	 *  广告管理页
	 * 
	 * @return
	 */
	@RequestMapping("advert")
	public String navigation(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfoBean u = (UserInfoBean) session.getAttribute("adminInfo");
		if(u == null){
			return "admin/adminlogin";
		}else{
			List<AdvPhotoBean> advPhotoList = advPhotoBll.selectAllAdvPhoto();
			model.addAttribute("advPhotoList", advPhotoList);
			model.addAttribute("imagesPath", "http://www.fanyongliang.com/images/");
			return "admin/advert";
		}
	}
	/**
	 * 修改广告
	 * @param advPhotoBean
	 * @param model
	 * @param file
	 * @param req
	 * @return
	 */
	@RequestMapping("advertupdate")
	public String advertupdate(AdvPhotoBean advPhotoBean,Model model,
			MultipartFile file, HttpServletRequest req) {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest)req;
		Date currentTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String date = sdf.format(currentTime);
		String realPath = SimpleProperties.getInstance().getStringProperty(
				"output-path");
		String uuid = UUID.randomUUID().toString();
		String s="";
		if(file.getOriginalFilename()!=null && !"".equals(file.getOriginalFilename())){
			s= file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
		
			
			if (file.isEmpty()) {
				logger.info("File not upload");
			} else {
				logger.info("========================================");
				logger.info("The length of the file: " + file.getSize());
				logger.info("The file name: " + date + "-" + uuid + s);
				logger.info(request.getScheme() + "://" + request.getServerName()
						+ ":" + request.getServerPort() + request.getContextPath());
				logger.info("========================================");
				try {				
					FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, date + "-" + uuid + s));
				} catch (IOException e) {
					logger.error(e.toString());
				}
			}
			//date + "-" + uuid + s
			//图片名字
			advPhotoBean.setFileName(date + "-" + uuid + s);
		}
		advPhotoBll.updateAdvPhoto(advPhotoBean);
		return "redirect:advert";
	}
	
	/**
	 * 新增广告
	 * @param advPhotoBean
	 * @param model
	 * @param file
	 * @param req
	 * @return
	 */
	@RequestMapping("advertadd")
	public String advertadd(AdvPhotoBean advPhotoBean,Model model,
			MultipartFile file, HttpServletRequest req) {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest)req;
		Date currentTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String date = sdf.format(currentTime);
		String realPath = SimpleProperties.getInstance().getStringProperty(
				"output-path");
		String uuid = UUID.randomUUID().toString();
		String s="";
		if(file.getOriginalFilename()!=null && !"".equals(file.getOriginalFilename())){
			s= file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
		
			
			if (file.isEmpty()) {
				logger.info("File not upload");
			} else {
				logger.info("========================================");
				logger.info("The length of the file: " + file.getSize());
				logger.info("The file name: " + date + "-" + uuid + s);
				logger.info(request.getScheme() + "://" + request.getServerName()
						+ ":" + request.getServerPort() + request.getContextPath());
				logger.info("========================================");
				try {				
					FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, date + "-" + uuid + s));
				} catch (IOException e) {
					logger.error(e.toString());
				}
			}
			//date + "-" + uuid + s
			//图片名字
			advPhotoBean.setFileName(date + "-" + uuid + s);
		}
		advPhotoBll.addAdvPhoto(advPhotoBean);
		return "redirect:advert";
	}
	/**
	 * 删除广告
	 * @return
	 */
	@RequestMapping("advertdelete")
	public String advertdelete(Integer code) {
		advPhotoBll.deleteAdvPhoto(code);
		return "redirect:advert";
	}

}
