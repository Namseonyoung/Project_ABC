package com.bitacademy.board;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BoardDAO {
	String resource="sqlmap-config.xml";
	Reader sqlReader;
	SqlSessionFactory factory;
	public BoardDAO()throws Exception{
		sqlReader=
				Resources.getResourceAsReader(
						resource);
		factory=
				new SqlSessionFactoryBuilder()
				.build(sqlReader);
	}
  public Board selectBoard(String num)throws Exception{
	  //1.	num�� ��ġ�ϴ� ���ڵ带 tb_board ���̺��� ��ȸ�ؼ� �����Ѵ�
	  //������ ���� ���̵� :selectBoard
	  SqlSession session=
				factory.openSession(true);
	  Board searchBoard=
			  session.selectOne("selectBoard",num);
	  session.close();
	  return searchBoard;
  }
  public List<Board> selectBoardList(
		  int startIndex,int endIndex)throws Exception{
	  //1.RowBounds ��ü�� �����Ѵ�
	  //2.	1.	tb_board ���̺��� startIndex���� endIndex ������ 
	  //    ���ڵ带 ��ȸ�ؼ� List�� ��Ƽ� �����Ѵ�.
	  //������ ���� ���̵� :selectBoardList
	  //��ȸ�� ���ڵ��� �����ε����� ���ε��� :1�� RowBounds ��ü
	  SqlSession session=
				factory.openSession(true);
		RowBounds bound=
				new RowBounds(startIndex,endIndex);
		List<Board> boardList=
				session.selectList(
						"selectBoardList"
						,null,bound);
		session.close();
		return boardList;	  
  }
  public List<Board> searchBoardList(Board board,int startIndex,int endIndex)
   throws Exception{
	//1.RowBounds ��ü�� �����Ѵ�
	  //2.	1.	tb_board ���̺��� startIndex���� endIndex ������ 
	  //    ���ڵ带 �˻��ؼ� List�� ��Ƽ� �����Ѵ�.
	  //������ ���� ���̵� :searchBoardList
	  //��ȸ�� ���ڵ��� �����ε����� ���ε��� :1�� RowBounds ��ü
	  SqlSession session=
				factory.openSession(true);
		RowBounds bound=
				new RowBounds(startIndex,endIndex);
		List<Board> boardList=
				session.selectList(
						"searchBoardList"
						,board,bound);
		session.close();
		return boardList;	
  }
  public int selectBoardCount()throws Exception{
	  //tb_board ���̺��� ��ü �Խù��� ���� ��ȸ�ϴ� 
	  //�������̵� selectBoardCount�� �����ؼ� ��� int �� �����Ѵ�
	  SqlSession session=
				factory.openSession(true);
	  int boardCount=
			  session.selectOne("selectBoardCount");
	  session.close();
	  return boardCount;
  }
  public int searchBoardCount(Board board)throws Exception{
	  //tb_board ���̺��� ��ü �Խù��� ���� ��ȸ�ϴ� 
	  //�������̵� searchBoardCount�� �����ؼ� ��� int �� �����Ѵ�
	  SqlSession session=
				factory.openSession(true);
	  int boardCount=
			  session.selectOne("searchBoardCount",board);
	  session.close();
	  return boardCount;
  }
  public Board insertBoard(Board board)throws Exception {
	  SqlSession session=
				factory.openSession(true);
	  session.insert("insertBoard",board);
	  return board;
  }
}
