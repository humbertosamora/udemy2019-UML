package udemymc2019.entities;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Table;

import udemymc2019.entities.enums.PaymentStatus;

@Entity
@Table(name="tb_payment_credit_card")
public class PaymentCreditCard extends Payment {
	
	private static final long serialVersionUID = 1L;
	
	Integer installments;
	
	String authorizationCode;
		
	public PaymentCreditCard() {
		
	}
	
	public PaymentCreditCard(Long id, PaymentStatus status, Order order, Instant confirmationDate, Integer installments, String authorizationCode) {
		super(id, status, order, confirmationDate);
		this.installments = installments;
		this.authorizationCode = authorizationCode;
	}
	
	public Integer getInstallments() {
		return installments;
	}
	
	public void setInstallments(Integer installments) {
		this.installments = installments;
	}
	
	public String getAuthorizationCode() {
		return authorizationCode;
	}
	
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
	
}
