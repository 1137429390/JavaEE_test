package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller	//����������
@RequestMapping("T1") //����ӳ��·��
public class TestController {
	
	@RequestMapping("test")
	public String f(@RequestParam("a") int abc) { //����ֵ������String����
		//��ν�ȡ���������
		//(1).ֻҪ�ڲ����ĺ����������ɱ���,���ұ������Ͳ���������һ��
		//(2).����������Ͳ�������һ�£�ͨ��@RequestParamע��,ӳ�����
		//(3).��������պ���ʵ������Ҫ������,��ֱ������ʵ�������(���α��������ʵ�����Ա������һ��,��һ���Ǵ���ʵ�����ȫ����Ա����)
		return null;
	}
	
	/*@RequestMapping("f2")
	public String f2(Book book) {
		//����SpringMVC������,һ���Խ����������
		return null;
	}*/
	@RequestMapping("test2")
	public String f2(@RequestParam("a") int abc) { //����ֵ������String����
		//��δӺ�̨��ת����ȫĿ¼�µ�JSP
		//(1).ͨ�������ļ��е���ͼ������,�Զ���·�����ǰ׺�ͺ�׺
		//(2).returnֱ��д�ļ�������
		//(3).JSP�ļ��������view�ļ�����
		return null;
	}
}
