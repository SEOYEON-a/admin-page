package org.hype.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hype.domain.noticeVO;
import org.hype.domain.qnaVO;
import org.hype.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/support")
public class SupportController {
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/noticeInfo") // 공지 상세 정보
	public String noticeInfoList(@RequestParam("noticeNo") int noticeNo, Model model) {
		 noticeVO noticeInfo = noticeService.getNoticeInfo(noticeNo);
	     model.addAttribute("noticeInfo", noticeInfo);
	     return "/customerService/noticeInfo";  
	}
	
}