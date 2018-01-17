package com.javaex.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.AuthorVo;

public class AuthorDao {

	public void insertAuthor(AuthorVo vo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "insert into author values(seq_author_id.nextval, ? , ? )";
		    pstmt = conn.prepareStatement(query);
		    
		    pstmt.setString(1,vo.getAuthorName());
			pstmt.setString(2,vo.getAuthorDesc());
			
			
			int count = pstmt.executeUpdate();
		    // 4.결과처리
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		       if (rs != null) {
		            rs.close();
		        }             
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}

	}
	public List<AuthorVo> selectAuthorList() {
		// 0. import java.sql.*;
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				 List<AuthorVo> authorList = new ArrayList<AuthorVo>();
				try {
				    // 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");
				    // 2. Connection 얻어오기
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					conn = DriverManager.getConnection(url, "webdb", "webdb");
				    // 3. SQL문 준비 / 바인딩 / 실행
				    String query = " select author_id, author_name, author_desc " + 
				    			   " from author ";
				    pstmt = conn.prepareStatement(query);
				    rs = pstmt.executeQuery();
				    
				   
				    
				    // 4.결과처리
				    while(rs.next()) {
				    	
				    	AuthorVo vo = new AuthorVo();//실제 데이타는 디비안에 있으니까 디비에서 조회해야함 
				    	
				    int authorId = rs.getInt("author_id");
				    String authorName = rs.getString("author_name");
				    String authorDesc = rs.getString("author_desc");
				   
				    vo.setAuthorId(authorId);
				    vo.setAuthorName(authorName);
				    vo.setAuthorDesc(authorDesc);//메모리에만 넣은거니까 add해줘야함 
				    
				    authorList.add(vo);
				    
				    
				   } 
				    
				} catch (ClassNotFoundException e) {
				    System.out.println("error: 드라이버 로딩 실패 - " + e);
				} catch (SQLException e) {
				    System.out.println("error:" + e);
				} finally {
				   
				    // 5. 자원정리
				    try {
				        if (rs != null) {
				            rs.close();
				        }                
				        if (pstmt != null) {
				            pstmt.close();
				        }
				        if (conn != null) {
				            conn.close();
				        }
				    } catch (SQLException e) {
				        System.out.println("error:" + e);
				    }

				}

				return authorList;
	} 
	
}
