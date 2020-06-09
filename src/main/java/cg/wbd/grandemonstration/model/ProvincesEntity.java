package cg.wbd.grandemonstration.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "provinces", schema = "cms")
public class ProvincesEntity {
    private long id;
    private String name;
    private Collection<CustomersEntity> customersById;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProvincesEntity that = (ProvincesEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "provincesByProvinceId")
    public Collection<CustomersEntity> getCustomersById() {
        return customersById;
    }

    public void setCustomersById(Collection<CustomersEntity> customersById) {
        this.customersById = customersById;
    }
}
