package com.project_abc.member;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class MemberService {
	@RequestMapping(value="addMemberForm.do")
	public ModelAndView addMemberForm () throws Exception{
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
	//6. 3��ü�� �̵��� �������� /Member/addMemberForm.jsp �� �̵��ϵ��� 3��ü�� setViewName�� /Member/addMemberForm ����
		mav.setViewName("/Member/addMemberForm");
	//7.3��ü ����
		return mav;
	}
	
	@RequestMapping("/idcheck.do")
	public void  idchek(HttpServletResponse response,
			@RequestParam("id") String id) throws Exception{
		String result=null;
		MemberDAO MemberDAO=new MemberDAO();
		Member member=MemberDAO.selectMemberById(id);
		if(member==null) {
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

	@RequestMapping(value="addMember.do")
	public ModelAndView addMember(Member member) throws Exception{
		//1.MemberDAO.insertMember() ȣ��
		MemberDAO MemberDAO=new MemberDAO();
		MemberDAO.insertMember(member);
		//2.ModelAndView ��ü ����
		ModelAndView mav=new ModelAndView();
		//3. 2��ü�� �̵��� �������� forward:/viewBoardList.do ����
		mav.setViewName("forward:/viewBoardList.do");
		//4.2��ü ����
		return mav;
	}

}
