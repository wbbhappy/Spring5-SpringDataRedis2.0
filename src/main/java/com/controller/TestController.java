package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import core.util.RedisUtil;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private RedisUtil redisUtil;
	
	@RequestMapping(value="index", method = RequestMethod.GET)
	public Object index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/test");
		
		return mv;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value="json", method = RequestMethod.GET)
	public Object json(HttpServletRequest request) {
		Map m = new HashMap();
		m.put("key", "value");
		redisUtil.setCacheMap("m", m);
		return redisUtil.getCacheMap("m");
	}
	
}