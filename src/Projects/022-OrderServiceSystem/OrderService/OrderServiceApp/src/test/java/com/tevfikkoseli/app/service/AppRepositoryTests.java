package com.tevfikkoseli.app.service;

import com.tevfikkoseli.app.service.data.entity.Order;
import com.tevfikkoseli.app.service.data.repository.OrderProductRepository;
import com.tevfikkoseli.app.service.data.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.stream.StreamSupport;

@SpringBootTest
@ActiveProfiles("unittest")
class AppRepositoryTests {
	private final ApplicationContext m_applicationContext;

	public AppRepositoryTests(ApplicationContext applicationContext)
	{
		m_applicationContext = applicationContext;
	}

	@Test
	void saveOrder_Repository_Test()
	{
		var order = new Order(0, LocalDateTime.of(2022, Month.JANUARY, 10,  23, 12, 34), 3);
		var orderRepo = m_applicationContext.getBean(OrderRepository.class);

		orderRepo.save(order);

		Assert.isTrue(order.getId() != 0, "Equals");
	}

	@Test
	void saveOrderClientId_Repository_Test()
	{
		var order = new Order(0, 3);
		var orderRepo = m_applicationContext.getBean(OrderRepository.class);

		orderRepo.save(order);

		Assert.isTrue(order.getId() != 0, "Equals");
	}

	@Test
	void findOrdersByClientId_Repository_Test() //Belirli id'leri veritabanından okuyup test ediniz
	{
		var clientId = 141;

		var orderRepo = m_applicationContext.getBean(OrderRepository.class);
		var result = orderRepo.findByClientId(clientId);

		Assert.isTrue(StreamSupport.stream(result.spliterator(), true).findAny().isPresent(), "Equals");
	}

	@Test
	void findOrdersByClientId_count_Repository_Test() //Belirli id'leri veritabanından okuyup test ediniz
	{
		var clientId = 141;

		var orderRepo = m_applicationContext.getBean(OrderRepository.class);
		var result = orderRepo.findByClientId(clientId);

		Assert.isTrue(StreamSupport.stream(result.spliterator(), true).count() == 2, "Equals");
	}

	@Test
	void findOrdersByDateTimeBetween_Repository_Test() //Belirli id'leri veritabanından okuyup test ediniz
	{
		var begin = LocalDate.of(2021, 6, 1).atTime(LocalTime.of(0, 0));
		var end = LocalDate.of(2021, 9, 1).atTime(LocalTime.of(0, 0));
		var count = 230;

		var orderRepo = m_applicationContext.getBean(OrderRepository.class);
		var result = orderRepo.findByDateTimeBetween(begin, end);

		Assert.isTrue(StreamSupport.stream(result.spliterator(), true).count() == count, "Equals");
	}

	@Test
	void findOrdersByDate_RepositoryTest()
	{
		var date = LocalDate.of(2022, Month.MARCH, 10);
		var count = 3;

		var orderRepo = m_applicationContext.getBean(OrderRepository.class);
		var result = orderRepo.findByDate(date);

		Assert.isTrue(StreamSupport.stream(result.spliterator(), false).count() == count, "Equals");
	}

	@Test
	void findOrderProductsByOrderIdTest_RepositoryTest() //Belirli id'leri veritabanından okuyup test ediniz
	{
		var orderId = 2;
		var count = 2;

		var orderProductRepo = m_applicationContext.getBean(OrderProductRepository.class);
		var result = orderProductRepo.findByOrderId(orderId);

		Assert.isTrue(StreamSupport.stream(result.spliterator(), false).count() == count, "Equals");
	}

	@Test
	void findOrdersByProductId_RepositoryTest()
	{
		var productId = 900;
		var count = 2;

		var orderRepo = m_applicationContext.getBean(OrderRepository.class);
		var result = orderRepo.findByProductId(productId);

		Assert.isTrue(StreamSupport.stream(result.spliterator(), false).count() == count, "Equals");
	}
}
