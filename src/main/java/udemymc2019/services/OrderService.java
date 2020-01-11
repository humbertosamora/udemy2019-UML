package udemymc2019.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import udemymc2019.entities.Order;
import udemymc2019.entities.OrderItem;
import udemymc2019.repositories.OrderRepository;
import udemymc2019.services.exceptions.DatabaseException;
import udemymc2019.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public List<Order> findAll() {
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));	// Lambda expression to implement a Supplier object
	}
	
	public Order insert(Order obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Order update(Long id, Order obj) {
		try {
			Order entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Order entity, Order obj) {
		entity.setClient(obj.getClient());
		entity.setDeliveredAddress(obj.getDeliveredAddress());
		entity.setRequestDate(obj.getRequestDate());
		entity.setPayment(obj.getPayment());
		
		Set<OrderItem> items = entity.getItems();
		items.clear();
		
		entity.getItems().forEach(oi -> items.add(oi));
	}
	
}
