/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.db.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Pavel
 */
@Data
@NoArgsConstructor
public class SchoolDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private List<Integer> batchesIdsList;
    private Integer number;
    private String name;
}
