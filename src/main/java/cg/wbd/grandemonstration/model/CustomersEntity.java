package cg.wbd.grandemonstration.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customers", schema = "cms")
public class CustomersEntity {
    private long id;
    private String address;
    private String email;
    private String name;
    private ProvincesEntity provincesByProvinceId;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 255, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        CustomersEntity that = (CustomersEntity) o;
        return id == that.id &&
                Objects.equals(address, that.address) &&
                Objects.equals(email, that.email) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, email, name);
    }

    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "id")
    public ProvincesEntity getProvincesByProvinceId() {
        return provincesByProvinceId;
    }

    public void setProvincesByProvinceId(ProvincesEntity provincesByProvinceId) {
        this.provincesByProvinceId = provincesByProvinceId;
    }
}
