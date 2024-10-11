package org.hype.mapper;

import java.util.List;

import org.hype.domain.signInVO;


public interface MemberMapper {
	public List<signInVO> getMList();  // 관리자 회원 리스트 가져오기
	public List<signInVO> getListBySearchMs(String searchMs);  // 관리자 검색 기능 추가
	public signInVO getMembersById (String userId);  // 특정 회원 조회
}
