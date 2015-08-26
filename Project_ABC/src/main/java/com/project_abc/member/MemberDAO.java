package com.project_abc.member;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDAO {
	//1.Mybatis 설정이 있는 문서 이름
	private static   String resource=
			  "sqlmap-config.xml";
	//1의 문서를 읽을 객체 생성
	private static Reader sqlReader=null;
	static{
		 try{	
		  sqlReader=Resources.getResourceAsReader(
								resource);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}				
	//SqlSession 객체-DB 로그인하고 쿼리를 실행할 객체
	//를 만드를 객체가 SqlSessionFactory 
	private static SqlSessionFactory sqlMapper =
			new SqlSessionFactoryBuilder().build(
					sqlReader);
	
	public MemberDAO()throws Exception{
		
	}
	
	/*쿼리아이디 selectMemberById를 실행한다
	 매개변수 id:조회할 회원 아이디
	 */
	public Member selectMemberById(String id){
		SqlSession session=sqlMapper.openSession(true);
		Member member=session.selectOne("selectMemberById",id);
		return member;
	}
	
	public void insertMember(Member member){
		// 쿼리 아이디 insertMember 쿼리를 실행한다
		//session.insert("insertMember",member);
		SqlSession session=sqlMapper.openSession(true);
		session.insert("insertMember",member);
	}

}
