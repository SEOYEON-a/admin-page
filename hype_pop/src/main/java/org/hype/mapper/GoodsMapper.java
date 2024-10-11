package org.hype.mapper;

import java.util.List;

import org.hype.domain.goodsVO;
import org.hype.domain.popStoreVO;

public interface GoodsMapper {
	public List<goodsVO> getGList();  // 관리자 상품 리스트 가져오기
	public List<goodsVO> getListBySearchGs(String searchGs);  // 관리자 검색 기능 추가
	public goodsVO getGoodsById (int gNo);  // 특정 굿즈(상품) 조회
}
