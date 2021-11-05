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
@NoArgsConstructor
@Table(name = "batch")
public class Batch implements Serializable {

    private static final long serialVersionUID = 1L;

    public Batch(Integer id, String nomer) {
        this.id = id;
        this.number = nomer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batch_id")
    private Integer id;

    @Column(name = "batch_number")
    private String number;

    @ManyToOne(fetch=FetchType.LAZY)    
    @JoinColumn(name = "batch_school_id", nullable=false)    
    private School school;
    
}
