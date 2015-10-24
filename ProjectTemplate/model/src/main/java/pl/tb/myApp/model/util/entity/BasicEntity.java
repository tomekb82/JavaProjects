
package pl.tb.myApp.model.util.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@MappedSuperclass
public abstract class BasicEntity implements Serializable, Cloneable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_GENERATOR")
    private Long id;

    @Column(name = "CREATED_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDate;

    @Column(name = "MODIFIED_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifiedDate;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Long version;

    @Column(name = "USER", nullable = false)
    private String user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

    public Calendar getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Calendar modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @PrePersist
    public void initCreatedDate() {
        setCreatedDate(Calendar.getInstance());
        setModifiedDate(Calendar.getInstance());
    }

    @PreUpdate
    public void updateModifiedDate() {
        setModifiedDate(Calendar.getInstance());
    }

}
