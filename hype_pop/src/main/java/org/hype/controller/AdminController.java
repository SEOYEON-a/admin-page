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
	public ResponseEntity<List<popStoreVO>> getList(@RequestParam(required = false) String searchPs) {
				
		log.info("팝업스토어 리스트 출력 : " );
		
		List<popStoreVO> popStoreList;

	    // 검색어가 있을 경우
	    if (searchPs != null && !searchPs.isEmpty()) {
	        log.info("검색어 : " + searchPs);
	        popStoreList = pservice.getListBySearchPs(searchPs); // 검색 메서드 호출
	    } else {
	        popStoreList = pservice.getList(); // 전체 리스트 호출
	    }
	    
		return new ResponseEntity<List<popStoreVO>>(popStoreList, HttpStatus.OK);
	}
	
	// 팝업스토어 이름 클릭 시 팝업스토어 수정 페이지로 이동
	@GetMapping("/popUpUpdate")
	public String updatePopUp(@RequestParam("psNo") int psNo, Model model) {
	    log.info("팝업스토어 수정 페이지로 이동: psNo = " + psNo);
	    
	    // 해당 psNo에 대한 팝업스토어 정보 조회
	    popStoreVO popStore = pservice.getPopStoreById(psNo);
	    if (popStore != null) {
	        model.addAttribute("popStore", popStore); // JSP에서 사용하기 위해 모델에 추가
	        return "admin/popUpUpdate"; // JSP 파일 경로
	    } else {
	        // 해당 ID의 팝업스토어가 없는 경우 처리
	        return "redirect:/admin/psList";
	    }
	}
	
	// 상품 리스트 출력
	@GetMapping(value ="/gList",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
					   MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<goodsVO>> getGList(@RequestParam(required = false) String searchGs) {
		
		log.info("상품 리스트 출력 : " );
		
		List<goodsVO> goodsList;
		
		// 검색어가 있을 경우
	    if (searchGs != null && !searchGs.isEmpty()) {
	        log.info("검색어 : " + searchGs);
	        goodsList = gservice.getListBySearchGs(searchGs); // 검색 메서드 호출
	    } else {
	    	goodsList = gservice.getGList(); // 전체 리스트 호출
	    }
		return new ResponseEntity<List<goodsVO>>(goodsList, HttpStatus.OK);
	}
	
	// 굿즈 이름 클릭 시 굿즈 정보 수정 페이지로 이동
	@GetMapping("/goodsUpdate")
	public String updateGoods(@RequestParam("gNo") int gNo, Model model) {
	    log.info("굿즈 정보 수정 페이지로 이동: gNo = " + gNo);
	    
	    // 해당 psNo에 대한 팝업스토어 정보 조회
	    goodsVO goods = gservice.getGoodsById(gNo);
	    if (goods != null) {
	        model.addAttribute("goods", goods); // JSP에서 사용하기 위해 모델에 추가
	        return "admin/goodsUpdate"; // JSP 파일 경로
	    } else {
	        // 해당 ID의 굿즈가 없는 경우 처리
	        return "redirect:/admin/gList";
	    }
	}
	
	// 회원 리스트 출력
	@GetMapping(value ="/mList",
	produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
			   MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<signInVO>> getMList(@RequestParam(required = false) String searchMs) {
		
		log.info("회원 리스트 출력 : " );
		
		List<signInVO> memberList;
		
		// 검색어가 있을 경우
	    if (searchMs != null && !searchMs.isEmpty()) {
	        log.info("검색어 : " + searchMs);
	        memberList = mservice.getListBySearchMs(searchMs); // 검색 메서드 호출
	    } else {
	    	memberList = mservice.getMList(); // 전체 리스트 호출
	    }
		
		return new ResponseEntity<List<signInVO>>(memberList, HttpStatus.OK);
	}
	
	// 회원 아이디 클릭 시 회원 정보 수정 페이지로 이동
	@GetMapping("/memberUpdate")
	public String updateMembers(@RequestParam("userId") String userId, Model model) {
	    log.info("회원 정보 수정 페이지로 이동: userId = " + userId);
	    
	    // 해당 psNo에 대한 팝업스토어 정보 조회
	    signInVO members = mservice.getMembersById(userId);
	    if (members != null) {
	        model.addAttribute("members", members); // JSP에서 사용하기 위해 모델에 추가
	        return "admin/memberUpdate"; // JSP 파일 경로
	    } else {
	        // 해당 ID의 회원이 없는 경우 처리
	        return "redirect:/admin/mList";
	    }
	}

	// 문의 리스트 확인 버튼 클릭 시 문의 리스트 확인 페이지로 이동
	@GetMapping("/askListCheck")
	public ResponseEntity<String> checkAskList() {
	    String url = "/admin/askList"; // 이동할 URL (실제 JSP 파일 경로가 아니라, 클라이언트가 요청할 URL (이름은 임의로 설정))
	    return new ResponseEntity<String>(url, HttpStatus.OK);
	}
	
	// 상품 상태 조회 버튼 클릭 시 상품 상태 조회 페이지로 이동
	@GetMapping("/goodsState")
	public ResponseEntity<String> checkGoodsState() {
		String url = "/admin/goodsState"; // 이동할 URL (실제 JSP 파일 경로가 아니라, 클라이언트가 요청할 URL (이름은 임의로 설정))
		return new ResponseEntity<String>(url, HttpStatus.OK);
	}
}
