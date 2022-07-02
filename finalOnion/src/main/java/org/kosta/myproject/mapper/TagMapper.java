package org.kosta.myproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.myproject.vo.TagVO;

@Mapper
public interface TagMapper {

	int tagCheck(String tag);

	void registTag(String tag);

	void hitsTag(String tag);

	void relateTag(int tagNo, int currentNo);

	int findTagNoByTag(String tag);

	List<TagVO> findTagByBoardNo(int boardNo);


}
