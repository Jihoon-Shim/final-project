package org.kosta.myproject.controller;

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
	public String tagcheckAjax(String tag) {
		return tagService.tagCheck(tag);
	}
	
	@RequestMapping("guest/tagPlusAjax")
	@ResponseBody
	public String tagPlusAjax(String tag) {
		return null;
	}
}
