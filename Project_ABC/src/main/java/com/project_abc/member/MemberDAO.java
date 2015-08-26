package com.project_abc.member;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDAO {
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
	private static SqlSessionFactory sqlMapper =
			new SqlSessionFactoryBuilder().build(
					sqlReader);
	
	public MemberDAO()throws Exception{
		
	}
	
	/*�������̵� selectMemberById�� �����Ѵ�
	 �Ű����� id:��ȸ�� ȸ�� ���̵�
	 */
	public Member selectMemberById(String id){
		SqlSession session=sqlMapper.openSession(true);
		Member member=session.selectOne("selectMemberById",id);
		return member;
	}
	
	public void insertMember(Member member){
		// ���� ���̵� insertMember ������ �����Ѵ�
		//session.insert("insertMember",member);
		SqlSession session=sqlMapper.openSession(true);
		session.insert("insertMember",member);
	}

}
