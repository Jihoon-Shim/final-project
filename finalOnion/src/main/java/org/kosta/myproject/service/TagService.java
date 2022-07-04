package org.kosta.myproject.service;

import java.util.ArrayList;
import java.util.List;

import org.kosta.myproject.vo.TagVO;

public interface TagService {

	ArrayList<String> tagCheck(String tag);

	void registTag(String tag);

	void hitsTag(String tag);

	void relateTag(int tagNo, int currentNo) ;

	int findTagNoByTag(String tag);

	List<TagVO> findTagByBoardNo(int boardNo);

		

}
