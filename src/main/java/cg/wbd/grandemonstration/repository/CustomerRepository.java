package cg.wbd.grandemonstration.repository;

import cg.wbd.grandemonstration.model.CustomersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<CustomersEntity, Long> {
    Iterable<CustomersEntity> findAllByNameContainsOrEmailContainsOrAddressContains(String name, String email, String address);

    Page<CustomersEntity> findAllByNameContainsOrEmailContainsOrAddressContains(String name, String email, String address, Pageable pageInfo);
}
