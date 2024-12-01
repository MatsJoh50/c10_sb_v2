package lernia.c10_springboot_v2.location.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import java.time.Instant;

@Entity
@Table(name = "locations", schema = "mydatabase")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull(message = "Name must not be null")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "kategori", nullable = false)
    private Integer kategori;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "is_private")
    private Boolean isPrivate = true;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    @Column(name = "deleted_by")
    private String deletedBy;

    @Column(name = "date_of_creation")
    private Instant dateOfCreation = Instant.now();

    @Column(name = "date_of_change")
    private Instant dateOfChange = Instant.now();

    @Column(name = "coordinates", nullable = false)
    private Point<G2D> coordinates;

    public Point<G2D> getCoordinates() {
        return this.coordinates;
    }
    public void setCoordinates(Point<G2D> coordinate) {
        this.coordinates = coordinate;
    }

    public Instant getDateOfChange() {
        return dateOfChange;
    }

    public void setDateOfChange(Instant dateOfChange) {
        this.dateOfChange = dateOfChange;
    }

    public Instant getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Instant dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKategori() {
        return kategori;
    }

    public void setKategori(Integer kategori) {
        this.kategori = kategori;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean is_private) {
        this.isPrivate = is_private;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}