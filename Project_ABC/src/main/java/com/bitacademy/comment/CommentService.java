package com.bitacademy.comment;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentService {
	@RequestMapping(value="addComment.do")
	public ModelAndView addComment(Comment comment,HttpServletRequest request)throws Exception{
		// 1.Ŭ���̾�Ʈ�� �����Ǹ� �˾Ƴ��� String ������ ����
		//Ŭ���̾�Ʈ�� �����Ǵ� request.getRemoteAddr() �� �˾Ƴ��� ����
		String clientIp=request.getRemoteAddr();
		//2. 1�� �Ű����� comment�� ip�Ӽ��� ����
		comment.setWriteip(clientIp);		
		//3.CommentDAO.insertComment() ȣ��
		CommentDAO commentDAO=new CommentDAO();
		commentDAO.insertComment(comment);
		//4.ModelAndView ��ü ����
		ModelAndView mav=new ModelAndView();
		//5. 4��ü�� �̵��� �������� forward:/viewBoard.do?num=�Խù���ȣ  ���� �Խù���ȣ�� comment�� num �Ӽ��� ���������
		mav.setViewName("forward:/viewBoard.do");
		mav.addObject("num", comment.getNum());
		//6.4��ü ����
		return mav;
	}

}
