package cg.wbd.grandemonstration.service;

import cg.wbd.grandemonstration.model.ProvincesEntity;

import java.util.List;

public interface ProvinceService {
    List<ProvincesEntity> findAll();

    ProvincesEntity findOne(Long id);

    ProvincesEntity save(ProvincesEntity Province);

    List<ProvincesEntity> save(List<ProvincesEntity> Provinces);

    boolean exists(Long id);

    List<ProvincesEntity> findAll(List<Long> ids);

    long count();

    void delete(Long id);

    void delete(ProvincesEntity Province);

    void delete(List<ProvincesEntity> Provinces);

    void deleteAll();
}
