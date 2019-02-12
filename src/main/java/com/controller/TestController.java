package com.controller;

import core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	@Qualifier("redisCacheManager")
	private CacheManager cacheManager;
	
	@RequestMapping(value="test", method = RequestMethod.GET)
	public Object test(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/test");
		System.out.println(cacheManager.getCache("user"));
		return mv;
	}

	@ResponseBody
	@RequestMapping(value="set", method = RequestMethod.GET)
	public void insert(HttpServletRequest request) {
		Map m = new HashMap();
		m.put("wbb", "happy");
		redisUtil.setCacheObject("test", "你好");
		System.out.println("插入数据成功！");
	}

	@ResponseBody
	@RequestMapping(value="get", method = RequestMethod.GET)
	public Object select(HttpServletRequest request) {
		System.out.println("进入查询方法！");
		return redisUtil.getCacheObject("test");
	}

	@ResponseBody
	@RequestMapping(value="del", method = RequestMethod.GET)
	public void remove(HttpServletRequest request) {
		redisUtil.deleteByKey("test");
		System.out.println("删除数据成功！");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="setmap", method = RequestMethod.GET)
	public void setmap(HttpServletRequest request) {
		Map m = new HashMap();
		m.put("key", "value");
		redisUtil.setCacheMap("m", m);
		System.out.println("插入map数据成功！");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value="getmap", method = RequestMethod.GET)
	public Object getmap(HttpServletRequest request) {
		System.out.println("进入查询map数据方法！");
		return redisUtil.getCacheMap("m");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="delmap", method = RequestMethod.GET)
	public void delmap(HttpServletRequest request) {
		redisUtil.deleteByKey("m");
		System.out.println("删除map数据成功！");
	}
}