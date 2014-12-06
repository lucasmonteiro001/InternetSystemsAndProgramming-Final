package utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Organization;

public class OrganizationDAO {

	public OrganizationDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addOrganization(Organization organization) {
		JDBCHelper jdbc = new JDBCHelper();
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(organization.getName());
		param.add(organization.getAddress());

		try {
			if (readOrganization(organization) == null) { //To prevent multiple instances of same organization
				jdbc.insertDB(
						"INSERT INTO organization (name, address) VALUES (?, ?);",
						param);
			}

			jdbc.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Organization readOrganization(Organization organization) {
		JDBCHelper jdbc = new JDBCHelper();
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(organization.getName());
		ResultSet rs1 = jdbc
				.queryDB(
						"SELECT * FROM organization WHERE organization.name =?;",
						param);
		try {
			if (rs1 != null && rs1.next()) {
				Organization returnOrganization = new Organization ();
				returnOrganization.setId (rs1.getInt("id"));
				returnOrganization.setName(rs1.getString("name"));
				returnOrganization.setAddress(rs1.getString("address"));
				
				return returnOrganization;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}
