package udemymc2019.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="tb_order")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant requestDate;
	
	@ManyToOne
	@JoinColumn(name="client_id")	// Foreign key name to be saved in table client (tb_client)
	private Client client;
	
	@OneToOne(mappedBy="order", cascade=CascadeType.ALL)	// CascadeType.ALL: update dependent object "payment" when updating Order object
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name="address_id")	// Foreign key name to be saved in table address (tb_address)
	private Address deliveredAddress;
	
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	public Order() {
		
	}
	
	public Order(Long id, Instant requestDate, Client client, Address deliveredAddress) {
		super();
		this.id = id;
		this.requestDate = requestDate;
		this.client = client;
		this.deliveredAddress = deliveredAddress;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Instant getRequestDate() {
		return requestDate;
	}
	
	public void setRequestDate(Instant requestDate) {
		this.requestDate = requestDate;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Address getDeliveredAddress() {
		return deliveredAddress;
	}
	
	public void setDeliveredAddress(Address deliveredAddress) {
		this.deliveredAddress = deliveredAddress;
	}
	
	public Set<OrderItem> getItems() {
		return items;
	}
	
	// Method named as get because jackson API invokes only get methods
	public Double getTotal() {
		return items.stream().mapToDouble(oi -> oi.getSubTotal()).sum();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((requestDate == null) ? 0 : requestDate.hashCode());
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
		Order other = (Order) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (requestDate == null) {
			if (other.requestDate != null)
				return false;
		} else if (!requestDate.equals(other.requestDate))
			return false;
		return true;
	}
	
}

