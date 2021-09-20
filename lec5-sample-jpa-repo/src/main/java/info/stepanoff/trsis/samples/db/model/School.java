package info.stepanoff.trsis.samples.db.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Pavel
 */
@Entity
@Table(name = "School")

public class School implements Serializable {

    private static final long serialVersionUID = 1L;

    public School() {
    }

    public School(Integer id, Integer nomer, String name) {
        this.id = id;
        this.number = nomer;
        this.name = name;
    }
    public School(Integer nomer, String name) {
        this.number = nomer;
        this.name = name;
    }

    @Id
    @Column(name = "school_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @OneToMany(targetEntity = Batch.class, mappedBy = "school" , fetch = FetchType.LAZY)
    private List<Batch> batchesList;

    public List<Batch> getBatchesList() {
        return batchesList;
    }

    public void setBatchesList(List<Batch> batchesList) {
        this.batchesList = batchesList;
    }

    @Column(name = "school_number")
    private Integer number;

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    @Column(name = "school_name")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
