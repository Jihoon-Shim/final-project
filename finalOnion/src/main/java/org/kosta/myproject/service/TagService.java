package org.kosta.myproject.service;

public interface TagService {

	String tagCheck(String tag);

	void registTag(String tag);

	void hitsTag(String tag);

	void relateTag(int tagNo, int currentNo) ;

	int findTagNoByTag(String tag);

		

}
