package cg.wbd.grandemonstration.service;

import cg.wbd.grandemonstration.model.CustomersEntity;
import cg.wbd.grandemonstration.service.exception.DuplicateEmailException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    List<CustomersEntity> findAll();

    Page<CustomersEntity> findAll(Pageable pageInfo);

    List<CustomersEntity> search(String keyword);

    Page<CustomersEntity> search(String keyword, Pageable pageInfo);

    CustomersEntity findOne(Long id);

    CustomersEntity save(CustomersEntity customer) throws DuplicateEmailException;

    List<CustomersEntity> save(List<CustomersEntity> customers);

    boolean exists(Long id);

    List<CustomersEntity> findAll(List<Long> ids);

    long count();

    void delete(Long id);

    void delete(CustomersEntity customer);

    void delete(List<CustomersEntity> customers);

    void deleteAll();
}
