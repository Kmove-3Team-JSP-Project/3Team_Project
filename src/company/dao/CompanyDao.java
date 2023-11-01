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
		Statement stmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("insert into company"
					+ "(company_no, company_name, master, phone, address, myStorage)" + "values(?,?,?,?,?,?)");
			pstmt.setInt(1, company.getCompany_No());
			pstmt.setString(2, company.getCompany_Name());
			pstmt.setString(3, company.getMaster());
			pstmt.setString(4, company.getPhone());
			pstmt.setString(5, company.getAddress());
			pstmt.setString(6, company.getMyStorage());
			int insertedCount = pstmt.executeUpdate();

			if (insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select last_insert_no() from company");
				if (rs.next()) {
					Integer newNum = rs.getInt(1);
					return new Company(newNum, company.getCompany_Name(), company.getMaster(), company.getPhone(),
							company.getAddress(), company.getMyStorage());
				}
			}
			return null;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	public List<Company> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"SELECT * FROM (SELECT ROWNUM AS rnum, a.*FROM(SELECT * FROM Company ORDER BY company_No DESC) a WHERE ROWNUM <=?) WHERE rnum >=?");
			pstmt.setInt(1, startRow + size);
			pstmt.setInt(2, startRow + 1);
			rs = pstmt.executeQuery();
			List<Company> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertCompany(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public Company convertCompany(ResultSet rs) throws SQLException {
		return new Company(rs.getInt("company_No"), rs.getString("company_name"), rs.getString("master"),
				rs.getString("phone"), rs.getString("address"), rs.getString("myStorage"));
	}

	public int getCount(Connection conn) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM Company")) {
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
			return 0;
		}
	}

	public Company selectByClass(Connection conn, String Company_class) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM company WHERE Company_class = ?");
			pstmt.setString(1, Company_class);
			rs = pstmt.executeQuery();
			Company company = null;

			if (rs.next()) {
				company = new Company(rs.getInt("company_No"), rs.getString("company_name"), rs.getString("master"),
						rs.getString("phone"), rs.getString("address"), rs.getString("myStorage"));
			}
			return company;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
