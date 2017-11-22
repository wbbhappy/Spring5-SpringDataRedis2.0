package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 声明用的是Spring的测试类 **/
@RunWith(SpringJUnit4ClassRunner.class)
/** 声明spring主配置文件位置，注意：以当前测试类的位置为基准,有多个配置文件以字符数组声明 **/
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
/** 事务自动回滚  **/
@Rollback
public class SpringJunitTest {

	@Test
	public void test() {
		System.out.println("test");
	}
	
	public static void main(String[] args) {
		System.out.println("main");
	}
}
