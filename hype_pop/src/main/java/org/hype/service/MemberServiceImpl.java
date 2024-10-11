package org.hype.service;

import java.util.List;

import org.hype.domain.signInVO;
import org.hype.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	public MemberMapper mMapper;

	@Override
	public List<signInVO> getMList() {
		return mMapper.getMList();
	}

	@Override
	public List<signInVO> getListBySearchMs(String searchMs) {
		return mMapper.getListBySearchMs(searchMs);
	}

	@Override
	public signInVO getMembersById(String userId) {
		return mMapper.getMembersById(userId);
	}	
	
	
}
