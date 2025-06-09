package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.TopCategory;

//  해당 영역은 MVC에서 Model 영역으로, DB 관련된 코드만 들어있어야 한다. 즉, 디자인코드가 절대 들어가 있으면 안됨.

public class TopCategoryDAO {
	// dbManager는 인스턴스 변수이므로, 누군가가 new TopCategoryDAO() 호출시 초기화되면서 DBManager의 인스턴스
	// 값으로 채워진다!
	DBManager dbManager = DBManager.getInstance();

	public TopCategoryDAO() {
	}

	public List selectAll() {
		// 쿼리를 날리기 위한 객체
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();

		con = dbManager.getConnetion();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select * from topcategory");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			list = new ArrayList();
			
			while (rs.next()) {
				TopCategory topcategory = new TopCategory();
				topcategory.setTopcategory_id(rs.getInt("topcategory_id")); // pk
				topcategory.setTop_name(rs.getString("top_name"));
				list.add(topcategory);
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt, rs);
		}

//		만일 배열로 rs의 데이터를 담게되면, 배열의 크기를 명시해야 하는 이유로, rs.last(), getRow()
//		rs.beforeFirst() A
//		TopCategory[] list = new TopCategory[?]
//	    Collection framework? 객체를 모아서 처리하는데 유용한 API를 지원하는 패키지
//		모여진 모습은 2가지 밖에 없다.
//		1)순서O - List, Queue 2) 순서X Set, Map
		
		return list;
	};

	public DBManager getDbManager() {
		return dbManager;
	}

	public void setDbManager(DBManager dbManager) {
		this.dbManager = dbManager;
	}

	public void select() {

	}

	public void update() {

	}

	public void delete() {

	}
}
