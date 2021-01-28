package Java.Data;

public class Address {

	private String street;
	private String zip;

	public Address(String street, String zip) {
		this.street = street;
		this.zip = zip;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "{\"street\":\"" + street + ",\"zip\":\"" + zip + "\"}";
	}
}
