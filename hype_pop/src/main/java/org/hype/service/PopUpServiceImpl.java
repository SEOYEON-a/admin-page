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

	@Override
	public List<popStoreVO> getList() {
		log.info("getList...");
		return mapper.getList();
	}
	
	
}
