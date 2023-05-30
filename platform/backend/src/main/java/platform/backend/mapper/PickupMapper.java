package platform.backend.mapper;

import platform.backend.model.Pickup;
import platform.backend.pojo.PickupPOJO;

public class PickupMapper {

    public static Pickup mapPOJOToPickup(PickupPOJO pickupPOJO) {
        Pickup pickup = new Pickup();
        pickup.setName(pickupPOJO.getName());
        pickup.setEmail(pickupPOJO.getEmail());
        pickup.setPhone(pickupPOJO.getPhone());
        pickup.setAddress(pickupPOJO.getAddress());
        pickup.setPassword(pickupPOJO.getPassword());
        pickup.setStatus(pickupPOJO.getStatus());
        return pickup;
    }
}
