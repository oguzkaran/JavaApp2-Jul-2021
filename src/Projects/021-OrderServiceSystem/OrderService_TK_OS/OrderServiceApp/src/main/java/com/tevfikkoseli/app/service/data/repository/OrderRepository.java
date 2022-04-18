package com.tevfikkoseli.app.service.data.repository;

import com.tevfikkoseli.app.service.data.entity.Order;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository implements IOrderRepository {
    private static final String FIND_ALL_SQL = "select * from orders";
    private static final String FIND_BY_CLIENT_ID_SQL = "select * from orders where client_id = :client_id";
    private static final String FIND_BY_DATETIME_BETWEEN_SQL = "select * from orders where odatetime between :begin and :end";
    private static final String SAVE_SQL = "insert into orders (odatetime, client_id) values (:oDateTime, :clientId)";

    private static void fillOrders(ResultSet rs, List<Order> orders) throws SQLException
    {
        do {
            var id = rs.getLong(1);
            var oDateTime = rs.getTimestamp(2).toLocalDateTime();
            var clientId = rs.getInt(3);

            orders.add(new Order(id, oDateTime, clientId));
        } while (rs.next());
    }

    private final NamedParameterJdbcTemplate m_jdbcTemplate;

    public OrderRepository(NamedParameterJdbcTemplate jdbcTemplate)
    {
        m_jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Order> findByMonthAndYear(int month, int year) //TODO
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Order> findByYearBetween(int begin, int end) //TODO
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Order> findByClientId(int clientId)
    {
        var paramMap = new HashMap<String, Object>();
        var orders = new ArrayList<Order>();

        paramMap.put("client_id", clientId);

        m_jdbcTemplate.query(FIND_BY_CLIENT_ID_SQL, paramMap, (ResultSet rs) -> fillOrders(rs, orders));

        return orders;
    }

    @Override
    public Iterable<Order> findByDateTimeBetween(LocalDateTime begin, LocalDateTime end)
    {
        var paramMap = new HashMap<String, Object>();
        var orders = new ArrayList<Order>();

        paramMap.put("begin", begin);
        paramMap.put("end", end);

        m_jdbcTemplate.query(FIND_BY_DATETIME_BETWEEN_SQL, paramMap, (ResultSet rs) -> fillOrders(rs, orders));

        return orders;
    }

    @Override
    public <S extends Order> S save(S order)
    {
        var keyHolder = new GeneratedKeyHolder();
        var parameterSource = new BeanPropertySqlParameterSource(order);

        parameterSource.registerSqlType("oDateTime", Types.TIMESTAMP);

        m_jdbcTemplate.update(SAVE_SQL, parameterSource, keyHolder, new String [] {"order_id"});

        order.setId(keyHolder.getKey().longValue());

        return order;
    }


    /////////////////////////////////////////////////////////////////
    @Override
    public long count()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Order entity)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends Order> entities)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Long aLong)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(Long aLong)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Order> findAll()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Order> findAllById(Iterable<Long> longs)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Order> findById(Long aLong)
    {
        throw new UnsupportedOperationException();
    }



    @Override
    public <S extends Order> Iterable<S> save(Iterable<S> entities)
    {
        throw new UnsupportedOperationException();
    }
}
