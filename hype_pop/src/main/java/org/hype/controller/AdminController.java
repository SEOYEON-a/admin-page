package org.hype.controller;

import java.sql.Date;
import java.util.List;

import org.hype.domain.goodsVO;
import org.hype.domain.popStoreVO;
import org.hype.domain.signInVO;
import org.hype.service.GoodsService;
import org.hype.service.MemberService;
import org.hype.service.PopUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private PopUpService pservice;
	
	@Autowired
	private GoodsService gservice;
	
	@Autowired
	private MemberService mservice;
	
	@GetMapping("/adminPopUp")
	public String adminPopUp() {		
		
		System.out.println("관리자 페이지 이동");
		
		return "/admin/adminMain";
	}
	
	@GetMapping("/askList")
	public String askList() {		
		
		System.out.println("문의 리스트 페이지 이동");
		
		return "/admin/askListCheck";
	}
	
	@GetMapping("/goodsRegister")
	public String goodsRegister() {		
		
		System.out.println("굿즈 등록 이동");
		
		return "/admin/goodsRegister";
	}
	
	// 팝업스토어 리스트 출력
	@GetMapping(value ="/psList",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
					   MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<popStoreVO>> getList() {
				
		log.info("팝업스토어 리스트 전체 출력 : " );
		return new ResponseEntity<List<popStoreVO>>(pservice.getList(), HttpStatus.OK);
	}
	
	// 굿즈 리스트 출력
	@GetMapping(value ="/gList",
	produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
			   MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<goodsVO>> getGList() {
		
		log.info("굿즈 리스트 전체 출력 : " );
		return new ResponseEntity<List<goodsVO>>(gservice.getGList(), HttpStatus.OK);
	}
	
	// 회원 리스트 출력
	@GetMapping(value ="/mList",
	produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
			   MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<signInVO>> getMList() {
		
		log.info("회원 리스트 전체 출력 : " );
		return new ResponseEntity<List<signInVO>>(mservice.getMList(), HttpStatus.OK);
	}
}
