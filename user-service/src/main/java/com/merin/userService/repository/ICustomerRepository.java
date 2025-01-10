package com.merin.userService.repository;

import com.merin.userService.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "SELECT EXISTS (SELECT 1 FROM TB_Customer_Details c WHERE c.CUSTOMER_NAME = :username)", nativeQuery = true)
    Long getByUserName(String username);

    @Query(value = "SELECT * FROM TB_Customer_Details WHERE EMAIL_ID LIKE ?1", nativeQuery = true)
    Customer getByEmailIdLike(String emailId);

}
