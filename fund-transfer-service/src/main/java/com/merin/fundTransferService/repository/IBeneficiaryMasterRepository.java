package com.merin.fundTransferService.repository;

import com.merin.fundTransferService.entity.BeneficiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBeneficiaryMasterRepository extends JpaRepository<BeneficiaryEntity, Integer> {

    @Query(value = "SELECT * FROM TB_Beneficiary_Account WHERE CUSTOMER_ID =?1",nativeQuery = true)
    List<BeneficiaryEntity> getCustomerBeneficiaryListById(int customerId);
}
