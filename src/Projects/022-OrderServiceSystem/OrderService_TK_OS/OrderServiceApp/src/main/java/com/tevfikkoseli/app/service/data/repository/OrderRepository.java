package com.tevfikkoseli.app.service.data.repository;

import com.tevfikkoseli.app.service.data.entity.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository implements IOrderRepository {
    private static final String FIND_BY_CLIENT_ID_SQL = "select * from dbo.get_orders_by_client_id(:client_id)";
    private static final String FIND_BY_PRODUCT_ID_SQL = "select * from dbo.get_orders_by_product_id(:product_id)";
    private static final String FIND_BY_DATETIME_BETWEEN_SQL = "select * from dbo.get_orders_by_datetime_between(:begin, :end)";
    private static final String FIND_BY_DATE_SQL = "select * from dbo.get_orders_by_date(:date)";
    private static final String FIND_BY_MONTH_AND_YEAR = "select * from dbo.get_orders_by_month_and_year(:month, :year)";
    private static final String FIND_BY_YEAR_BETWEEN = "select * from dbo.get_orders_by_year_between(:begin, :end)";
    private static final String SAVE_SQL = "exec sp_insert_order ?, ?, ?";
    private static final String SAVE_CLIENT_ID_SQL = "exec sp_insert_order_client_id ?, ?";

    private static void fillOrders(ResultSet rs, List<Order> orders) throws SQLException
    {
        do {
            var id = rs.getLong(1);
            var oDateTime = rs.getTimestamp(2).toLocalDateTime();
            var clientId = rs.getInt(3);

            orders.add(new Order(id, oDateTime, clientId));
        } while (rs.next());
    }

    private final NamedParameterJdbcTemplate m_namedParameterJdbcTemplate;
    private final DateTimeFormatter m_isoLocalDateFormatter;
    private final JdbcTemplate m_jdbcTemplate;

    private CallableStatement saveJdbcTemplateCallCallback(Order order, Connection con) throws SQLException
    {
        var optDateTime = order.getoDateTime();
        var stmt = con.prepareCall(optDateTime.isPresent() ? SAVE_SQL : SAVE_CLIENT_ID_SQL);

        if (optDateTime.isPresent()) {
            stmt.setTimestamp(1, Timestamp.valueOf(order.getoDateTime().get()));
            stmt.setInt(2, order.getClientId());
            stmt.registerOutParameter(3, Types.BIGINT);
        }
        else {
            stmt.setInt(1, order.getClientId());
            stmt.registerOutParameter(2, Types.BIGINT);
        }

        return stmt;
    }

    public OrderRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate,
                           @Qualifier("iso_local_date_formatter") DateTimeFormatter isoLocalDateFormatter)
    {
        m_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        m_isoLocalDateFormatter = isoLocalDateFormatter;
        m_jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Order> findByMonthAndYear(int month, int year)
    {
        var paramMap = new HashMap<String, Object>();
        var orders = new ArrayList<Order>();

        paramMap.put("month", month);
        paramMap.put("year", year);

        m_namedParameterJdbcTemplate.query(FIND_BY_MONTH_AND_YEAR, paramMap, (ResultSet rs) -> fillOrders(rs, orders));

        return orders;
    }

    @Override
    public Iterable<Order> findByYearBetween(int begin, int end)
    {
        var paramMap = new HashMap<String, Object>();
        var orders = new ArrayList<Order>();

        paramMap.put("begin", begin);
        paramMap.put("end", end);

        m_namedParameterJdbcTemplate.query(FIND_BY_YEAR_BETWEEN, paramMap, (ResultSet rs) -> fillOrders(rs, orders));

        return orders;
    }

    @Override
    public Iterable<Order> findByDate(LocalDate date)
    {
        var paramMap = new HashMap<String, Object>();
        var orders = new ArrayList<Order>();

        paramMap.put("date", m_isoLocalDateFormatter.format(date));

        m_namedParameterJdbcTemplate.query(FIND_BY_DATE_SQL, paramMap, (ResultSet rs) -> fillOrders(rs, orders));

        return orders;
    }

    @Override
    public Iterable<Order> findByClientId(int clientId)
    {
        var paramMap = new HashMap<String, Object>();
        var orders = new ArrayList<Order>();

        paramMap.put("client_id", clientId);

        m_namedParameterJdbcTemplate.query(FIND_BY_CLIENT_ID_SQL, paramMap, (ResultSet rs) -> fillOrders(rs, orders));

        return orders;
    }

    @Override
    public Iterable<Order> findByDateTimeBetween(LocalDateTime begin, LocalDateTime end)
    {
        var paramMap = new HashMap<String, Object>();
        var orders = new ArrayList<Order>();

        paramMap.put("begin", begin);
        paramMap.put("end", end);

        m_namedParameterJdbcTemplate.query(FIND_BY_DATETIME_BETWEEN_SQL, paramMap, (ResultSet rs) -> fillOrders(rs, orders));

        return orders;
    }

    @Override
    public Iterable<Order> findByProductId(int productId)
    {
        var paramMap = new HashMap<String, Object>();
        var orders = new ArrayList<Order>();

        paramMap.put("product_id", productId);

        m_namedParameterJdbcTemplate.query(FIND_BY_PRODUCT_ID_SQL, paramMap, (ResultSet rs) -> fillOrders(rs, orders));

        return orders;
    }

    @Override
    public <S extends Order> S save(S order)
    {
        var paramList = new ArrayList<SqlParameter>();

        if (order.getoDateTime().isPresent())
            paramList.add(new SqlParameter(Types.TIMESTAMP));

        paramList.add(new SqlParameter(Types.INTEGER));
        paramList.add(new SqlOutParameter("id", Types.BIGINT));

        var map = m_jdbcTemplate.call(con -> saveJdbcTemplateCallCallback(order, con), paramList);

        order.setId((long)map.get("id"));

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
