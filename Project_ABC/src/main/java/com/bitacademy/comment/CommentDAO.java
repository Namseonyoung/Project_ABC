package com.bitacademy.comment;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CommentDAO {
	
	//1.Mybatis ������ �ִ� ���� �̸�
	private static   String resource=
			  "sqlmap-config.xml";
	//1�� ������ ���� ��ü ����
	private static Reader sqlReader=null;
	static{
		 try{	
		  sqlReader=Resources.getResourceAsReader(
								resource);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}				
	//SqlSession ��ü-DB �α����ϰ� ������ ������ ��ü
	//�� ���带 ��ü�� SqlSessionFactory 
	private static SqlSessionFactory factory =
			new SqlSessionFactoryBuilder().build(
					sqlReader);
	
	public CommentDAO()throws Exception{
		
	}
	
	public void insertComment(Comment comment) {
		SqlSession session=factory.openSession(true);
		session.insert("insertComment",comment);
	}
	
	public List<Comment> selectCommentList(String num){
		//���� ���̵� selectCommentList �����ؼ� Ŀ��Ʈ ����Ʈ�� ������ ��� Ŀ��Ʈ ����Ʈ ����
		//session.selectList("selectCommentList",num);
		SqlSession session=factory.openSession(true);
		List<Comment> commentList=session.selectList("selectCommentList",num);
		return commentList;
	}
}







