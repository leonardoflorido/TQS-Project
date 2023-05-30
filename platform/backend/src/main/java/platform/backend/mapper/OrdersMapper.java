package platform.backend.mapper;

import platform.backend.dto.OrdersDTO;
import platform.backend.model.Orders;

public class OrdersMapper {

    public static Orders mapDTOToOrders(OrdersDTO ordersDTO) {
        Orders orders = new Orders();
        orders.setPickupId(ordersDTO.getPickupId());
        orders.setCustomer(ordersDTO.getCustomer());
        orders.seteStore(ordersDTO.geteStore());
        orders.setDate(ordersDTO.getDate());
        orders.setProducts(ordersDTO.getProducts());
        orders.setStatus(ordersDTO.getStatus());
        return orders;
    }

    public static OrdersDTO mapOrdersToDTO(Orders orders) {
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setPickupId(orders.getPickupId());
        ordersDTO.setCustomer(orders.getCustomer());
        ordersDTO.seteStore(orders.geteStore());
        ordersDTO.setDate(orders.getDate());
        ordersDTO.setProducts(orders.getProducts());
        ordersDTO.setStatus(orders.getStatus());
        return ordersDTO;
    }
}
