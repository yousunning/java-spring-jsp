package com.example.news;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class NewsDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";
	
	public Connection open() {
		Connection conn = null;
		try {
			//1. JDBC드라이버 로드
			Class.forName(JDBC_DRIVER);
			//2. 데이터베이스 연결
			conn = DriverManager.getConnection(JDBC_URL, "jwbook", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return conn;
	}
	
	// 뉴스 목록 전체를 가지고 오기 위한 메서드
	public List<News> getAll() throws Exception {
		Connection conn = open();
		List<News> newsList = new ArrayList<>();
		
		String sql = "select aid, title,  FORMATDATETIME(date, 'yyyy-MM-dd hh:mm:ss') as cdate,";
			sql +=  "       img, content   from news";
		//3. prepareStatement 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//4. sql문 전송
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs) {
			//5. 결과값 처리:  products ArrayList 생성
			while(rs.next()) {
				News n = new News();
				n.setAid(rs.getInt("aid"));
				n.setTitle(rs.getString("title"));
				n.setDate(rs.getString("cdate"));
				n.setImg(rs.getString("img"));
				n.setContent(rs.getString("content"));
				
				newsList.add(n);
			}
			
			return newsList;
		}

	}
	

	// 뉴스 목록 에서 페이지리스트를 가지고 오기 위한 메서드
	// offset 시작인덱스, limit 페이지당 건수
	public List<News> getAll(int offset, int limit) throws Exception {
		Connection conn = open();
		List<News> newsList = new ArrayList<>();
		
		String sql = "select aid, title,  FORMATDATETIME(date, 'yyyy-MM-dd hh:mm:ss') as cdate,";
			sql +=  "       img, content   from news order by date desc";
			sql +=  "  limit " + limit + " offset " + offset;
			
		//3. prepareStatement 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//4. sql문 전송
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs) {
			//5. 결과값 처리:  products ArrayList 생성
			while(rs.next()) {
				News n = new News();
				n.setAid(rs.getInt("aid"));
				n.setTitle(rs.getString("title"));
				n.setDate(rs.getString("cdate"));
				n.setImg(rs.getString("img"));
				n.setContent(rs.getString("content"));
				
				newsList.add(n);
			}
			
			return newsList;
		}

	}
	
	
	// 뉴스 세부 내용 조회 :  목록 링크를 클릭했을때
	public News getNews(int aid) throws Exception {
		Connection conn = open();
		News n = new News();
		
		String sql = "select aid, title,  FORMATDATETIME(date, 'yyyy-MM-dd hh:mm:ss') as cdate,";
			sql +=  "       img, content   from news where aid = ?";
			
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, aid);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		
		try(conn; pstmt; rs) {
			n.setAid(rs.getInt("aid"));
			n.setTitle(rs.getString("title"));
			n.setImg(rs.getString("img"));
			n.setDate(rs.getString("cdate"));
			n.setContent(rs.getString("content"));	
			
			return n;
		}
			
	}
	
	// 뉴스를 추가하는 메서드 매개변수롤 DO 클래스  News 입력받음
	public void addNews(News n) throws Exception {
		Connection conn = open();
		
		String sql = "insert into news( TITLE, IMG, DATE, CONTENT ) ";
			sql +=  " values(?, ?, CURRENT_TIMESTAMP(), ?)";
			
		PreparedStatement pstmt = conn.prepareStatement(sql);	
		try(conn; pstmt) {
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getImg());
			pstmt.setString(3, n.getContent());
			
			pstmt.executeUpdate();
		}
		
	}
	
	// 뉴스 수정
	public void updateNews(News n) throws Exception {
		Connection conn = open();
		
		String sql = "update news ";
			sql +=  " set title = ?, date=CURRENT_TIMESTAMP(), ";
			sql +=  "     content = ?, img = ? ";
			sql +=  " where aid = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);	
		try(conn; pstmt) {
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getContent());
			pstmt.setString(3, n.getImg());
			pstmt.setInt(4, n.getAid());
			
			pstmt.executeUpdate();
		}
		
	}	
	
	// 뉴스 삭제를 위한 메서드
	public void delNews (int aid) throws SQLException {
		Connection conn = open();
		
		String sql = "delete from news where aid = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);	
		try(conn; pstmt) {
			pstmt.setInt(1, aid);
			
			if(pstmt.executeUpdate() == 0) {
				throw new SQLException("데이타 삭제 에러");
			}
		}
		
	}
	
	
	
	
}
