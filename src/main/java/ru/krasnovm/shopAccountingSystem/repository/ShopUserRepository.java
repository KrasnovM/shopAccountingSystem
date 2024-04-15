package ru.krasnovm.shopAccountingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.krasnovm.shopAccountingSystem.entity.ShopUser;

import java.util.List;
import java.util.Optional;

public interface ShopUserRepository extends JpaRepository<ShopUser, Long> {
    //List<ShopUser> findByName(String name);

    @Query(value = "SELECT * FROM shop_user WHERE name = ?1", nativeQuery = true)
    Optional<ShopUser> findByName(String name);

    @Query(value = "SELECT * FROM shop_user", nativeQuery = true)
    List<ShopUser> findAllUsers();
}
