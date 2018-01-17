package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.AuthorVo;
import com.javaex.vo.BookVo;

public class BookDao {

	
	public void insertBook(BookVo vo1) {
		
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
		    String query = "insert into book values(seq_book_id.nextval, ? , ? , ? , ? )";
		    pstmt = conn.prepareStatement(query);
		    
		    pstmt.setInt(1,vo1.getBookId());
			pstmt.setString(2,vo1.getTitle());
			pstmt.setString(3, vo1.getPubs());
			pstmt.setString(4, vo1.getPub_date());
			pstmt.setInt(4, vo1.getAuthorId());
			
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
		    }}

		}

		public List<BookVo> selectBookList() {
		
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					 List<BookVo> BookList = new ArrayList<BookVo>();
					try {
					    // 1. JDBC 드라이버 (Oracle) 로딩
						Class.forName("oracle.jdbc.driver.OracleDriver");
					    // 2. Connection 얻어오기
						String url = "jdbc:oracle:thin:@localhost:1521:xe";
						conn = DriverManager.getConnection(url, "webdb", "webdb");
					    // 3. SQL문 준비 / 바인딩 / 실행
					    String query = " select b.book_id , b.title , b.pubs , "
					    		+ "to_char(b.pub_date,'YYYY/MM/DD') pub_date , "
					    		+ "b.author_id , a.author_name , a.author_desc " + 
					    		"  from book b, author a "  + " where a.author_id = b.author_id ";
					    pstmt = conn.prepareStatement(query);
					    rs = pstmt.executeQuery();
					    
					   
					    
					    // 4.결과처리
					    while(rs.next()) {
					    	
					    	BookVo vo1 = new BookVo();//실제 데이타는 디비안에 있으니까 디비에서 조회해야함 
					    	
					    int BookId = rs.getInt("book_id");
					    String title = rs.getString("title");
					    String pubs = rs.getString("pubs");
					    String pubDate = rs.getString("pub_date");
					    int authorId = rs.getInt("author_id");
					    
					    vo1.setBookId(authorId);
					    vo1.setTitle(title);
					    vo1.setPubs(pubs);//메모리에만 넣은거니까 add해줘야함 
					    vo1.setPub_date(pubDate);
					    vo1.setAuthorId(authorId);
					    
					    BookList.add(vo1);
					    
					    
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

					return BookList;
		
		
		
	}
}
