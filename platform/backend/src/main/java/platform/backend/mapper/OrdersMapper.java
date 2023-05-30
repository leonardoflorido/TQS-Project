package platform.backend.mapper;

import platform.backend.model.Orders;
import platform.backend.pojo.OrdersPOJO;

public class OrdersMapper {
    public static Orders mapPOJOToOrders(OrdersPOJO ordersPOJO) {
        Orders orders = new Orders();
        orders.setPickupId(ordersPOJO.getPickupId());
        orders.setCustomer(ordersPOJO.getCustomer());
        orders.seteStore(ordersPOJO.geteStore());
        orders.setDate(ordersPOJO.getDate());
        orders.setProducts(ordersPOJO.getProducts());
        orders.setStatus(ordersPOJO.getStatus());
        return orders;
    }
}
