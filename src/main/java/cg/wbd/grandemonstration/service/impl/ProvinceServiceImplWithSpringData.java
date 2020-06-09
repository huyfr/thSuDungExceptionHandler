package cg.wbd.grandemonstration.service.impl;

import cg.wbd.grandemonstration.model.ProvincesEntity;
import cg.wbd.grandemonstration.repository.ProvinceRepository;
import cg.wbd.grandemonstration.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ProvinceServiceImplWithSpringData implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<ProvincesEntity> findAll() {
        return streamAll().collect(Collectors.toList());
    }

    @Override
    public ProvincesEntity findOne(Long id) {
        return provinceRepository.findOne(id);
    }

    @Override
    public ProvincesEntity save(ProvincesEntity Province) {
        return provinceRepository.save(Province);
    }

    @Override
    public List<ProvincesEntity> save(List<ProvincesEntity> Provinces) {
        Iterable<ProvincesEntity> updatedProvinces = provinceRepository.save(Provinces);
        return streamAll(updatedProvinces).collect(Collectors.toList());
    }

    @Override
    public boolean exists(Long id) {
        return provinceRepository.exists(id);
    }

    @Override
    public List<ProvincesEntity> findAll(List<Long> ids) {
        Iterable<ProvincesEntity> Provinces = provinceRepository.findAll(ids);
        return streamAll(Provinces).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return provinceRepository.count();
    }

    @Override
    public void delete(Long id) {
        provinceRepository.delete(id);
    }

    @Override
    public void delete(ProvincesEntity Province) {
        provinceRepository.delete(Province);
    }

    @Override
    public void delete(List<ProvincesEntity> Provinces) {
        provinceRepository.delete(Provinces);
    }

    @Override
    public void deleteAll() {
        provinceRepository.deleteAll();
    }

    private Stream<ProvincesEntity> streamAll() {
        return StreamSupport.stream(provinceRepository.findAll().spliterator(), false);
    }

    private Stream<ProvincesEntity> streamAll(Iterable<ProvincesEntity> Provinces) {
        return StreamSupport.stream(Provinces.spliterator(), false);
    }
}
