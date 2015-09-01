package com.projectabc.message;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.projectabc.member.Member;
import com.projectabc.member.MemberDAO;


@Controller
public class MessageService {
	@RequestMapping(value="sendMessgeForm.do")
	public ModelAndView sendMessageForm()throws Exception{
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/message/sendMessageForm");		
		return mav;
		
	}
	@RequestMapping(value="sendMessage.do")
	public ModelAndView sendMessage(
			Message message
			//, @RequestParam("id")String memid
			)throws Exception{
			
		MessageDAO mesDAO = new MessageDAO();
		mesDAO.insertMessage(message);
			
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/message/showMessageList");		
		return mav;
		
	}
	
	@RequestMapping(value="showMessageList.do")
	public ModelAndView showReceiveMessageList(
			//@RequestParam("id")String id,
			HttpSession session
			)throws Exception{
		
		Member member = (Member)session.getAttribute("MEMBER");
		MessageDAO mesDAO = new MessageDAO();
		
		List<Message> mesList = (List<Message>)
				mesDAO. selectMessageListByRecvid(member.getId());
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/message/showMessageList");
		mav.addObject("MESSAGE_LIST",mesList);
		return mav;
		
	}
/*
	@RequestMapping(value="messagePage.do")
	public ModelAndView messagePage(
			@RequestParam("mesno")String mesno
			)throws Exception{

		MessageDAO projDAO = new MessageDAO();
		MemberDAO memDAO =new MemberDAO();
		TodoDAO todoDAO = new TodoDAO();
		TodoListDAO todoListDAO = new TodoListDAO();
		
		Project project=projDAO.selectProjectByNo(projno);	
		List<Member> memList=memDAO.selectMemberListByProjno(projno);	
		
		List<TodoList> todoList = 
				todoListDAO.selectTodolistListByProjno(projno);
		
		List<List<Todo>> todo = new ArrayList<List<Todo>>();
		
		for(int i=0; i<todoList.size(); i++)
		{
			todo.add(todoDAO.selectTodoListByListno(todoList.get(i).getListno()));
		}
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/project/projectPage");
		mav.addObject("PROJECT",project);
		mav.addObject("MEM_LIST", memList);
		mav.addObject("TODO",todo);
		mav.addObject("TODO_LIST", todoList);
		
		return mav;
		
	}
*/	

}
