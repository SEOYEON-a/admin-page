package org.hype.controller;

import java.io.File;   
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hype.domain.Criteria;
import org.hype.domain.PageDTO;
import org.hype.domain.goodsVO;
import org.hype.domain.pCatVO;
import org.hype.domain.pImgVO;
import org.hype.domain.popStoreVO;
import org.hype.domain.signInVO;
import org.hype.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService aservice;	
	
	@GetMapping("/adminPage")
	public String adminPopUp() {		
		
		System.out.println("관리자 페이지 이동");
		
		return "/admin/adminMain";
	}
	
	@GetMapping("/askList")
	public String askList() {		
		
		System.out.println("문의 리스트 페이지 이동");
		
		return "/admin/askListCheck";
	}
	
	// **관리자 페이지 영역**
	// 팝업스토어 리스트 출력 (header - 공통)
	// 페이징 처리 O
	@ResponseBody
	@GetMapping(value = "/psList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getList(Criteria cri, @RequestParam(required = false) String searchPs) {
	    log.warn(cri.getAmount());
	    log.warn(cri.getPageNum());
	    log.warn(searchPs);
	    
	    if (cri.getPageNum() == 0 || cri.getAmount() == 0) {
	        cri.setPageNum(1);
	        cri.setAmount(10);
	    }
	    
	    log.info(cri.getPageNum() + "/" + cri.getAmount());

	    int total = aservice.getPTotal(searchPs);  // 전체 스토어 수 
	    List<popStoreVO> list = aservice.getPList(cri, searchPs);  // 검색 결과
	    PageDTO pageMaker = new PageDTO(cri, total);  // 페이지 메이커
	    
	    
	    log.info("list : " + list);
	    log.info("total : " + total);
	    log.info("pageMaker : " + pageMaker);
	    
	    Map<String, Object> response = new HashMap<>();	
	    
	    response.put("list", list);
		response.put("total", total);
	    response.put("pageMaker", pageMaker);
	    
	    return ResponseEntity.ok(response);
	}
	
//	현재씨 코드 참조
//	@ResponseBody
//	@GetMapping(value = "/psList", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Map<String, Object>> getList(@RequestParam Map<String, Object> request) {
//		
//		log.info("팝업스토어 리스트 출력 : ");
//		
//		// 굳이? 
//		// xml에서 던질 때 넘어가는거라
//		Integer psNo = request.get("psNo") != null ? Integer.parseInt((String) request.get("psNo")) : null; // String에서 Integer로 변환
////        Integer startDate = Integer.parseInt(String.valueOf(request.get("psStartDate"))); // String에서 Integer로 변환
////        Integer endDate = Integer.parseInt(String.valueOf(request.get("psEndDate"))); // String에서 Integer로 변환
//		
//		
//		String psName = (String)request.get("psName"); 
//		Integer pageNum = request.get("pageNum") != null ? Integer.parseInt((String) request.get("pageNum")) : 1;  // 페이지 번호
//		Integer amount = request.get("amount") != null ? Integer.parseInt((String) request.get("amount")) : 1;  // 항목 수 
//		
//		System.out.println("Received request: " + request);
//
//		
//		// Criteria 객체 생성 및 설정
//		Criteria cri = new Criteria();
//		if (pageNum <= 0) {
//			cri.setPageNum(1); // 기본 페이지 번호 설정
//		} else {
//			cri.setPageNum(pageNum);
//		}
//		
//		if (amount <= 0) {
//			cri.setAmount(10); // 기본 항목 수 설정
//		}		
//		
//		List<popStoreVO> popStores = aservice.getList(psNo, psName, cri);
//		
//		Map<String, Object> response = new HashMap<>();
//		response.put("popUpStores", popStores);
//		response.put("totalReviews", aservice.getTotal()); // 전체 리뷰 수 추가
//		
//		return ResponseEntity.ok()
//				.contentType(MediaType.APPLICATION_JSON)
//				.body(response);
//	}
		
	// 팝업스토어 이름 클릭 시 팝업스토어 수정/삭제 페이지로 이동
	@GetMapping("/popUpUpdate")
	public String updatePopUp(@RequestParam("psNo") int psNo, Model model) {
	    log.info("팝업스토어 수정 페이지로 이동: psNo = " + psNo);
	    
	    // 해당 psNo에 대한 팝업스토어 정보 조회
	    popStoreVO popStore = aservice.getPopStoreById(psNo);
	    if (popStore != null) {
	        model.addAttribute("popStore", popStore); // JSP에서 사용하기 위해 모델에 추가
	        return "admin/psUpdateDelete"; // JSP 파일 경로
	    } else {
	        // 해당 ID의 팝업스토어가 없는 경우 처리
	        return "redirect:/admin/psList";
	    }
	}
	
	// 상품 리스트 출력 (header - 공통)
	// 페이징 O
	@ResponseBody
	@GetMapping(value ="/gList",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
					   MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Map<String, Object>> getGList(Criteria cri, @RequestParam(required = false) String searchGs) {
		log.warn(cri.getAmount());
		log.warn(cri.getPageNum());
		log.warn(searchGs);
		
		if (cri.getPageNum() == 0 || cri.getAmount() == 0) {
			cri.setPageNum(1);
			cri.setAmount(10);
		}
		
		log.info(cri.getPageNum() + "/" + cri.getAmount());
		
		int total = aservice.getGTotal(searchGs);  // 전체 스토어 수 
		List<goodsVO> list = aservice.getGList(cri, searchGs);  // 검색 결과
		PageDTO pageMaker = new PageDTO(cri, total);  // 페이지 메이커
		
		
		log.info("list : " + list);
		log.info("total : " + total);
		log.info("pageMaker : " + pageMaker);
		
		Map<String, Object> response = new HashMap<>();	
		
		response.put("list", list);
		response.put("total", total);
		response.put("pageMaker", pageMaker);
		
		return ResponseEntity.ok(response);
	}
	
	// 페이징 X
//	public ResponseEntity<List<goodsVO>> getGList(@RequestParam(required = false) String searchGs) {
//		
//		log.info("상품 리스트 출력 : " );
//		
//		List<goodsVO> goodsList;
//		
//		// 검색어가 있을 경우
//	    if (searchGs != null && !searchGs.isEmpty()) {
//	        log.info("검색어 : " + searchGs);
//	        goodsList = aservice.getListBySearchGs(searchGs); // 검색 메서드 호출
//	    } else {
//	    	goodsList = aservice.getGList(); // 전체 리스트 호출
//	    }
//		return new ResponseEntity<List<goodsVO>>(goodsList, HttpStatus.OK);
//	}
	
	// 굿즈 이름 클릭 시 굿즈 정보 수정/삭제 페이지로 이동
	@GetMapping("/goodsUpdate")
	public String updateGoods(@RequestParam("gNo") int gNo, Model model) {
	    log.info("굿즈 정보 수정 페이지로 이동: gNo = " + gNo);
	    
	    // 해당 psNo에 대한 팝업스토어 정보 조회
	    goodsVO goods = aservice.getGoodsById(gNo);
	    if (goods != null) {
	        model.addAttribute("goods", goods); // JSP에서 사용하기 위해 모델에 추가
	        return "admin/gUpdateDelete"; // JSP 파일 경로
	    } else {
	        // 해당 ID의 굿즈가 없는 경우 처리
	        return "redirect:/admin/gList";
	    }
	}
	
	// 회원 리스트 출력 (header - 공통)
	@ResponseBody
	@GetMapping(value ="/mList",
	produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
			   MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<signInVO>> getMList(@RequestParam(required = false) String searchMs) {
		
		log.info("회원 리스트 출력 : " );
		
		List<signInVO> memberList;
		
		// 검색어가 있을 경우
	    if (searchMs != null && !searchMs.isEmpty()) {
	        log.info("검색어 : " + searchMs);
	        memberList = aservice.getListBySearchMs(searchMs); // 검색 메서드 호출
	    } else {
	    	memberList = aservice.getMList(); // 전체 리스트 호출
	    }
		
		return new ResponseEntity<List<signInVO>>(memberList, HttpStatus.OK);
	}
	
	// 회원 아이디 클릭 시 회원 정보 수정 페이지로 이동
	@GetMapping("/memberUpdate")
	public String updateMembers(@RequestParam("userId") String userId, Model model) {
	    log.info("회원 정보 수정 페이지로 이동: userId = " + userId);
	    
	    // 해당 psNo에 대한 팝업스토어 정보 조회
	    signInVO members = aservice.getMembersById(userId);
	    if (members != null) {
	        model.addAttribute("members", members); // JSP에서 사용하기 위해 모델에 추가
	        return "admin/memberUpdate"; // JSP 파일 경로
	    } else {
	        // 해당 ID의 회원이 없는 경우 처리
	        return "redirect:/admin/mList";
	    }
	}
	
	//***footer 영역***
	// 문의 리스트 확인 버튼 클릭 시 문의 리스트 확인 페이지로 이동
	@GetMapping("/askListCheck")
	public String checkAskList() {
		return "admin/askListCheck"; // JSP 파일 경로
	}
				
	// 상품 상태 조회 버튼 클릭 시 상품 상태 조회 페이지로 이동
	@GetMapping("/goodsState")
	public String checkGoods() {
		return "admin/goodsState"; // JSP 파일 경로
	}
		
	// 등록하기 버튼 클릭 시 이동
	@GetMapping("/popUpRegister")
    public String popUpRegister() {
        return "admin/popUpRegister"; 
    }

    @GetMapping("/goodsRegister")
    public String goodsRegister() {
        return "admin/goodsRegister"; 
    }
    
    //***팝업스토어 등록 페이지 영역***
    // 이미지 파일 등록
 	// 비동기 통신 : @ResponseBody
    @ResponseBody
    @PostMapping(value = "/uploadAsyncAction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<pImgVO> uploadAsyncPost(@RequestParam("uploadFile") MultipartFile uploadFile) {
        log.info("upload Async post...");

        String uploadFolder = "C:\\upload"; // 기본 저장 경로
        
        File uploadPath = new File(uploadFolder);
        log.info("uploadPath : " + uploadPath);

        // 경로가 없으면 생성
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        log.info("-----------------------");
        log.info("Upload File Name : " + uploadFile.getOriginalFilename());
        log.info("Upload File Size : " + uploadFile.getSize());

        String uploadFileName = uploadFile.getOriginalFilename();
        uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
        log.info("only file name : " + uploadFileName);

        UUID uuid = UUID.randomUUID();
        uploadFileName = uuid.toString() + "_" + uploadFileName;

        try {
            File saveFile = new File(uploadPath, uploadFileName);
            uploadFile.transferTo(saveFile);

            pImgVO attachDto = new pImgVO();
            attachDto.setUuid(uuid.toString());
            attachDto.setUploadPath(uploadFolder); // 실제 경로 설정
            attachDto.setFilename(uploadFile.getOriginalFilename());

            return new ResponseEntity<>(attachDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 팝업스토어 등록버튼 클릭 시 등록 (insert)   	
    // 현재 에러 나고 잇어서 주석 처리
//	@PostMapping("/psRegister")
//	public String registerPopStore(popStoreVO psvo, pCatVO pcatvo, @RequestParam("uploadFile") MultipartFile imageFile) {
//				
//		log.warn("pcatvo :" + pcatvo.getHealthBeauty());
//		log.warn("pcatvo :" + pcatvo.getCulture());
//		log.warn("pcatvo :" + pcatvo.getShopping());
//		pcatvo.setPsNo(psvo.getPsNo());
//		
//		pImgVO image = new pImgVO();
//	    if (!imageFile.isEmpty()) {
//	    	image.setPsNo(psvo.getPsNo());
//	    	image.setUploadPath("C:\\upload");
//	    	image.setUploadPath("\\192.168.0.129\storeGoodsImg"); 
//	    	image.setUuid(UUID.randomUUID().toString());
//	        psvo.getPsImg().setFilename("Test Filename");
//	        imgVO.setFilename(imageFile.getOriginalFilename());
//
//	        // 파일 업로드 메서드 호출
//	        ResponseEntity<pImgVO> response = uploadAsyncPost(imageFile);
//	        if (response.getStatusCode() == HttpStatus.OK) {
//	        	image = response.getBody(); // 업로드된 이미지 정보
//	        }
//	        
//	        // 파일 저장 로직 추가
//	        try {
//	            saveFile(imageFile, image.getUploadPath(), image.getFilename());
//	        } catch (IOException e) {
//	            log.error("파일 저장 중 오류 발생: " + e.getMessage());
//	            throw new RuntimeException("파일 저장 실패");
//	        }
//	    }
//		    
//		// 등록
//		pservice.psInsert(psvo, pcatvo, imageFile);
//        
//        return "admin/adminMain";
//    }
//	
//	private void saveFile(MultipartFile file, String uploadPath, String filename) throws IOException {
//	    File dest = new File(uploadPath + "\\" + filename);
//	    file.transferTo(dest); // 파일 저장
//	}
    
    // 현재 에러 나고 잇어서 주석 처리
//    @PostMapping("/psRegister")
//    public String registerPopStore(
//            popStoreVO psvo,
//            @RequestParam("psCat") String categories, // 카테고리
//            @RequestParam("imageFile") MultipartFile imageFile) { 
//    	
//    	log.info("registerPopStore....." + categories);
//    	
//    	log.warn("등록하기 이미지 " + psvo.getPsImg().getFilename());
//    	
//    	//psvo.getPsImg().setFilename("Test Filename");
//    	   	
//    	int result = 0;
//    	
//        try {
//            // 카테고리를 문자열 배열로 변환
//            String[] categoryArray = categories.split(",");
//            
//            // 이미지 정보 생성
//            pImgVO imgVO = new pImgVO();
//            if (!imageFile.isEmpty()) {
////                imgVO.setFilename(imageFile.getOriginalFilename());
//                imgVO.setUploadPath("\\192.168.0.129\storeGoodsImg"); // 실제 경로 설정 필요
//                imgVO.setUuid(UUID.randomUUID().toString());
//
//                // 파일 업로드 메서드 호출
//                ResponseEntity<pImgVO> response = uploadAsyncPost(imageFile);
//                if (response.getStatusCode() == HttpStatus.OK) {
//                    imgVO = response.getBody(); // 업로드된 이미지 정보
//                }
//            }
//
//            // 팝업스토어 등록
//            result = pservice.psInsert(psvo, categoryArray, imgVO);
//
//            // 성공적으로 등록된 경우 JSON 응답 반환
//            log.error("팝업스토어 등록이 완료되었습니다. ID : " + result);
//        } catch (Exception e) {
//            // 오류 발생 시 JSON 응답 반환
//        	log.error("팝업스토어 등록 중 오류가 발생했습니다. " + result);
//        }
//        
//        return "/admin/adminMain";
//    }	
    
    // 팝업스토어 수정/삭제 페이지 영역
    // 삭제 버튼 클릭 시 삭제 후 관리자 메인페이지로 이동 
    // 현재 에러 나고 잇어서 주석 처리
//    @PostMapping("/psDelete")
//    public String deletePopStore(
//    		@ModelAttribute popStoreVO psvo,
//            @ModelAttribute pCatVO pcatvo, // 카테고리 정보
//            @ModelAttribute pImgVO image)
//    {
//    	log.info("팝업스토어 이름: " + psvo.getPsName());
//        log.info("카테고리: " + pcatvo.toString());
//        log.info("이미지 UUID: " + image.getUuid());
//        
//        String imagePath = "C:\\upload\\" + image.getUuid() + ".jpg"; // 예시 경로
//        
//        // 이미지 파일 삭제 로직
//        File fileToDelete = new File(imagePath);
//        if (fileToDelete.exists() && fileToDelete.delete()) {
//            log.info("이미지 파일 삭제 성공: " + imagePath);
//        } else {
//            log.warn("이미지 파일 삭제 실패 또는 파일이 존재하지 않음: " + imagePath);
//        }
//
//        // 삭제 로직 수행
//        int result = pservice.psDelete(psvo, pcatvo, image); // 삭제 서비스 호출
//        
//        return "/admin/adminMain";
//    }

}
