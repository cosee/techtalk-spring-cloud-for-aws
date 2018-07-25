package biz.cosee.techtalks.springcloud.springcloudforaws.database.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class OrderItem {

    @Id
    private String id;

    private String orderId;
    private int candyId;
    private String candyName;
    private int price;
    private int amount;

    public OrderItem(String orderId, int candyId, String candyName, int price, int amount) {
        this.id = orderId + "_" + candyId;
        this.orderId = orderId;
        this.candyId = candyId;
        this.candyName = candyName;
        this.price = price;
        this.amount = amount;
    }
}
