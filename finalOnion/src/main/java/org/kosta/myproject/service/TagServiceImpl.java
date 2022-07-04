package org.kosta.myproject.service;

import java.util.ArrayList;
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
	public ArrayList<String> tagCheck(String tag) {
		ArrayList<String> taglist =new ArrayList<String>(); 
		taglist=tagMapper.tagCheck(tag);
		return  taglist;

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

	@Override
	public String tagCheckExact(String tag) {
		int count = tagMapper.tagCheckExact(tag);
		return (count == 0) ? "ok" : "fail";
	}

}
