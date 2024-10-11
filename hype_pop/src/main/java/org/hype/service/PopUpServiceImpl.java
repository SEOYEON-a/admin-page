package org.hype.service;

import java.util.List;

import org.hype.domain.popStoreVO;
import org.hype.mapper.PopUpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class PopUpServiceImpl implements PopUpService{
	
	@Autowired
	public PopUpMapper mapper;
	
	
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

	// 특정 팝업 스토어 조회
	@Override
	public popStoreVO getPopStoreById(int psNo) {
		return mapper.getPopStoreById(psNo);
	}

	
	
	
}
