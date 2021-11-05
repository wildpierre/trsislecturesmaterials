/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
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
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Pavel
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "school")

public class School implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @OneToMany(targetEntity = Batch.class, mappedBy = "school" , fetch = FetchType.LAZY)
    private List<Batch> batchesList;

//    @OneToMany(targetEntity = SchoolHistory.class, mappedBy = "schhSchool" , fetch = FetchType.LAZY)
//    private List<SchoolHistory> historiesList;    
    
    @Column(name = "school_number")
    private Integer number;

    @Column(name = "school_name")
    private String name;
}
