package domain.entity;

/**
 *
 * @author skytthe
 */
public class EShippingCompany extends EUser {

	public EShippingCompany(String UserID, String Name) {
		super(UserID, Name);
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
