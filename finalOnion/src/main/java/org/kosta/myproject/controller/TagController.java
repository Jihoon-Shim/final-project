package org.kosta.myproject.controller;

import java.util.ArrayList;

import org.kosta.myproject.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TagController {

	private final TagService tagService;
	
	@RequestMapping("guest/tagcheckAjax")
	@ResponseBody
	public ArrayList<String> tagcheckAjax(String tag) {
		ArrayList<String> taglist = new ArrayList(); 
		taglist = tagService.tagCheck(tag);
		return taglist;
	}
	
	@RequestMapping("guest/tagPlusAjax")
	@ResponseBody
	public String tagPlusAjax(String tag) {
		return null;
	}
}
