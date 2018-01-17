package com.javaex.main;

import java.util.List;

import com.javaex.dao.AuthorDao;
import com.javaex.dao.BookDao;
import com.javaex.vo.AuthorVo;
import com.javaex.vo.BookVo;

public class MainApp {

	public static void main(String[] args) {
		
		AuthorVo vo = new AuthorVo();
		BookVo vo1 = new BookVo();
		AuthorDao aDao = new AuthorDao();
		BookDao bDao = new BookDao();
		
		List<AuthorVo> authorList = aDao.selectAuthorList();
		List<BookVo> BookList = bDao.selectBookList();
		
		for( AuthorVo aDao1 : authorList ) {
			System.out.println(aDao1);
		}
		System.out.println("==============================================================");
		
		for( BookVo bDao1 : BookList) {
			System.out.println(bDao1);
		}
		
	}

}
