package com.bitacademy.customer;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class CustomerDAO {
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
	
	public CustomerDAO()throws Exception{
		
	}
	
	/*�������̵� selectCustomerById�� �����Ѵ�
	 �Ű����� id:��ȸ�� ȸ�� ���̵�
	 */
	public Customer selectCustomerById(String id){
		SqlSession session=sqlMapper.openSession(true);
		Customer customer=session.selectOne("selectCustomerById",id);
		return customer;
	}
	
	public void insertCustomer(Customer customer){
		// ���� ���̵� insertCustomer ������ �����Ѵ�
		//session.insert("insertCustomer",customer);
		SqlSession session=sqlMapper.openSession(true);
		session.insert("insertCustomer",customer);
	}

}







