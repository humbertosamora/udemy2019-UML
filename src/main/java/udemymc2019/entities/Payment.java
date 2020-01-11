package udemymc2019.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import udemymc2019.entities.enums.PaymentStatus;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="tb_payment")
public abstract class Payment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	private Integer status;
	
	@JsonIgnore			// Ignore Jackson getOrder invocation to avoid infinite loop between order and payment objects
	@OneToOne
	@MapsId				// Payment objects are dependent of Order objects and will share the same id
	@JoinColumn(name="order_id")
	private Order order;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant confirmationDate;
	
	public Payment() {
		
	}
	
	public Payment(Long id, PaymentStatus status, Order order, Instant confirmationDate) {
		super();
		this.id = id;
		this.status = status.getCode();
		this.order = order;
		this.confirmationDate = confirmationDate;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public PaymentStatus getStatus() {
		return PaymentStatus.valueOf(status);
	}
	
	public void SetStatus(PaymentStatus status) {
		this.status = status.getCode();
	}
	
	public Instant getConfirmationDate() {
		return confirmationDate;
	}
	
	public void setConfirmationDate(Instant moment) {
		this.confirmationDate = moment;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
