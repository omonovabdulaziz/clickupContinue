package uz.pdp.appclickup.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appclickup.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
