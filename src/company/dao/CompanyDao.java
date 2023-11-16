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

	public Company insert(Connection conn, Company company) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// SQL 쿼리를 사용하여 회사 정보를 데이터베이스에 삽입
			pstmt = conn.prepareStatement(
					"insert into company (company_No, company_name, master, phone, address, myStorage) values(?,?,?,?,?,?)",
					new String[] { "company_No" }); // 회사 번호를 얻기 위해 자동 생성된 키를 반환하도록 설정

			// 회사 객체의 필드 값을 설정
			pstmt.setInt(1, company.getCompany_No());
			pstmt.setString(2, company.getCompany_Name());
			pstmt.setString(3, company.getMaster());
			pstmt.setString(4, company.getPhone());
			pstmt.setString(5, company.getAddress());
			pstmt.setString(6, company.getMyStorage());

			// SQL 쿼리 실행
			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("회사 정보 등록에 실패했습니다.");
			}

			// 자동 생성된 키 값 얻기
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				int generatedKey = rs.getInt(1);
				company.setCompany_No(generatedKey);
			} else {
				throw new SQLException("회사 정보 등록에 실패했습니다. 생성된 키를 얻을 수 없습니다.");
			}

			return company;

		} finally {
			// 사용된 자원을 닫음
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public List<Company> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = conn.prepareStatement("select * from (select rownum as rnum, a.* "
					+ "from (select * from company order by rownum desc) a " + "where rownum <=?) where rnum >=?");
			pstmt.setInt(1, startRow + size); //
			pstmt.setInt(2, startRow + 1); //

			// SQL 쿼리 실행
			rs = pstmt.executeQuery();
			List<Company> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertCompany(rs)); //
			}
			return result; //
		} finally {
			JdbcUtil.close(rs); //
			JdbcUtil.close(pstmt); //
		}
	}

	public List<Company> selectbyName(Connection conn, String company_name, int startRow, int size)
			throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM (SELECT * FROM company WHERE company_name = ?) a WHERE ROWNUM <= ?) WHERE rnum >= ?")) {
			pstmt.setString(1, company_name);
			pstmt.setInt(2, startRow + size);
			pstmt.setInt(3, startRow + 1);
			try (ResultSet rs = pstmt.executeQuery()) {
				List<Company> result = new ArrayList<>();
				while (rs.next()) {
					result.add(convertCompany(rs));
				}
				return result;
			}
		}
	}

	private Company convertCompany(ResultSet rs) throws SQLException {
		return new Company(rs.getInt("company_No"), rs.getString("company_Name"), rs.getString("master"),
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

	public int getCountByName(Connection conn, String company_name) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM company WHERE company_name = ?")) {
			pstmt.setString(1, company_name);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			}
		}
	}

	public int getCountByClass(Connection conn, String master) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM company WHERE master = ?")) {
			pstmt.setString(1, master);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			}
		}
	}

	public List<Company> selectByClass(Connection conn, String master, int startRow, int size)
			throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM (SELECT * FROM company WHERE master = ?) a WHERE ROWNUM <= ?) WHERE rnum >= ?")) {
			pstmt.setString(1, master);
			pstmt.setInt(2, startRow + size);
			pstmt.setInt(3, startRow + 1);
			try (ResultSet rs = pstmt.executeQuery()) {
				List<Company> result = new ArrayList<>();
				while (rs.next()) {
					result.add(convertCompany(rs));
				}
				return result;
			}
		}
	}

	public int getCompanyNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int companyNo = 0;
		try {
			String getMaxOrderNoQuery = "SELECT MAX(order_no) FROM company";
			pstmt = conn.prepareStatement(getMaxOrderNoQuery);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				companyNo = rs.getInt(1);
				if (rs.wasNull()) { // 만약 최대값이 NULL이면 기본값으로 3000001 사용
					companyNo = 1;
				} else {
					companyNo += 1; // 다음 주문번호 생성
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return companyNo;
	}
}
