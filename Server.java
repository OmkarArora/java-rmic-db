
// import java.io.Serializable;
import java.rmi.*;

public class Server {
	public static void main(String args[]) throws Exception {
		try {
			StudImpl ob = new StudImpl();
			Naming.rebind("insert", ob);
			Naming.rebind("update", ob);
			Naming.rebind("delete", ob);
			Naming.rebind("select", ob);
			Naming.rebind("selectWithWhere", ob);
			System.out.println("Objects registered");

		} catch (Exception e) {
			throw e;
		}
	}
}
