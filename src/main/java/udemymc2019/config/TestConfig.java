package udemymc2019.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import udemymc2019.entities.Address;
import udemymc2019.entities.Category;
import udemymc2019.entities.City;
import udemymc2019.entities.Client;
import udemymc2019.entities.Order;
import udemymc2019.entities.OrderItem;
import udemymc2019.entities.Payment;
import udemymc2019.entities.PaymentCreditCard;
import udemymc2019.entities.PaymentSlip;
import udemymc2019.entities.Product;
import udemymc2019.entities.State;
import udemymc2019.entities.enums.ClientType;
import udemymc2019.entities.enums.PaymentStatus;
import udemymc2019.repositories.AddressRepository;
import udemymc2019.repositories.CategoryRepository;
import udemymc2019.repositories.CityRepository;
import udemymc2019.repositories.ClientRepository;
import udemymc2019.repositories.OrderItemRepository;
import udemymc2019.repositories.OrderRepository;
import udemymc2019.repositories.PaymentRepository;
import udemymc2019.repositories.ProductRepository;
import udemymc2019.repositories.StateRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "COMPUTER");
		Category cat2 = new Category(null, "OFFICE");
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		
		Product p1 = new Product(null, "LAPTOP", 500.00);
		Product p2 = new Product(null, "PRINTER", 200.00);
		Product p3 = new Product(null, "MOUSE", 20.00);
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		p1.getCategories().add(cat1);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat2);
		p3.getCategories().add(cat1);
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		State st1 = new State(null, "MINAS GERAIS");
		State st2 = new State(null, "SÃO PAULO");
		stateRepository.saveAll(Arrays.asList(st1, st2));
		
		City ct1 = new City(null, "UBERLÂNDIA", st1);
		City ct2 = new City(null, "SÃO PAULO", st2);
		City ct3 = new City(null, "CAMPINAS", st2);
		cityRepository.saveAll(Arrays.asList(ct1, ct2, ct3));
		
		st1.getCities().add(ct1);
		st2.getCities().add(ct2);
		st2.getCities().add(ct3);
		stateRepository.saveAll(Arrays.asList(st1, st2));
		
		Client cli1 = new Client(null, "MARIA SILVA", "MARIA@GMAIL.COM", ClientType.NATURAL_PERSON, "36378912377");
		clientRepository.saveAll(Arrays.asList(cli1));	// Save independent objects first
		
		Address a1 = new Address(null,"FLORES ST.", "300", "AP 203", "JARDIM", "38220834", cli1, ct1);
		Address a2 = new Address(null,"MATOS AV.", "105", "SUIT 800", "DOWNTOWN", "38777012", cli1, ct2);
		addressRepository.saveAll(Arrays.asList(a1, a2));
		
		cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));
		cli1.getAddress().addAll(Arrays.asList(a1, a2));		
		clientRepository.save(cli1);
		
		Order o1 = new Order(null, Instant.parse("2017-09-30T10:32:00Z"), cli1, a1);
		Order o2 = new Order(null, Instant.parse("2017-10-10T19:35:00Z"), cli1, a2);
		
		/* To save a dependent object, it should be added to the main (independent) object and
		 * then save this object, which will persist all dependent objects. See CascadeType.ALL
		 * in Payment object declaration in class Order.
		 * */
		Payment pay1 = new PaymentCreditCard(null, PaymentStatus.CONFIRMED, o1,
											Instant.parse("2017-09-30T10:50:00Z"),
											6, "4BC477389F");
		o1.setPayment(pay1);
		
		Payment pay2 = new PaymentSlip( null, PaymentStatus.PENDING , o2,
										null,
										Instant.parse("2017-10-20T23:59:59Z"),
										"8451 4781 1524 9783");
		o2.setPayment(pay2);
		
		cli1.getOrders().addAll(Arrays.asList(o1, o2));
		
		orderRepository.saveAll(Arrays.asList(o1, o2));	// Save order first because it is a independent class
		
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		
		OrderItem oi1 = new OrderItem(o1, p1,  0.0, 1, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3,  0.0, 2, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p2, 25.0, 1, p2.getPrice());
				
		p1.getOrderItems().add(oi1);
		p2.getOrderItems().add(oi3);
		p3.getOrderItems().add(oi2);
		
		o1.getItems().addAll(Arrays.asList(oi1, oi2));
		o2.getItems().add(oi3);
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));
		
	}
	
}

