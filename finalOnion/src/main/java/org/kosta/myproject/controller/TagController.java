package org.kosta.myproject.controller;

import java.util.ArrayList;

import org.kosta.myproject.service.TagService;
import org.kosta.myproject.vo.TagVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	@RequestMapping("/guest/trendPopup")
	public String trendPopup(Model model) {
		ArrayList<TagVO> trendlist = new ArrayList<TagVO>();
		ArrayList<TagVO> toplist = new ArrayList<TagVO>();
		trendlist = tagService.trendsearch();
		toplist = tagService.toplistsearch();
		model.addAttribute("toplist",toplist);
		model.addAttribute("trendlist",trendlist);
		return "/board/trendPopup";
	}
}
