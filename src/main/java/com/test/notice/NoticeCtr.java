package com.test.notice;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.file.FileUtil;
import com.test.file.FileVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class NoticeCtr {
	
	@Autowired
	private NoticeSvc noticeSvc;
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeCtr.class);
	

	//삭제
	@RequestMapping(value = "/noticeDelete")
	public String noticeDelete(HttpServletRequest request) {
		String ntcNo = request.getParameter("ntcNo");
		
		noticeSvc.deleteNoticeOne(ntcNo);
		return "redirect:/noticeList";
	}
	
	// 글보기
	@RequestMapping(value = "/noticeRead") 
	public String noticeRead(HttpServletRequest request, ModelMap modelMap) throws Exception {
		
		String ntcNo = request.getParameter("ntcNo");
		
		NoticeVO noticeInfo = noticeSvc.selectNoticeOne(ntcNo);
		List<?> listview = noticeSvc.selectNoticeFile(ntcNo);
		
		modelMap.addAttribute("noticeInfo", noticeInfo);
		modelMap.addAttribute("listview", listview);
		
		return "/notice/noticeRead";
	}
	// 리스트
	@RequestMapping(value = "/noticeList")
	public String noticeList(ModelMap modelMap) throws Exception {
		
		List<?> listview = noticeSvc.selectNoticeList();
		modelMap.addAttribute("listview", listview);
		return "notice/noticeList";
	}
	
	// 저장
	@RequestMapping(value = "/noticeSave")
	public String noticeSave(HttpServletRequest request, NoticeVO noticeInfo) {
		String[] fileno = request.getParameterValues("fileno");
		
		FileUtil fs = new FileUtil();
		List<FileVO> filelist = fs.saveAllFiles(noticeInfo.getUploadfile());
		
		noticeSvc.insertNotice(noticeInfo, filelist, fileno);
		return "redirect:/noticeList";
	}
	
	// 폼 하나로 새글쓰기, 수정 
	@RequestMapping(value = "/noticeForm") 
	public String noticeForm(HttpServletRequest request, ModelMap modelMap) throws Exception {
		String ntcNo = request.getParameter("ntcNo");
		
		if( ntcNo != null ) {
			NoticeVO noticeInfo = noticeSvc.selectNoticeOne(ntcNo);
			List<?> listview = noticeSvc.selectNoticeFile(ntcNo);
			
			modelMap.addAttribute("noticeInfo", noticeInfo);
			modelMap.addAttribute("listview", listview);
		}
		
		return "/notice/noticeForm";
	}

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
