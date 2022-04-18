package com.tevfikkoseli.app.service;

import com.tevfikkoseli.app.service.data.entity.Order;
import com.tevfikkoseli.app.service.data.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.StreamSupport;

@SpringBootTest
class AppTests {

	private final ApplicationContext m_applicationContext;

	public AppTests(ApplicationContext applicationContext)
	{
		m_applicationContext = applicationContext;
	}

	@Test
	void saveOrder_id_Repository_Test()
	{
		var order = new Order(0, LocalDateTime.now(), 2);
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

		Assert.isTrue(StreamSupport.stream(result.spliterator(), true).count() == 230, "Equals");
	}
}
