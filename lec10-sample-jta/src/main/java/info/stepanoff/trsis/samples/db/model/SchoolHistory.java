/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.db.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Pavel
 */
@Data
@Entity
@Table(name = "school_history")
@NoArgsConstructor
public class SchoolHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    public SchoolHistory(Integer id, String userName, Integer schoolId, Integer nomer, String name, String operation) {
        this.schhId = id;
        this.schhUserName = userName;
        this.schhNumber = nomer;
        this.schhName = name;
        this.schhOperation = operation;
    }

    public SchoolHistory(String userName, Integer schoolId, Integer number, String name, String operation) {
        this.schhSchoolId = schoolId;
        this.schhUserName = userName;
        this.schhNumber = number;
        this.schhName = name;
        this.schhOperation = operation;
    }

    public SchoolHistory(Integer number, String name) {
        this.schhNumber = number;
        this.schhName = name;
    }

    @Id
    @Column(name = "school_history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer schhId;

    @Column(name = "school_history_user_name")
    private String schhUserName;

    //@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    //@JoinColumn(name = "school_history_school_id", nullable=true)
    @Column(name = "school_history_school_id")
    private Integer schhSchoolId;

    @Column(name = "school_history_school_number")
    private Integer schhNumber;

    @Column(name = "school_history_school_name")
    private String schhName;

    @Column(name = "school_history_operation")
    private String schhOperation;
}
