package com.file.dal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.file.model.*;

public class userDAL {
	 public final static String url="jdbc:sqlserver://localhost:1433;database=filecloud;user=sa;password=mis_123";
	//�������ݿ�
		public Connection getConn(){
			Connection conn=null;
		
			try {
				  try {
						 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				         } 
				    catch (ClassNotFoundException e) {
					   // TODO Auto-generated catch block
					  e.printStackTrace();
				       }
				conn=DriverManager.getConnection(url);
			} catch (SQLException e) {
				System.out.println("�������ݿ�����쳣��");
				e.printStackTrace();
			}
			return conn;		
		}
		//�ر����ݿ���Դ
		public void closeAll(Connection conn,PreparedStatement ps,ResultSet rs){
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("rs�رշ����쳣");
					e.printStackTrace();
				}
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("ps�رշ����쳣");
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("conn�رշ����쳣");
					e.printStackTrace();
				}
			}
		}
		//��ɾ�ĵ��ô˷���
		//���ص�ֵ������
		public   User findUser(String username){
			//���巵�ص��������ĳ�ʼֵΪ0
		  User user=null;
		  ResultSet rs=null;
			
			Connection conn=null;
			PreparedStatement ps=null;
			
			try {
				conn=getConn();//������ݿ����ӷ���
				ps=conn.prepareStatement("SELECT *  FROM [user] where [username]=?");			
				ps.setString(1, username);			
	            rs=ps.executeQuery();			
			    while(rs.next()){
			    user=new User();
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setEmail(rs.getString(3));		
			    }
			
			} catch (SQLException e) {
				System.out.println("executeSQLִ���쳣");
				e.printStackTrace();
			} finally{
				closeAll(conn, ps, rs);
			}
			
			return user;
		}
		//��ɾ�ĵ��ô˷���
		public int insert(User user){
			Connection conn=null;
			PreparedStatement ps=null;
			int ret=0;
			try 
			{
				conn=getConn();//������ݿ����ӷ���
				ps=conn.prepareStatement("insert into [user] (username,password,email) values(?,?,?)");			
				ps.setString(1, user.getUsername());	
				ps.setString(2, user.getPassword());	
				ps.setString(3, user.getEmail());	
			 
	            ret=ps.executeUpdate();	
				
			} 
			catch (SQLException e) 
			{
				System.out.println(e.getMessage());
			}
			finally
			{
				closeAll(conn, ps, null);
			}
			return ret;
		}

}
