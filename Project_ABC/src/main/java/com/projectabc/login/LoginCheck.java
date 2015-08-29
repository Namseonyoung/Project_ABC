package com.projectabc.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.commons.dbcp.BasicDataSource;
import com.projectabc.member.Member;

public class LoginCheck {
	BasicDataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	Member membersBean = new Member();
	boolean idcheck; //Member�� id�� check
	
	public LoginCheck(){
		
		try{
			Context init = new InitialContext();
			ds=(BasicDataSource) init.lookup ("java:comp/env/jdbc/OracleDB") ;
			}catch(Exception ex){
				return;
		} //member�� ȸ�� db�� ����-���� ....�´����𸣰ھ��Ф�
	}
	
	public boolean MemberidCheck(String id){
		try{
			con=ds.getConnection();
			pstmt=con.prepareStatement("select * from MemberById where id=?"); 
			// ���Ⱑ db���� id check�κ��ε� select���� �´��� �� �𸣰ھ��
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				return true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				 e.printStackTrace();
			}
		}
		return idcheck;
	}
}
		
		// member id check�ϴ� �κ�
	//��й�ȣ�� id�� �Է��� ������� ��й�ȣ��
	//�Է��� id�� ���ڵ� ��й�ȣ�� ��ġ�ϴ����� Ȯ���ؾ��ϴµ�
	//�װ� �� �𸣰ھ�� usebean���� �ѹ��� �ϸ� �����Ű�����..
	//���ڵ带 bean�����Ͽ� ��°� ���������..�̤�