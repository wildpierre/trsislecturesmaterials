
package info.stepanoff.trsis.samples.db.model;

/**
 *
 * @author Pavel
 */
public class Batch {

    public Batch() {
    }    
    
    public Batch(Integer id, String nomer) {
        this.id = id;
        this.number = nomer;

    }

    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    private String number;

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }


}
