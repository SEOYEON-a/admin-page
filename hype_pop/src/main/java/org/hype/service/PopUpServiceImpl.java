package org.hype.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hype.domain.pCatVO;
import org.hype.domain.pImgVO;
import org.hype.domain.popStoreVO;
import org.hype.mapper.AttachMapper;
import org.hype.mapper.PopUpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class PopUpServiceImpl implements PopUpService{
	
	@Autowired
	public PopUpMapper mapper;
	
	@Autowired
	public AttachMapper amapper;
	
	@Override
	public List<popStoreVO> getPopularPopUps() {
		List<popStoreVO> popUps = mapper.getPopularPopUps();
		return popUps;
	}

	// 관리자 popup 리스트 가져오기
	@Override
	public List<popStoreVO> getList() {
		log.info("getList...");
		return mapper.getList();
	}

	// 관리자 검색 기능 추가
	@Override
	public List<popStoreVO> getListBySearchPs(String searchPs) {
		return mapper.getListBySearchPs(searchPs);
	}

	// 특정 팝업스토어 조회
	@Override
	public popStoreVO getPopStoreById(int psNo) {
		return mapper.getPopStoreById(psNo);
	}

	
//    public void psInsert(Map<String, Object> paramMap) {
//        popStoreVO psvo = (popStoreVO) paramMap.get("psvo");
//        List<pImgVO> imgList = (List<pImgVO>) paramMap.get("imgList");
//        mapper.psInsert(psvo, imgList);
//    }
	
	// 팝업스토어 등록
	@Transactional
	@Override
	public Map<String, Object> psInsert(popStoreVO psvo, List<pCatVO> categories, List<pImgVO> images) {
	    Map<String, Object> resultMap = new HashMap<>();

	    // 팝업스토어 삽입
	    int psNo = mapper.psInsert(psvo); // 삽입 후 생성된 ID를 가져옴
	    resultMap.put("psNo", psNo); // 삽입된 팝업스토어 ID 저장

	    // 카테고리 삽입
	    List<Integer> insertedCategoryIds = new ArrayList<>();
	    for (pCatVO category : categories) {
	        category.setPsNo(psNo); // 방금 삽입한 팝업스토어 번호 설정
	        int categoryId = mapper.catInsert(category); // 카테고리 삽입 후 ID 가져오기
	        insertedCategoryIds.add(categoryId);
	    }
	    resultMap.put("insertedCategories", insertedCategoryIds); // 삽입된 카테고리 IDs 저장

	    // 이미지 삽입
	    List<Integer> insertedImageIds = new ArrayList<>();
	    for (pImgVO image : images) {
	        image.setPsNo(psNo); // 방금 삽입한 팝업스토어 번호 설정
	        int imageId = mapper.imgInsert(image); // 이미지 정보를 삽입 후 ID 가져오기
	        insertedImageIds.add(imageId);
	    }
	    resultMap.put("insertedImages", insertedImageIds); // 삽입된 이미지 ID 저장

	    return resultMap; // 모든 결과를 포함한 Map 반환
	}

//	@Transactional
//	@Override
//	public int psInsert(popStoreVO psvo, List<pCatVO> categories, List<pImgVO> images) {
//		// 팝업스토어 삽입
//		int psNo = mapper.psInsert(psvo); // 삽입 후 생성된 ID를 가져옴
//		
//		// 카테고리 삽입
//		for (pCatVO category : categories) {
//			category.setPsNo(psNo); // 방금 삽입한 팝업스토어 번호 설정
//			mapper.catInsert(category);
//		}	
//		
//		// 이미지 삽입
//		for (pImgVO image : images) {
//			image.setPsNo(psNo); // 방금 삽입한 팝업스토어 번호 설정
//			mapper.imgInsert(image); // 이미지 정보를 삽입
//		}
//		
//		return psNo; // 삽입된 팝업스토어의 ID를 반환
//	}
	
//	@Transactional
//	@Override
//    public int psInsert(popStoreVO psvo, List<MultipartFile> imgList) {
//		
//		List<String> store = mapper.getPopStoreById(psNo);
//        // 이미지 리스트를 pImgVO 리스트로 변환
//        List<pImgVO> pImgList = new ArrayList<>();
//       
//        for (MultipartFile file : imgList) {
//            if (!file.isEmpty()) {
//                pImgVO imgVO = new pImgVO();
//                imgVO.setUuid(java.util.UUID.randomUUID().toString()); // UUID 생성
//                imgVO.setUploadPath("/uploads/test/"); // 저장할 경로 (적절히 수정)
//                imgVO.setFilename(file.getOriginalFilename()); // 원래 파일명
//
//                try {
//                    File destinationFile = new File(imgVO.getUploadPath(), imgVO.getFilename());
//                    file.transferTo(destinationFile);
//                } catch (IOException e) {
//                    // 파일 저장 중 에러 처리
//                    e.printStackTrace();
//                    throw new RuntimeException("파일 저장에 실패했습니다: " + e.getMessage());
//                }
//                
//                pImgList.add(imgVO);
//            }
//        }
//
//        // Map 생성
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("psvo", psvo);
//        paramMap.put("imgList", pImgList);
//
//        // DB에 저장
//        mapper.psInsert(paramMap);
//    }
	
	

//	@Transactional
//	@Override
//	public int psInsert(popStoreVO psvo, List<pImgVO> imgList) {
//	    Map<String, Object> paramMap = new HashMap<>();
//	    paramMap.put("psvo", psvo);
//	    paramMap.put("imgList", imgList);
//
//	    // 팝업스토어 등록
//	    mapper.psInsert(paramMap);
//
//	    // 이미지 리스트에 psNo를 설정
//	    if (imgList != null && !imgList.isEmpty()) {
//	        imgList.forEach(img -> {
//	            img.setPsNo(psvo.getPsNo()); // 방금 등록한 팝업스토어 번호 설정
//	            amapper.insertImage(img); // 첨부 파일 등록
//	        });
//	    }
//	    return psvo.getPsNo(); // 성공적으로 등록된 팝업스토어 ID 반환
//	}
	
	

}
