package com.controller;

import com.entity.User;
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
import java.util.*;

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
		Map map = new HashMap();
		map.put("wbb", "happy");
		ArrayList list = new ArrayList();
		list.add("您好");
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

	@ResponseBody
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="setmap", method = RequestMethod.GET)
	public void setMap(HttpServletRequest request) {
		User user = new User();
		user.setUsername("wbb");
		user.setPassword("happy");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user", user.toString());
		redisUtil.setCacheMap("maps", map);
		System.out.println("插入map数据成功！");
	}

	@ResponseBody
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="getmap", method = RequestMethod.GET)
	public Object getMap(HttpServletRequest request) {
		System.out.println("进入查询map数据方法！");
		return redisUtil.getCacheMap("maps");
	}

	@ResponseBody
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="delmap", method = RequestMethod.GET)
	public void delMap(HttpServletRequest request) {
		redisUtil.deleteByKey("maps");
		System.out.println("删除map数据成功！");
	}

	@ResponseBody
	@RequestMapping(value="setlist", method = RequestMethod.GET)
	public void setList(HttpServletRequest request) {
		User users = new User();
		users.setUsername("wbb");
		users.setPassword("happy");
		/**
		 * List<Object>和list区别：
		 * 没有区别，其实java的泛型是伪泛型。编译以后虚拟机会帮助我们进行强制转型的。
		 * 没有泛型的话，IDE的编译器会提示一个泛型警告（可以用注解屏蔽）。
		 * 但是如果你定义了Object如User你就只能往里放User类，防止你往list放入别的东西
		 */
		//List lists = new ArrayList();
		//lists.add(users.toString());
		List<Object> list = new ArrayList<Object>();
		list.add(users.toString());
		redisUtil.setCacheList("list", list);
		System.out.println("插入list数据成功！");
	}

	@ResponseBody
	@RequestMapping(value="getlist", method = RequestMethod.GET)
	public Object getList(HttpServletRequest request) {
		System.out.println("进入查询list数据方法！");
		return redisUtil.getCacheList("list");
	}

	@ResponseBody
	@RequestMapping(value="dellist", method = RequestMethod.GET)
	public void delList(HttpServletRequest request) {
		redisUtil.deleteByKey("list");
		System.out.println("删除list数据成功！");
	}

	@ResponseBody
	@RequestMapping(value="setSet", method = RequestMethod.GET)
	public void setSet(HttpServletRequest request) {
		User users = new User();
		users.setUsername("wbb");
		users.setPassword("happy");
		/**
		 * Set<Object>和Set区别：
		 * 没有区别，其实java的泛型是伪泛型。编译以后虚拟机会帮助我们进行强制转型的。
		 * 没有泛型的话，IDE的编译器会提示一个泛型警告（可以用注解屏蔽）。
		 * 但是如果你定义了Object如User你就只能往里放User类，防止你往Set放入别的东西
		 */
		//Set sets = new HashSet();
		//sets.add(users.toString());
		Set<Object> set = new HashSet<Object>();
		set.add(users.toString());
		redisUtil.setCacheSet("set", set);
		System.out.println("插入set数据成功！");
	}

	@ResponseBody
	@RequestMapping(value="getSet", method = RequestMethod.GET)
	public Object getSet(HttpServletRequest request) {
		System.out.println("进入查询set数据方法！");
		return redisUtil.getCacheSet("set");
	}

	@ResponseBody
	@RequestMapping(value="delSet", method = RequestMethod.GET)
	public void delSet(HttpServletRequest request) {
		redisUtil.deleteByKey("set");
		System.out.println("删除set数据成功！");
	}
}