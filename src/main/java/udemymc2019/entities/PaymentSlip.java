package udemymc2019.entities;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import udemymc2019.entities.enums.PaymentStatus;

@Entity
@Table(name="tb_payment_slip")
public class PaymentSlip extends Payment {
	
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant expirationDate;
	
	String code;
	
	public PaymentSlip() {
		
	}
	
	public PaymentSlip(Long id, PaymentStatus status, Order order, Instant confirmationDate, Instant expirationDate, String code) {
		super(id, status, order, confirmationDate);
		this.expirationDate = expirationDate;
		this.code = code;
	}
	
	public Instant getExpirationDate() {
		return expirationDate;
	}
	
	public void setExpirationDate(Instant expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
}
