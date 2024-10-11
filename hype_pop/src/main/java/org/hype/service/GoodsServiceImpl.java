package org.hype.service;

import java.util.List;
import java.util.Map;

import org.hype.domain.goodsVO;
import org.hype.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class GoodsServiceImpl implements GoodsService{
	
	@Autowired
	public GoodsMapper mapper;

	@Override
	public List<goodsVO> getGList() {
		return mapper.getGList();
	}

	@Override
	public List<goodsVO> getListBySearchGs(String searchGs) {
		return mapper.getListBySearchGs(searchGs);
	}

	@Override
	public goodsVO getGoodsById(int gNo) {
		return mapper.getGoodsById(gNo);
	}	
	
		
}
