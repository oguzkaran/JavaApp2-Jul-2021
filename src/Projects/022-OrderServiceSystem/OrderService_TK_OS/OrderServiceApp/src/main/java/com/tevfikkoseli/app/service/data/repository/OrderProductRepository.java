package com.tevfikkoseli.app.service.data.repository;

import com.tevfikkoseli.app.service.data.entity.Order;
import com.tevfikkoseli.app.service.data.entity.OrderProduct;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderProductRepository implements IOrderProductRepository {
    private static final String FIND_BY_ORDER_ID_SQL = "select * from dbo.get_order_products_by_order_id(:order_id)";
    private static final String FIND_BY_PRODUCT_ID_SQL = "select * from dbo.get_order_products_by_product_id(:product_id)";
    private static final String SAVE_SQL = "exec sp_insert_order_product ?, ?, ?, ?";
    private final NamedParameterJdbcTemplate m_jdbcTemplate;

    private static void fillOrderProducts(ResultSet rs, List<OrderProduct> orders) throws SQLException
    {
        do {
            var id = rs.getLong(1);
            var orderId = rs.getLong(2);
            var productId = rs.getInt(3);
            var quantity = rs.getDouble(4);
            var unitPrice = rs.getDouble(5);

            orders.add(new OrderProduct(id, orderId, productId, quantity, unitPrice));
        } while (rs.next());
    }

    public OrderProductRepository(NamedParameterJdbcTemplate jdbcTemplate)
    {
        m_jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<OrderProduct> findByOrderId(long orderId)
    {
        var paramMap = new HashMap<String, Object>();
        var orderProducts = new ArrayList<OrderProduct>();

        paramMap.put("order_id", orderId);

        m_jdbcTemplate.query(FIND_BY_ORDER_ID_SQL, paramMap, (ResultSet rs) -> fillOrderProducts(rs, orderProducts));

        return orderProducts;
    }

    @Override
    public Iterable<OrderProduct> findByProductId(int productId)
    {
        var paramMap = new HashMap<String, Object>();
        var orderProducts = new ArrayList<OrderProduct>();

        paramMap.put("product_id", productId);

        m_jdbcTemplate.query(FIND_BY_PRODUCT_ID_SQL, paramMap, (ResultSet rs) -> fillOrderProducts(rs, orderProducts));

        return orderProducts;
    }

    @Override
    public <S extends OrderProduct> S save(S entity)
    {
        throw new UnsupportedOperationException();
    }

    //////////////////////////////////////////////////////////////////////////////////////
    @Override
    public long count()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(OrderProduct entity)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends OrderProduct> entities)
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
    public Iterable<OrderProduct> findAll()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<OrderProduct> findAllById(Iterable<Long> longs)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<OrderProduct> findById(Long aLong)
    {
        throw new UnsupportedOperationException();
    }



    @Override
    public <S extends OrderProduct> Iterable<S> save(Iterable<S> entities)
    {
        throw new UnsupportedOperationException();
    }
}
