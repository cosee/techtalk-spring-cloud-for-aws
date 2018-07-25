package biz.cosee.techtalks.springcloud.springcloudforaws.database.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
}
