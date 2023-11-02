package company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import company.model.Company;
import jdbc.JdbcUtil;

public class CompanyDao {

	// 주차권 정보를 데이터베이스에 삽입하는 메서드
	public Company insert(Connection conn, Company company) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// SQL 쿼리를 사용하여 주차권 정보를 데이터베이스에 삽입
			pstmt = conn.prepareStatement("insert into company "
					+ " (company_No, company_name, master, phone, address, myStorage)" + "values(?,?,?,?,?,?)");
			pstmt.setInt(1, company.getCompany_No()); // 1. 주차권 번호 설정
			pstmt.setString(2, company.getCompany_Name()); // 2. 차량 번호 설정
			pstmt.setString(3, company.getMaster()); // 3. 차주 전화번호 설정
			pstmt.setString(4, company.getPhone()); // 4. 주차권 등급 설정
			pstmt.setString(5, company.getAddress()); // 5. 주차권 상태 설정
			pstmt.setString(6, company.getMyStorage()); // 6. 시작 일자 설정
			return null;

		} finally {
			// 사용된 자원을 닫음
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	public List<Company> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = conn.prepareStatement("select * from (select rownum as rnum, a.* "
					+ "from (select * from company order by carno desc) a " + "where rownum <=?) where rnum >=?");
			pstmt.setInt(1, startRow + size); // 첫 번째 바인딩 변수 설정
			pstmt.setInt(2, startRow + 1); // 두 번째 바인딩 변수 설정

			// SQL 쿼리 실행
			rs = pstmt.executeQuery();
			List<Company> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertCompany(rs)); // ResultSet의 결과를 Ticket 객체로 변환하고 목록에 추가
			}
			return result; // 페이징된 주차권 목록을 반환
		} finally {
			JdbcUtil.close(rs); // ResultSet 자원 닫기
			JdbcUtil.close(pstmt); // PreparedStatement 자원 닫기
		}
	}

	private Company convertCompany(ResultSet rs) throws SQLException {
		return new Company(rs.getInt("company_No"), rs.getString("Company_Name"), rs.getString("master"),
				rs.getString("phone"), rs.getString("address"), rs.getString("myStorage"));
	}

	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from company");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}

	}

}