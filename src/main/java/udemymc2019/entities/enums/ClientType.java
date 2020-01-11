package udemymc2019.entities.enums;

public enum ClientType {
	NATURAL_PERSON(1),
	LEGAL_ENTITY(2);
	
	private int code;
	
	private ClientType(int code) {
		this.code =  code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static ClientType valueOf(int code) {
		for (ClientType value : ClientType.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		
		throw new IllegalArgumentException("Invalid ClientType code.");
	}
	
}