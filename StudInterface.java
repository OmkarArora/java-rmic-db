import java.rmi.*;
import java.util.*;

interface StudInterface extends Remote {
	public String insert(int empno, String ename, String job, int mgr, String hiredate, int sal, int comm, int deptno)
			throws RemoteException;

	public String update(int empid, String job) throws RemoteException;

	public String delete(int empid) throws RemoteException;

	public ArrayList<String> select() throws RemoteException;

	public ArrayList<String> selectWithWhere(String job) throws RemoteException;
}