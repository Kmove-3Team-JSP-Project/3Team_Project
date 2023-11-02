package company.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import company.dao.CompanyDao;
import company.model.Company;
import jdbc.connection.ConnectionProvider;

public class CompanyService {
	private CompanyDao companyDao = new CompanyDao();
	private int size = 10;

	public CompanyPage getCompany(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = companyDao.getCount(conn);
			List<Company> content = companyDao.select(conn, (pageNum - 1) * size, size);
			return new CompanyPage(total, pageNum, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean registerCompany(Company company) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Company> searchCompanies(String searchKeyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Company> searchResults = new ArrayList<>();

		try {
			// 데이터베이스 연결 및 쿼리 작성
			conn = getConnection();
			String sql = "SELECT * FROM company WHERE company_name LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");

			// 쿼리 실행
			rs = pstmt.executeQuery();

			// 검색 결과를 리스트에 저장
			while (rs.next()) {
				Company company = new Company();
				company.setCompany_No(rs.getInt("company_no"));
				company.setCompany_Name(rs.getString("company_name"));
				company.setMaster(rs.getString("master"));
				company.setPhone(rs.getString("phone"));
				company.setAddress(rs.getString("address"));
				company.setMyStorage(rs.getString("mystorage"));
				searchResults.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결 및 리소스 해제
			close(conn, pstmt, rs);
		}

		return searchResults;
	}

	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		return null;
	}

}
