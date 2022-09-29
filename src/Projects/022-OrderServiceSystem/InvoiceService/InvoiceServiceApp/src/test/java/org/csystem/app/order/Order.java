package org.csystem.app.order;

import java.time.LocalDate;

//Top-Level Class
public class Order{
    private long m_orderId;
    private LocalDate m_orderDate;
    private int m_clientId;
    //...

    //Nested class.(Static iç içe class) iç içe sınıfları en büyük özelliği top-level class ile birbirlerinin
    //private elemanlarına erişebilmeleridir.
    //Outsource işlemlerde class ın tamamını dışarıya vermek istemediğimiz durumlarda NestedClass kullanılabilir.
    //Bir türün çok fazla veri elemanı varsa ve veri elemanlarının set edilmesi opsiyonelse çok fazla constructor
    //yazmak gerekir bundan dolayı Fluent-Builder pattern kullanabiliriz.
    public static final class Builder{
        private final Order m_order;
        //...

        public Builder() { m_order = new Order(); }

        public Order.Builder setOrderId(long orderId) { m_order.m_orderId = orderId; return this; }
        public Order.Builder setOrderDate(LocalDate orderDate) { m_order.m_orderDate = orderDate; return this; }
        public Order.Builder setClientId(int clientId) { m_order.m_clientId = clientId; return this; }

        public Order build() { return m_order; }
    }

    private Order() {}

    public Order(long orderId, LocalDate orderDate, int clientId)
    {
        m_orderId = orderId;
        m_orderDate = orderDate;
        m_clientId = clientId;
    }

    public static Order.Builder builder()
    {
        return new Order.Builder();
    }

    public long getOrderId() { return m_orderId; }
    public void setOrderId(long orderId) { m_orderId = orderId; }
    public LocalDate getOrderDate() { return m_orderDate; }
    public void setOrderDate(LocalDate orderDate) { m_orderDate = orderDate; }
    public int getClientId() { return m_clientId; }
    public void setClientId(int clientId) { m_clientId = clientId; }
}
