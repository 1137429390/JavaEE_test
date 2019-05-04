package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller	//申明控制器
@RequestMapping("T1") //申明映射路径
public class TestController {
	
	@RequestMapping("test")
	public String f(@RequestParam("a") int abc) { //返回值必须是String类型
		//如何接取画面参数：
		//(1).只要在参数的后面声明若干变量,并且变量名和参数名必须一致
		//(2).如果变量名和参数名不一致，通过@RequestParam注解,映射变量
		//(3).如果参数刚好是实体类需要的类型,则直接声明实体类对象(传参变量必须和实体类成员变量名一致,不一定是传递实体类的全部成员变量)
		return null;
	}
	
	/*@RequestMapping("f2")
	public String f2(Book book) {
		//配置SpringMVC过滤器,一次性解决中文乱码
		return null;
	}*/
	@RequestMapping("test2")
	public String f2(@RequestParam("a") int abc) { //返回值必须是String类型
		//如何从后台跳转到安全目录下的JSP
		//(1).通过配置文件中的试图解析器,自动给路径添加前缀和后缀
		//(2).return直接写文件名即可
		//(3).JSP文件必须放在view文件夹下
		return null;
	}
}
