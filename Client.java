import java.rmi.Naming;
import java.util.*;

public class Client {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> _result;
		try {
			String insertStr = "rmi://localhost/insert";
			String updateStr = "rmi://localhost/update";
			String deleteStr = "rmi://localhost/delete";
			String selectStr = "rmi://localhost/select";
			String selectWWStr = "rmi://localhost/selectWithWhere";
			StudInterface insertObj = (StudInterface) Naming.lookup(insertStr);
			StudInterface updateObj = (StudInterface) Naming.lookup(updateStr);
			StudInterface deleteObj = (StudInterface) Naming.lookup(deleteStr);
			StudInterface selectObj = (StudInterface) Naming.lookup(selectStr);
			StudInterface selectWWObj = (StudInterface) Naming.lookup(selectWWStr);

			while (true) {
				System.out.println("\n-- MENU --");
				System.out.println("1. SELECT");
				System.out.println("2. SELECT with WHERE");
				System.out.println("3. INSERT");
				System.out.println("4. UPDATE");
				System.out.println("5. DELETE");
				System.out.println("0. EXIT\n");
				int choice = sc.nextInt();
				switch (choice) {
				case 0:
					return;
				case 1:
					_result = selectObj.select();
					for (int i = 0; i < _result.size(); i++) {
						System.out.println(_result.get(i));
					}
					break;
				case 2:
					System.out.print("\nEnter emp job: ");
					String select_empjob = sc.next();
					_result = selectWWObj.selectWithWhere(select_empjob);
					for (int i = 0; i < _result.size(); i++) {
						System.out.println(_result.get(i));
					}
					break;
				case 3:
					// empno, ename, job, mgr, hiredate, sal, comm, deptno
					System.out.print("Enter empno: ");
					int empno = sc.nextInt();
					System.out.print("\nEnter emp name: ");
					String empname = sc.next();
					System.out.print("\nEnter emp job: ");
					String empjob = sc.next();
					System.out.print("\nEnter mgr: ");
					int mgr = sc.nextInt();
					System.out.print("\nEnter hiredate(DD-MM-YYYY): ");
					String hiredate = sc.next();
					System.out.print("\nEnter salary: ");
					int salary = sc.nextInt();
					System.out.print("\nEnter comm: ");
					int comm = sc.nextInt();
					System.out.print("\nEnter deptno: ");
					int deptno = sc.nextInt();
					String insertRes = insertObj.insert(empno, empname, empjob, mgr, hiredate, salary, comm, deptno);
					System.out.println("INSERT Result: " + insertRes);
					break;
				case 4:
					System.out.print("Enter empno: ");
					int new_empno = sc.nextInt();
					System.out.print("\nEnter updated emp job: ");
					String new_empjob = sc.next();
					System.out.println("UPDATE Result: " + updateObj.update(new_empno, new_empjob));
					break;
				case 5:
					System.out.print("Enter empno to delete: ");
					int del_empno = sc.nextInt();
					System.out.println("DELETE Result: " + deleteObj.delete(del_empno));
					break;
				default:
					System.out.println("-Invalid Input-");
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
