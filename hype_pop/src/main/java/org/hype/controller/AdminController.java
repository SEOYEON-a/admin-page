package org.hype.controller;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hype.domain.goodsVO;
import org.hype.domain.pCatVO;
import org.hype.domain.pImgVO;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	// 관리자 페이지 영역
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
	public String checkAskList() {
		return "admin/askListCheck"; // JSP 파일 경로
	}
		
//	@GetMapping("/askListCheck")
//	public ResponseEntity<String> checkAskList() {
//	    String url = "/admin/askList"; // 이동할 URL (실제 JSP 파일 경로가 아니라, 클라이언트가 요청할 URL (이름은 임의로 설정))
//	    return new ResponseEntity<String>(url, HttpStatus.OK);
//	}
		
//	@GetMapping(value = "/askListCheck", produces = "application/json")
//	public ResponseEntity<Map<String, String>> checkAskList() {
//	    Map<String, String> response = new HashMap<>();
//	    response.put("redirectUrl", "/admin/goaskListCheck");
//	    return new ResponseEntity<>(response, HttpStatus.OK);
//	}

//	@GetMapping(value = "/goaskListCheck")
//	public String goCheckAskList() {
//		return "redirect:/admin/askListCheck";
//	}
		
	// 상품 상태 조회 버튼 클릭 시 상품 상태 조회 페이지로 이동
	@GetMapping("/goodsState")
	public String checkGoods() {
		return "admin/goodsState"; // JSP 파일 경로
	}
//	@GetMapping("/goodsState")
//	public ResponseEntity<String> checkGoodsState() {
//		String url = "/admin/goodsState"; // 이동할 URL (실제 JSP 파일 경로가 아니라, 클라이언트가 요청할 URL (이름은 임의로 설정))
//		return new ResponseEntity<String>(url, HttpStatus.OK);
//	}
		
	// 등록하기 버튼 클릭 시 이동
	@GetMapping("/popUpRegister")
    public String popUpRegister() {
        return "admin/popUpRegister"; 
    }

    @GetMapping("/goodsRegister")
    public String goodsRegister() {
        return "admin/goodsRegister"; 
    }
    
    // 팝업스토어 등록 페이지 영역
    // 이미지 파일 등록
 	// 비동기 통신 : @ResponseBody
    @ResponseBody
    @PostMapping(value = "/uploadAsyncAction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<pImgVO>> uploadAsyncPost(@RequestParam("uploadFile") MultipartFile[] uploadFile) {
        List<pImgVO> list = new ArrayList<>();

        log.info("upload Async post...");

        String uploadFolder = "C:\\upload"; // 기본 저장 경로
        
        File uploadPath = new File(uploadFolder);
        log.info("uploadPath : " + uploadPath);

        // 경로가 없으면 생성
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        for (MultipartFile multipartFile : uploadFile) {
            log.info("-----------------------");
            log.info("Upload File Name : " + multipartFile.getOriginalFilename());
            log.info("Upload File Size : " + multipartFile.getSize());

            String uploadFileName = multipartFile.getOriginalFilename();
            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
            log.info("only file name : " + uploadFileName);

            UUID uuid = UUID.randomUUID();
            uploadFileName = uuid.toString() + "_" + uploadFileName;

            try {
                File saveFile = new File(uploadPath, uploadFileName);
                multipartFile.transferTo(saveFile);

                pImgVO attachDto = new pImgVO();
                attachDto.setUuid(uuid.toString());
                attachDto.setUploadPath(""); // 빈 문자열
                attachDto.setFilename(multipartFile.getOriginalFilename());

                list.add(attachDto);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 팝업스토어 등록
    @PostMapping("/psRegister")
    public String registerPopStore(
            popStoreVO psvo,
            @RequestParam("categories") List<pCatVO> categories,
            @RequestParam("uploadFile") MultipartFile[] imageFiles,
            Model model) {

        try {
            // 이미지 정보를 담을 리스트 생성
            List<pImgVO> images = new ArrayList<>();

            // MultipartFile 배열을 pImgVO 리스트로 변환
            for (MultipartFile file : imageFiles) {
                if (!file.isEmpty()) {
                    pImgVO imgVO = new pImgVO();
                    imgVO.setFilename(file.getOriginalFilename());
                    imgVO.setUploadPath("C:\\upload"); // 실제 경로 설정 필요
                    imgVO.setUuid(java.util.UUID.randomUUID().toString());
                    images.add(imgVO);
                }
            }

            // 파일 업로드 메서드 호출 (이미지 업로드)
            ResponseEntity<List<pImgVO>> response = uploadAsyncPost(imageFiles);
            if (response.getStatusCode() == HttpStatus.OK) {
                images = response.getBody(); // 업로드된 이미지 정보
            }

            // 팝업스토어 등록
            Map<String, Object> result = pservice.psInsert(psvo, categories, images);
            int psNo = (int) result.get("psNo"); // 삽입된 팝업스토어 ID

            // 추가적으로 삽입된 카테고리 및 이미지 ID를 사용할 수 있음
            List<Integer> insertedCategories = (List<Integer>) result.get("insertedCategories");
            List<Integer> insertedImages = (List<Integer>) result.get("insertedImages");

            model.addAttribute("message", "팝업스토어 등록이 완료되었습니다. ID: " + psNo);
            // 필요하다면 추가적인 정보를 모델에 추가 가능

            return "redirect:/popup/psList"; // 등록 후 리스트 페이지로 리다이렉트
        } catch (Exception e) {
            model.addAttribute("errorMessage", "팝업스토어 등록 중 오류가 발생했습니다: " + e.getMessage());
            return "admin/popUpRegister"; // 등록 페이지로 돌아감
        }
    }

//    @PostMapping("/psRegister")
//    public String registerPopStore(
//            popStoreVO psvo,
//            @RequestParam("categories") List<pCatVO> categories,
//            @RequestParam("uploadFile") MultipartFile[] imageFiles,
//            Model model) {
//
//        try {
//            // 이미지 정보를 담을 리스트 생성
//            List<pImgVO> images = new ArrayList<>();
//
//            // MultipartFile 배열을 pImgVO 리스트로 변환
//            for (MultipartFile file : imageFiles) {
//                if (!file.isEmpty()) {
//                    pImgVO imgVO = new pImgVO();
//                    imgVO.setFilename(file.getOriginalFilename());
//                    imgVO.setUploadPath("/uploads/test/"); // 실제 경로 설정 필요
//                    imgVO.setUuid(java.util.UUID.randomUUID().toString());
//                    images.add(imgVO);
//                }
//            }
//
//            // 파일 업로드 메서드 호출
//            ResponseEntity<List<pImgVO>> response = uploadAsyncPost(imageFiles);
//            if (response.getStatusCode() == HttpStatus.OK) {
//                images = response.getBody(); // 업로드된 이미지 정보
//            }
//
//            // 팝업스토어 등록
//            int psNo = pservice.psInsert(psvo, categories, images);
//            
//            model.addAttribute("message", "팝업스토어 등록이 완료되었습니다. ID: " + psNo);
//            return "redirect:/popup/psList"; // 등록 후 리스트 페이지로 리다이렉트
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", "팝업스토어 등록 중 오류가 발생했습니다: " + e.getMessage());
//            return "admin/popUpRegister"; // 등록 페이지로 돌아감
//        }
//    }

//    @PostMapping("/psRegister")
//    public String registerPopStore(
//    		popStoreVO psvo,
//    		@RequestParam("categories") List<pCatVO> categories,
//    		@RequestParam("images") List<MultipartFile> imageFiles,
//    		Model model) {
//    	
//    	try {
//    		// 이미지 정보를 담을 리스트 생성
//    		List<pImgVO> images = new ArrayList<>();
//    		for (MultipartFile file : imageFiles) {
//    			if (!file.isEmpty()) {
//    				// 파일 처리 로직 (UUID 생성, 파일 경로 설정 등)
//    				pImgVO imgVO = new pImgVO();
//    				imgVO.setFilename(file.getOriginalFilename());
//    				imgVO.setUploadPath("/uploads/test/"); // 실제 경로 설정 필요
//    				imgVO.setUuid(java.util.UUID.randomUUID().toString());
//    				images.add(imgVO);
//    			}
//    		}
//    		
//    		// 팝업스토어 등록
//    		int psNo = pservice.psInsert(psvo, categories, images);
//    		
//    		model.addAttribute("message", "팝업스토어 등록이 완료되었습니다. ID: " + psNo);
//    		return "redirect:/popup/success"; // 성공 페이지로 리다이렉트
//    	} catch (Exception e) {
//    		model.addAttribute("errorMessage", "팝업스토어 등록 중 오류가 발생했습니다: " + e.getMessage());
//    		return "popup/register"; // 등록 페이지로 돌아감
//    	}
//    }
    
    
//    @PostMapping("/psRegister")
//    public String registerPopStore(@ModelAttribute popStoreVO psvo, @RequestParam("imgList") List<MultipartFile> imgList) {
//        // DB에 등록
//        pservice.psInsert(psvo, imgList);
//        return "redirect:/admin/psList"; // 성공 후 목록 페이지로 이동
//    }
//    @PostMapping("/psRegister")
//    public String registerPopStore(@ModelAttribute popStoreVO psvo, @RequestParam("imgList") List<MultipartFile> imgList) {
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("popStore", psvo);
//        paramMap.put("imgList", imgList);
//
//        // DB에 등록
//        pservice.psInsert(paramMap);
//        return "redirect:/admin/psList"; // 성공 후 목록 페이지로 이동
//    }

//    @PostMapping("/psRegister")
//    public String registerPopStore(
//            @ModelAttribute popStoreVO psvo, 
//            @RequestParam("imgList") List<MultipartFile> imgList) {
//        
//        // Map 생성
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("psvo", psvo);
//        
//        // imgList를 pImgVO 리스트로 변환
//        List<pImgVO> pImgList = new ArrayList<>();
//        for (MultipartFile file : imgList) {
//            pImgVO imgVO = new pImgVO();
//            imgVO.setFilename(file.getOriginalFilename());
//            imgVO.setUploadPath("/uploads/test/"); // 예시 경로
//            // UUID나 기타 필요한 정보 설정
//            pImgList.add(imgVO);
//        }
//        
//        paramMap.put("imgList", pImgList);
//
//        // 서비스 호출
//        pservice.psInsert(paramMap);
//        
//        return "redirect:/admin/psList"; // 성공 후 목록 페이지로 이동
//    }
//    public String registerPopStore(
//    		@ModelAttribute popStoreVO psvo,
//    		@RequestParam("imgList") List<MultipartFile> imgList) {
//    	
//    	
//    	// 이미지 리스트를 pImgVO 리스트로 변환
//    	List<pImgVO> pImgList = new ArrayList<>();
//    	for (MultipartFile file : imgList) {
//    		if (!file.isEmpty()) {
//    			pImgVO imgVO = new pImgVO();
//    			imgVO.setUuid(java.util.UUID.randomUUID().toString());
//    			imgVO.setFilename(file.getOriginalFilename());
//    			imgVO.setUploadPath("/uploads/test/"); // 예시 경로
//    			pImgList.add(imgVO);
//    		}
//    	}
//    	
//    	// 서비스 호출 (여기서 pImgList를 그대로 전달)
//    	pservice.psInsert(psvo, imgList); // imgList를 전달
//    	
//    	return "redirect:/admin/psList"; // 성공 후 목록 페이지로 이동
//    }
    



}
