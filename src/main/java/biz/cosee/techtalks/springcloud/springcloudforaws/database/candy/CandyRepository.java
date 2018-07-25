package biz.cosee.techtalks.springcloud.springcloudforaws.database.candy;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CandyRepository extends JpaRepository<Candy, Integer> {
}
