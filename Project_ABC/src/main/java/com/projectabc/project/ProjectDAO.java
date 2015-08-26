package com.projectabc.project;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ProjectDAO {
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
	
	public ProjectDAO()throws Exception{
		
	}

	public Project selectProjectByNo(String projno){
		SqlSession session=sqlMapper.openSession(true);
		Project project=session.selectOne("selectProjectByNo",projno);
		return project;
	}
	
	public List<Project> selectProjectListById(String userid){
		SqlSession session=sqlMapper.openSession(true);
		List<Project> projectList=session.selectList("selectProjectListById",userid);
		return projectList;
	}
	
	public void insertProject(Project project){
		SqlSession session=sqlMapper.openSession(true);
		session.insert("insertProject",project);
	}
}
