package com.bitacademy.customer;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bitacademy.college.College;
import com.bitacademy.college.CollegeDAO;
import com.bitacademy.dept.Dept;
import com.bitacademy.dept.DeptDAO;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Controller
public class CustomerService {

	
	@RequestMapping(value="addCustomerForm.do")
	public ModelAndView addCustomerForm () throws Exception{
	//1.CollegeDAO.selectCollegeList()�� ȣ���ؼ� ���ϵ� ��ü �а� ������ List�� ����
		CollegeDAO collegeDAO=new CollegeDAO();
		List<College> collegeList=collegeDAO.selectCollegeList();
	//2. DepartmentDAO.selectStudentList(100)�޼��带 ȣ���ϰ� 
	//���ϵ� �ܰ����� 100�� ��ü �а�������  List�� ����
		DeptDAO deptDAO=new DeptDAO();
		List<Dept> deptList=deptDAO.selectDeptList("100");
	//3.ModelAndView ��ü ����
		ModelAndView mav=new ModelAndView();
	//4. 1�� ��ü�ܴ븮��Ʈ�� 3��ü�� ���� : �̸�: COLLEGE_LIST
		mav.addObject("COLLEGE_LIST", collegeList);
	//5. 2�� �ܴ� 100�� ��ü�а�����Ʈ�� 3��ü�� ���� �̸�:DEPT_LIST
		mav.addObject("DEPT_LIST", deptList);
	//6. 3��ü�� �̵��� �������� /customer/addCustomerForm.jsp �� �̵��ϵ��� 3��ü�� setViewName�� /customer/addCustomerForm ����
		mav.setViewName("/customer/addCustomerForm");
	//7.3��ü ����
		return mav;
	}
	
	@RequestMapping(value="viewDeptList.do")
	@ResponseBody
	public void viewDeptList(HttpServletResponse response,String colno) throws Exception{
		DeptDAO deptDAO=new DeptDAO();
		//2.DeptDAO.selectDeptList(colno) ȣ���ؼ� �ܴ뿡 �Ҽӵ� �а�����Ʈ�� ������ ����
		List<Dept> deptList=deptDAO.selectDeptList(colno);
		//3.���������� �ѱ� ����
		response.setContentType("text/xml;charset=UTF-8");
		//4.��ü�� XML�� ��ȯ��Ű�� XStream��ü ����
		XStream xstream=new XStream(new DomDriver());
		//5.Dept ��ü�� �±� �̸��� dept�� ����
		xstream.alias("dept", Dept.class);
		//6.List ��ü�� �±� �̸��� deptlist�� ����
		xstream.alias("deptlist", List.class);
		//7.2�� ����Ʈ�� xml������ ��ȯ
		String xml=xstream.toXML(deptList);
		//8.7�� Ŭ���̾�Ʈ���� ����
		PrintWriter out= response.getWriter();
		out.println(xml);
		out.flush();
		out.close();
		//AjaxService3�� �����ؼ� �����Ұ�
	}
	
	@RequestMapping("/idcheck.do")
	public void  idchek(HttpServletResponse response,
			@RequestParam("id") String id) throws Exception{
		String result=null;
		CustomerDAO customerDAO=new CustomerDAO();
		Customer customer=customerDAO.selectCustomerById(id);
		if(customer==null) {
			result="��밡���� ���̵��Դϴ�";
		} else {
			result="�̹� ������� ���̵��Դϴ�";
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}

	@RequestMapping(value="addCustomer.do")
	public ModelAndView addCustomer(Customer customer) throws Exception{
		//1.CustomerDAO.insertCustomer() ȣ��
		CustomerDAO customerDAO=new CustomerDAO();
		customerDAO.insertCustomer(customer);
		//2.ModelAndView ��ü ����
		ModelAndView mav=new ModelAndView();
		//3. 2��ü�� �̵��� �������� forward:/viewBoardList.do ����
		mav.setViewName("forward:/viewBoardList.do");
		//4.2��ü ����
		return mav;
	}

}
