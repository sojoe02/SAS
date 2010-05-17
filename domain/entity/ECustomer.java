package domain.entity;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author skytthe
 */
public class ECustomer extends EUser {

	private String Company;
	private String Adress;

	private Map<String, EOrder> order = new HashMap<String, EOrder>();

	public ECustomer(String UserID, String Name, String Company, String Adress) {
		super(UserID, Name);
		this.Company = Company;
		this.Adress = Adress;
	}

	@Override
	void makeOrder() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	void getOrder() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
