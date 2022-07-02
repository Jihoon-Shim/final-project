package org.kosta.myproject.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper {

	int tagCheck(String tag);

	void registTag(String tag);

	void hitsTag(String tag);

	void relateTag(int tagNo, int currentNo);

	int findTagNoByTag(String tag);


}
