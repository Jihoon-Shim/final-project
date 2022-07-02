package org.kosta.myproject.service;

import java.util.List;

import org.kosta.myproject.mapper.TagMapper;
import org.kosta.myproject.vo.TagVO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{
	
	private final TagMapper tagMapper;
	
	@Override
	public String tagCheck(String tag) {		
		int count = tagMapper.tagCheck(tag);
		return (count == 0) ? "ok" : "fail";
	}

	@Override
	public void registTag(String tag) {
		tagMapper.registTag(tag);
	}

	@Override
	public void hitsTag(String tag) {
		tagMapper.hitsTag(tag);	
	}

	@Override
	public void relateTag(int tagNo, int currentNo) {
		tagMapper.relateTag(tagNo,currentNo);
	}

	@Override
	public int findTagNoByTag(String tag) {
		return tagMapper.findTagNoByTag(tag);
	}

	@Override
	public List<TagVO> findTagByBoardNo(int boardNo) {
		return tagMapper.findTagByBoardNo(boardNo);
	}

}
