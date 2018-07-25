package biz.cosee.techtalks.springcloud.springcloudforaws.database.candy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Candy {

    @Id
    private int id;
    private String name;
    private int price;
    private int calories;
}
