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
 * @author ����ɢ��
 * Java API����HDFS
 * ������
 * 
 * **/
public class OperaHDFS {
	
		
	/***
	 * ���������ļ�
	 * **/
	 Configuration conf=new Configuration();
	 
	
	 //�����û��ļ���
	 public boolean  CreateUserDirectoryOnHDFS(String username)throws Exception{
		 
		    String dst="hdfs://10.15.8.188:9000//user/"+username;
			FileSystem fs=FileSystem.get(URI.create(dst),conf);
		    Path p =new Path(dst);
			if(fs.mkdirs(p)){
				fs.close();//�ͷ���Դ
				System.out.println("�����ļ��гɹ�.....");
				return true;
			}
			return false;
		 
	 }
	 //�����ļ���
	 public boolean  CreateDirectoryOnHDFS(String dst)throws Exception{
		 
		 
			FileSystem fs=FileSystem.get(URI.create(dst),conf);
		    Path p =new Path(dst);
			if(fs.mkdirs(p)){
				fs.close();//�ͷ���Դ
				System.out.println("�����ļ��гɹ�.....");
				return true;
			}
			return false;
		 
	 }
	 public  boolean ShareFile(String dst,String user)throws Exception{
			//����Ĭ������
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
	        System.out.println("����ɹ�........");
	        
	   
	        fs.close();//�ͷ���Դ
	        
	      return flag;
			
		}
	 /* 
	  * ��ȡHDFSĳ���ļ��е�����
	  
	  * **/
	    public List<directory>  GetHDFSDirectoryListAll(String directory, String username ) throws Exception{

	    	List<directory> listdir=new ArrayList<directory>();
	        String  dst="hdfs://10.15.8.188:9000//user/"+username+"/";
	    
	        FileSystem hdfs=FileSystem.get(URI.create(dst),conf);
	        //��ȡ��־�ļ��ĸ�Ŀ¼
	        Path listf =new Path(dst);
	      //��ȡ��Ŀ¼�µ�����2�����ļ�Ŀ¼
	        FileStatus stats[]=hdfs.listStatus(listf);
	      //�Զ���j������鿴������Ϣ
	        int count=0;
	  //      System.out.println("������2�����ļ�Ŀ¼������"+stats.length);
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
	        //��ȡ��־�ļ��ĸ�Ŀ¼
	        Path listf =new Path(dst);
	      //��ȡ��Ŀ¼�µ�����2�����ļ�Ŀ¼
	        FileStatus stats[]=hdfs.listStatus(listf);
	      //�Զ���j������鿴������Ϣ
	        int count=0;
	  //      System.out.println("������2�����ļ�Ŀ¼������"+stats.length);
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
		  * ������һ���ļ��л����ļ�
		  * 
		  * **/
		 public  void renameFileOrDirectoryOnHDFS(String directory, String username)throws Exception{
			 
			    String url="hdfs://10.15.8.188:9000//user/"+username+directory;
				FileSystem fs=FileSystem.get(conf);
			    Path p1 =new Path("hdfs://10.2.143.5:9090/root/myfile/my.txt");
			    Path p2 =new Path("hdfs://10.2.143.5:9090/root/myfile/my2.txt");
				fs.rename(p1, p2);
				
				fs.close();//�ͷ���Դ
				System.out.println("�������ļ��л��ļ��ɹ�.....");
			 
		 }
	 /**
	  * ��HDFS�������ļ����ļ��е�����
	  * 
	  * **/
	 public  void downloadFileorDirectoryOnHDFS()throws Exception{
		 
			FileSystem fs=FileSystem.get(conf);
		    Path p1 =new Path("hdfs://10.2.143.5:9090/root/myfile//my2.txt");
		    Path p2 =new Path("D://7");
			fs.copyToLocalFile(p1, p2);
			fs.close();//�ͷ���Դ
			System.out.println("�����ļ��л��ļ��ɹ�.....");
		 
	 }
	 /**
	  * ��HDFS�ϴ���һ���ļ���
	  * 
	  * **/
	 public  void createDirectoryOnHDFS()throws Exception{
		 
			FileSystem fs=FileSystem.get(conf);
		    Path p =new Path("hdfs://10.2.143.5:9090/root/myfile");
			fs.mkdirs(p);
			fs.close();//�ͷ���Դ
			System.out.println("�����ļ��гɹ�.....");
		 
	 }
	 
	 /**
	  * ��HDFS��ɾ��һ���ļ���
	  * 
	  * **/
	 public  void deleteDirectoryOnHDFS()throws Exception{
		 
			FileSystem fs=FileSystem.get(conf);
		    Path p =new Path("hdfs://10.2.143.5:9090/root/myfile");
			fs.deleteOnExit(p);
			fs.close();//�ͷ���Դ
			System.out.println("ɾ���ļ��гɹ�.....");
		 
	 }
	 /**
	  * ��HDFS�ϴ���һ���ļ�
	  * 
	  * **/
	 public  void createFileOnHDFS()throws Exception{
		 
			FileSystem fs=FileSystem.get(conf);
		    Path p =new Path("hdfs://10.2.143.5:9090/root/abc.txt");
			fs.createNewFile(p);
			//fs.create(p);
			fs.close();//�ͷ���Դ
			System.out.println("�����ļ��ɹ�.....");
		 
	 }
	 
	 /**
	  * ��HDFS��ɾ��һ���ļ�
	  * 
	  * **/
	 public  void deleteFileOnHDFS()throws Exception{
		 
			FileSystem fs=FileSystem.get(conf);
		    Path p =new Path("hdfs://10.2.143.5:9090/root/abc.txt");
			fs.deleteOnExit(p);
			fs.close();//�ͷ���Դ
			System.out.println("ɾ���ɹ�.....");
		 
	 }
	 
	 
	/***
	 * �ϴ������ļ���
	 * HDFS��
	 * 
	 * **/
	

}
