
package info.stepanoff.trsis.samples.db.model;

/**
 *
 * @author Pavel
 */
public class School {

    public School() {
    }    
    
    public School(Integer id, Integer nomer, String name) {
        this.id = id;
        this.number = nomer;
        this.name = name;
    }

    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    private Integer number;

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
