package cg.wbd.grandemonstration.service.impl;

import cg.wbd.grandemonstration.model.CustomersEntity;
import cg.wbd.grandemonstration.repository.CustomerRepository;
import cg.wbd.grandemonstration.service.CustomerService;
import cg.wbd.grandemonstration.service.exception.DuplicateEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CustomerServiceImplWithSpringData implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomersEntity> findAll() {
        return streamAll().collect(Collectors.toList());
    }

    @Override
    public Page<CustomersEntity> findAll(Pageable pageInfo) {
        return customerRepository.findAll(pageInfo);
    }

    @Override
    public List<CustomersEntity> search(String keyword) {
        Iterable<CustomersEntity> searchResult = customerRepository
                .findAllByNameContainsOrEmailContainsOrAddressContains(keyword, keyword, keyword);
        return streamAll(searchResult).collect(Collectors.toList());
    }

    @Override
    public Page<CustomersEntity> search(String keyword, Pageable pageInfo) {
        return customerRepository
                .findAllByNameContainsOrEmailContainsOrAddressContains(keyword, keyword, keyword, pageInfo);
    }

    @Override
    public CustomersEntity findOne(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public CustomersEntity save(CustomersEntity customer) throws DuplicateEmailException {
        try {
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateEmailException();
        }
    }

    @Override
    public List<CustomersEntity> save(List<CustomersEntity> customers) {
        Iterable<CustomersEntity> updatedCustomerEntity = customerRepository.save(customers);
        return streamAll(updatedCustomerEntity).collect(Collectors.toList());
    }

    @Override
    public boolean exists(Long id) {
        return customerRepository.exists(id);
    }

    @Override
    public List<CustomersEntity> findAll(List<Long> ids) {
        Iterable<CustomersEntity> customers = customerRepository.findAll(ids);
        return streamAll(customers).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return customerRepository.count();
    }

    @Override
    public void delete(Long id) {
        customerRepository.delete(id);
    }

    @Override
    public void delete(CustomersEntity customer) {
        customerRepository.delete(customer);
    }

    @Override
    public void delete(List<CustomersEntity> customers) {
        customerRepository.delete(customers);
    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();
    }

    private Stream<CustomersEntity> streamAll() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false);
    }

    private Stream<CustomersEntity> streamAll(Iterable<CustomersEntity> customers) {
        return StreamSupport.stream(customers.spliterator(), false);
    }
}
