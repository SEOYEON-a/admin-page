package org.hype.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

	// 팝업스토어 등록 
	@Transactional
	@Override
	public int psInsert(popStoreVO psvo, List<pImgVO> imgList) {
		
		int result = mapper.psInsert(psvo, imgList);

		// 첨부 파일 등록 
		if (imgList != null && !imgList.isEmpty()) {
		    imgList.forEach(img -> {
		        img.setPsNo(psvo.getPsNo()); // 팝업스토어 번호 설정
		        amapper.insertImage(img); // 첨부 파일 등록
		    });
		}
	    return result;
	}

}
