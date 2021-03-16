
// import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.*;
import java.sql.*;
import java.util.*;

public class StudImpl extends UnicastRemoteObject implements StudInterface {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd;

	public StudImpl() throws RemoteException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			stmt = conn.createStatement();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String insert(int empno, String ename, String job, int mgr, String hiredate, int sal, int comm, int deptno)
			throws RemoteException {
		// empno, ename, job, mgr, hiredate, sal, comm, deptno
		System.out.println("insert() called");
		try {
			stmt.executeUpdate("insert into new_emp values(" + empno + ",'" + ename + "','" + job + "'," + mgr
					+ ", TO_DATE('" + hiredate + "', 'DD-MM-YYYY')," + sal + "," + comm + "," + deptno + ")");
			return "Tuple Inserted";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Error Occurred";
	}

	public String update(int empid, String newJob) throws RemoteException {
		System.out.println("update() called");
		try {
			int rowsAffected = stmt.executeUpdate("update new_emp set job='" + newJob + "' where empno=" + empid);
			if (rowsAffected == 0)
				return "No such record exists";
			return rowsAffected + " rows updated";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Error Occurred: Update Failed";
	}

	public String delete(int empid) throws RemoteException {
		System.out.println("delete() called");
		try {
			int rowsAffected = stmt.executeUpdate("delete from new_emp where empno=" + empid);
			if (rowsAffected == 0)
				return "No such record exists";
			return rowsAffected + " rows deleted";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Error Occurred: Delete Failed";
	}

	public ArrayList<String> select() throws RemoteException {
		System.out.println("select() called");
		ArrayList<String> res = new ArrayList<>();
		try {
			rs = stmt.executeQuery("select * from new_emp");
			rsmd = rs.getMetaData();
			int numOfCol = rsmd.getColumnCount();
			int j = 0;

			String columnNames = "";
			for (int i = 1; i <= numOfCol; i++) {
				columnNames += rsmd.getColumnName(i) + "\t";
			}
			res.add("");
			res.set(j, columnNames);
			j++;

			String temp = "";
			while (rs.next()) {
				res.add("");
				for (int i = 1; i <= numOfCol; i++) {
					temp = rs.getString(i);
					res.set(j, res.get(j) + temp + "\t");

				}
				j++;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<String> selectWithWhere(String job) throws RemoteException {
		System.out.println("selectWithWhere() called");
		ArrayList<String> res = new ArrayList<>();
		try {
			rs = stmt.executeQuery("select * from new_emp where LOWER(job)='" + job.toLowerCase() + "'");
			rsmd = rs.getMetaData();
			int numOfCol = rsmd.getColumnCount();
			int j = 0;

			String columnNames = "";
			for (int i = 1; i <= numOfCol; i++) {
				columnNames += rsmd.getColumnName(i) + "\t";
			}
			res.add("");
			res.set(j, columnNames);
			j++;

			String temp = "";
			while (rs.next()) {
				res.add("");
				for (int i = 1; i <= numOfCol; i++) {
					temp = rs.getString(i);
					res.set(j, res.get(j) + temp + "\t");

				}
				j++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
