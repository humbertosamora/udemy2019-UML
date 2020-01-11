package udemymc2019.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import udemymc2019.entities.enums.ClientType;

@Entity
@Table(name = "tb_client")
public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String email;
	
	private Integer type;
	
	private String cpfOrCnpj;
	
	@OneToMany(mappedBy = "client")
	Set<Address> address = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	Set<Order> orders = new HashSet<>();
	
	@ElementCollection
	@CollectionTable(name="tb_phone_number")
	Set<String> phones = new HashSet<>();
	
	public Client() {
		
	}
	
	public Client(Long id, String name, String email, ClientType type, String cpfOrCnpj) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfOrCnpj = cpfOrCnpj;
		this.type = type.getCode();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public ClientType getType() {
		return ClientType.valueOf(type);
	}
	
	public void setType(ClientType type) {
		this.type = type.getCode();
	}
	
	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}
	
	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}
	
	public Set<Address> getAddress() {
		return address;
	}
	
	public Set<Order> getOrders() {
		return orders;
	}
	
	public Set<String> getPhones() {
		return phones;
	}
	
}
