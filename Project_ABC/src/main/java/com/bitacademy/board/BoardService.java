package com.bitacademy.board;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bitacademy.comment.Comment;
import com.bitacademy.comment.CommentDAO;
import com.bitacademy.util.PageUtil;

@Controller
public class BoardService {
	//���������� ������ �Խù� ���� 
	public static final int length=5;
	@RequestMapping("viewBoardList.do")
	public ModelAndView viewBoardList(
			@RequestParam(value="page", required=false,defaultValue="1")
			int page)throws Exception{
	/*
	1.���������� ������ �Խù��� �����ε���  (page-1)*length �� ������ ����
	3.BoardDAO ��ü �����ؼ� ������ ����	
	3.	3��ü.selectBoardList(1����,length)ȣ���ؼ� �������� ������ �Խù� ������ ��ȸ�ؼ� ������ ����
	4.  3��ü.selectBoardCount() ȣ���ؼ� ��ü �Խù��� ���� ������ ����
	5.�ٸ��������� �̵��ϴ� ��ũ�� ����� �ִ� 
	PageUtil.generate(page, 4����,length, "viewBoardList.do")
	�� ȣ���ؼ� ���ϰ��� ������ ����
	6.	ModelAndView ��ü ����
	7.	3�� �Խù� ������ 6 ��ü�� ���� (�Ӽ���:BOARD_LIST)
	7.	5 ��  6��ü�� ���� (�Ӽ���:PAGE_LINK)
	4.	/board/viewBoardList.jsp�� �̵��ϵ���6��ü�� setViewName�� /board/viewBoardList����
	5.	6��ü ����
	*/
		int startIndex=(page-1)*length;
		BoardDAO boardDAO=new BoardDAO();
		
		List<Board> boardList=boardDAO.selectBoardList(startIndex, length);
		int boardCount=boardDAO.selectBoardCount();
		
		String pageLink=PageUtil.generate(page,boardCount,length, "viewBoardList.do");
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("BOARD_LIST", boardList);
		mav.addObject("PAGE_LINK", pageLink);
		mav.setViewName("/board/viewBoardList");
		
		return mav;
	}
	
	@RequestMapping("searchBoardList.do")
	public ModelAndView serchBoardList(
			@RequestParam(value="page", required=false,defaultValue="1")
			int page,
			@RequestParam(value="keyword", required=false)String keyword,
			@RequestParam(value="search", required=false)String search)throws Exception{
	/*
	1.���������� ������ �Խù��� �����ε���  (page-1)*length �� ������ ����
	
	3.Board board=null;
	4. �Ű����� search�� null �� �ƴϰ� search.trim().length()�� 1�̻��̸�
	 4.1 Board ��ü �����ؼ� 3������ ���� 
	 4.1 �Ű����� keyword�� ����  "title"�̸� 3��ü�� title�Ӽ��� �Ű����� search����
	 4.2�Ű����� keyword�� ����  "content"�̸� 3��ü�� content�Ӽ��� �Ű����� search����
	 4.3 �Ű����� filename �� ����  "filename"�̸� 3��ü�� content�Ӽ��� �Ű����� search����
	 -���ڿ��̱� ������  equals�� ���Ѵ�
	5.  BoardDAO ��ü �����ؼ� ������ ����
    6.ArrayList<Board> boardList=null;	
	  int totalCount=0;
	7. 3�� board �� null�̸�	
	   5��ü.selectBoardList(1����,length)ȣ���ؼ� ��ü �Խù� ������ ��ȸ�ؼ� ���� boardList�� ����
	   5��ü.selectBoardCount() ȣ���ؼ� ��ü �Խù��� ���� ���� totalCount �� ����
	8. 3�� board�� null�� �ƴϸ�
	   5��ü.searchBoardList(board,1����,length)ȣ���ؼ� �Խù� ������ �˻��ؼ� ���� boardList�� ����
	   5��ü.searchBoardCount(board) ȣ���ؼ� ��ü �˻��Խù��� ���� ���� totalCount �� ����
	9.�ٸ��������� �̵��ϴ� ��ũ�� ����� �ִ� 
	PageUtil.generate(page, totalCount,length, "searchBoardList.do")
	�� ȣ���ؼ� ���ϰ��� ������ ����
	10.	ModelAndView ��ü ����
	11.	6�� �Խù� ������ 10 ��ü�� ���� (�Ӽ���:BOARD_LIST)
	11.	9 ��  10��ü�� ���� (�Ӽ���:PAGE_LINK)
	12.	/board/viewBoardList.jsp�� �̵��ϵ���6��ü�� setViewName�� /board/viewBoardList����
	13.	10��ü ����
	*/
		int startIndex=(page-1)*length;
		Board board=null;
		if(search!=null && search.trim().length()>=1) {
			board=new Board();
			if("title".equals(keyword)){
				board.setTitle(search);
			}
			else if("content".equals(keyword)){
				board.setContent(search);
			}
			else if("filename".equals(keyword)){
				board.setFilename(search);
			}
		}
		
		BoardDAO boardDAO=new BoardDAO();
		
		List<Board> boardList=null;	
		int totalCount=0;

		if(board==null){
			boardList=boardDAO.selectBoardList(startIndex,length);
			totalCount=boardDAO.selectBoardCount();
		} else {
			boardList=boardDAO.searchBoardList(board,startIndex,length);
			totalCount=boardDAO.searchBoardCount(board);
		}
		
		String pageLink=PageUtil.generate(page, totalCount,length, "searchBoardList.do");
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("BOARD_LIST", boardList);
		mav.addObject("PAGE_LINK", pageLink);
		mav.setViewName("/board/viewBoardList");
		
		return mav;
	}
	@RequestMapping("viewBoard.do")
	public ModelAndView viewBoard(
			@RequestParam(value="num")String num)throws Exception{
	/*	
	3.BoardDAO ��ü �����ؼ� ������ ����	
	4.	3��ü.selectBoardList(num) �Ű����� num�� ��ȣ�� ��ġ�ϴ� �Խù� ��ȸ
	5.	ModelAndView ��ü ����
	6.	4�� �Խù� ������ 5 ��ü�� ���� (�Ӽ���:BOARD)
	4.	/board/viewBoard.jsp�� �̵��ϵ���6��ü�� setViewName�� /board/viewBoard����
	5.	5��ü ����
	*/
		BoardDAO boardDAO=new BoardDAO();
		Board board=boardDAO.selectBoard(num);
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("BOARD",board);
		mav.setViewName("/board/viewBoard");
		
		CommentDAO commentDAO=new CommentDAO();
		List<Comment> commentList=commentDAO.selectCommentList(num);
		mav.addObject("COMMENT_LIST",commentList);
		
		return mav;
	}
	@RequestMapping(value="addBoardForm.do")
	public ModelAndView addBoardForm()throws Exception {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/board/addBoardForm");
		return mav;
	}
	@RequestMapping(value="addBoard.do")
	public ModelAndView addBoard(Board board,
			@RequestParam(value="file")MultipartFile file)throws Exception {
		if(file!=null&&file.isEmpty()==false) {
			File saveFile=new File("c:/upload/"+file.getOriginalFilename());
			int num=1;
			while(saveFile.exists()==true){
				String filename=file.getOriginalFilename();
				int dotIndex=filename.indexOf(".");
				if(dotIndex>=0){
					String onlyFilename=filename.substring(0, dotIndex);
					String onlyExt=filename.substring(dotIndex,filename.length());
					saveFile=new File("c:/upload/"+onlyFilename+num+onlyExt);
				}
				else {
					saveFile=new File("c:/upload/"+filename+num);
				}
				num++;
			}			
			file.transferTo(saveFile);
			board.setFilename(file.getOriginalFilename());
			board.setRealfilename(saveFile.getName());
		}
		BoardDAO boardDAO=new BoardDAO();
		boardDAO.insertBoard(board);
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("forward:/viewBoard.do?num="+board.getNum());
		return mav;
	}
	@RequestMapping(value="downloadBoard.do",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public void downloadBoard(@RequestParam("num") String num,
			HttpServletResponse response)throws Exception {
		BoardDAO boardDAO=new BoardDAO();
		Board board=boardDAO.selectBoard(num);
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename="+board.getFilename());
		File downFile=new File("c:/upload/"+board.getRealfilename());
		FileCopyUtils.copy(new FileInputStream(downFile),response.getOutputStream());
		response.flushBuffer();
	}


}
