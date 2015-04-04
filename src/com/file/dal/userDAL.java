jianglili
package com.file.dal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.file.model.*;

public class userDAL {
	 public final static String url="jdbc:sqlserver://localhost:1433;database=filecloud;user=sa;password=mis_123";
	//连接数据库
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
				System.out.println("连接数据库出现异常！");
				e.printStackTrace();
			}
			return conn;		
		}
		//关闭数据库资源
		public void closeAll(Connection conn,PreparedStatement ps,ResultSet rs){
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("rs关闭发生异常");
					e.printStackTrace();
				}
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("ps关闭发生异常");
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("conn关闭发生异常");
					e.printStackTrace();
				}
			}
		}
		//增删改调用此方法
		//返回的值是整形
		public   User findUser(String username){
			//定义返回的整形数的初始值为0
		  User user=null;
		  ResultSet rs=null;
			
			Connection conn=null;
			PreparedStatement ps=null;
			
			try {
				conn=getConn();//获得数据库连接方法
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
				System.out.println("executeSQL执行异常");
				e.printStackTrace();
			} finally{
				closeAll(conn, ps, rs);
			}
			
			return user;
		}
		//增删改调用此方法
		public int insert(User user){
			Connection conn=null;
			PreparedStatement ps=null;
			int ret=0;
			try 
			{
				conn=getConn();//获得数据库连接方法
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
