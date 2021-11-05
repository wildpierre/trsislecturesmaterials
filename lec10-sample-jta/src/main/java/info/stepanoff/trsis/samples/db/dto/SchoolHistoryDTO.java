/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.db.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Pavel
 */
@Data
@NoArgsConstructor
public class SchoolHistoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer schhId;
    private String schhUserName;
    private Integer schhSchoolId;
    private Integer schhNumber;
    private String schhName;
    private String schhOperation;
}
