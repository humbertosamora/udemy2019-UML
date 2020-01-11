package udemymc2019.entities.enums;

public enum PaymentStatus {
	PENDING (1),
	CONFIRMED (2),
	CANCELED (3);
	
	private int code;
	
	private PaymentStatus(int code) {
		this.code =  code;
	}
	
	public int getCode() {
		return code;
	}
	
	//public static PaymentStatus valueOf(Integer code) {
	public static PaymentStatus valueOf(int code) {
		
		//if (code == null) return false;
		
		for (PaymentStatus value : PaymentStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		
		throw new IllegalArgumentException("Invalid PaymentStatus code.");
	}
	
}