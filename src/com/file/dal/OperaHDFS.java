package com.file.dal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.file.model.directory;
import com.file.model.document;


/**
 * @author 三劫散仙
 * Java API操作HDFS
 * 工具类
 * 
 * **/
public class OperaHDFS {
	
		
	/***
	 * 加载配置文件
	 * **/
	 Configuration conf=new Configuration();
	 
	
	 //创建用户文件夹
	 public boolean  CreateUserDirectoryOnHDFS(String username)throws Exception{
		 
		    String dst="hdfs://10.15.8.188:9000//user/"+username;
			FileSystem fs=FileSystem.get(URI.create(dst),conf);
		    Path p =new Path(dst);
			if(fs.mkdirs(p)){
				fs.close();//释放资源
				System.out.println("创建文件夹成功.....");
				return true;
			}
			return false;
		 
	 }
	 //创建文件夹
	 public boolean  CreateDirectoryOnHDFS(String dst)throws Exception{
		 
		 
			FileSystem fs=FileSystem.get(URI.create(dst),conf);
		    Path p =new Path(dst);
			if(fs.mkdirs(p)){
				fs.close();//释放资源
				System.out.println("创建文件夹成功.....");
				return true;
			}
			return false;
		 
	 }
	 public  boolean ShareFile(String dst,String user)throws Exception{
			//加载默认配置
		//    int lenpoint=dst.lastIndexOf("/", dst.length()-2);
		//    System.out.println("***"+dst);
		//    String file=dst.substring(lenpoint+1);
		    
		    FileSystem fs=FileSystem.get(URI.create(dst),conf);
			Path source=new Path(dst);
			Path direction=new Path("hdfs://10.15.8.188:9000//user/"+user+"/");
		
	        boolean flag=true;
	        try {
				fs.copyFromLocalFile(source, direction);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag=false;
			}
	        System.out.println("分享成功........");
	        
	   
	        fs.close();//释放资源
	        
	      return flag;
			
		}
	 /* 
	  * 读取HDFS某个文件夹的所有
	  
	  * **/
	    public List<directory>  GetHDFSDirectoryListAll(String directory, String username ) throws Exception{

	    	List<directory> listdir=new ArrayList<directory>();
	        String  dst="hdfs://10.15.8.188:9000//user/"+username+"/";
	    
	        FileSystem hdfs=FileSystem.get(URI.create(dst),conf);
	        //获取日志文件的根目录
	        Path listf =new Path(dst);
	      //获取根目录下的所有2级子文件目录
	        FileStatus stats[]=hdfs.listStatus(listf);
	      //自定义j，方便查看插入信息
	        int count=0;
	  //      System.out.println("的所有2级子文件目录个数："+stats.length);
	         for(int i = 0; i < stats.length; i++){
	        	if(stats[i].isDir()){
	        		directory dir=new directory(); 
	        		dir.setName(stats[i].getPath().toString());
	        		System.out.print(stats[i].getModificationTime());	        		
	        		dir.setTime(new Date(stats[i].getModificationTime()));
	        		dir.setUrl(stats[i].getPath().toString());
	        	//	dir.setDirectory(stats[i].getPath().toString());
	        		listdir.add(dir);
	        	}

	      }
	         System.out.println("dir count"+count);
	        	 
	         hdfs.close();
	       
             return listdir;
	    }
	    public List<document>  GetHDFSDocumentListAll(String directory, String username ) throws Exception{

	      	List<document> listdoc=new ArrayList<document>();
	        String  dst="hdfs://10.15.8.188:9000//user/"+username+"/";
	       
	        FileSystem hdfs=FileSystem.get(URI.create(dst),conf);
	        //获取日志文件的根目录
	        Path listf =new Path(dst);
	      //获取根目录下的所有2级子文件目录
	        FileStatus stats[]=hdfs.listStatus(listf);
	      //自定义j，方便查看插入信息
	        int count=0;
	  //      System.out.println("的所有2级子文件目录个数："+stats.length);
	         for(int i = 0; i < stats.length; i++){
	        
	        	if(!stats[i].isDir()){
	        		document doc=new document();	        		
	        		doc.setFilename(stats[i].getPath().toString());
	        		doc.setUrl(stats[i].getPath().toString());
	        		doc.setTime(new Date(stats[i].getModificationTime()));
	        		listdoc.add(doc);
	        	}

	      }
	        	 
	         hdfs.close();
	       
             return listdoc;
	    }

      
	    /**
		  * 重名名一个文件夹或者文件
		  * 
		  * **/
		 public  void renameFileOrDirectoryOnHDFS(String directory, String username)throws Exception{
			 
			    String url="hdfs://10.15.8.188:9000//user/"+username+directory;
				FileSystem fs=FileSystem.get(conf);
			    Path p1 =new Path("hdfs://10.2.143.5:9090/root/myfile/my.txt");
			    Path p2 =new Path("hdfs://10.2.143.5:9090/root/myfile/my2.txt");
				fs.rename(p1, p2);
				
				fs.close();//释放资源
				System.out.println("重命名文件夹或文件成功.....");
			 
		 }
	 /**
	  * 从HDFS上下载文件或文件夹到本地
	  * 
	  * **/
	 public  void downloadFileorDirectoryOnHDFS()throws Exception{
		 
			FileSystem fs=FileSystem.get(conf);
		    Path p1 =new Path("hdfs://10.2.143.5:9090/root/myfile//my2.txt");
		    Path p2 =new Path("D://7");
			fs.copyToLocalFile(p1, p2);
			fs.close();//释放资源
			System.out.println("下载文件夹或文件成功.....");
		 
	 }
	 /**
	  * 在HDFS上创建一个文件夹
	  * 
	  * **/
	 public  void createDirectoryOnHDFS()throws Exception{
		 
			FileSystem fs=FileSystem.get(conf);
		    Path p =new Path("hdfs://10.2.143.5:9090/root/myfile");
			fs.mkdirs(p);
			fs.close();//释放资源
			System.out.println("创建文件夹成功.....");
		 
	 }
	 
	 /**
	  * 在HDFS上删除一个文件夹
	  * 
	  * **/
	 public  void deleteDirectoryOnHDFS()throws Exception{
		 
			FileSystem fs=FileSystem.get(conf);
		    Path p =new Path("hdfs://10.2.143.5:9090/root/myfile");
			fs.deleteOnExit(p);
			fs.close();//释放资源
			System.out.println("删除文件夹成功.....");
		 
	 }
	 /**
	  * 在HDFS上创建一个文件
	  * 
	  * **/
	 public  void createFileOnHDFS()throws Exception{
		 
			FileSystem fs=FileSystem.get(conf);
		    Path p =new Path("hdfs://10.2.143.5:9090/root/abc.txt");
			fs.createNewFile(p);
			//fs.create(p);
			fs.close();//释放资源
			System.out.println("创建文件成功.....");
		 
	 }
	 
	 /**
	  * 在HDFS上删除一个文件
	  * 
	  * **/
	 public  void deleteFileOnHDFS()throws Exception{
		 
			FileSystem fs=FileSystem.get(conf);
		    Path p =new Path("hdfs://10.2.143.5:9090/root/abc.txt");
			fs.deleteOnExit(p);
			fs.close();//释放资源
			System.out.println("删除成功.....");
		 
	 }
	 
	 
	/***
	 * 上传本地文件到
	 * HDFS上
	 * 
	 * **/
	

}
