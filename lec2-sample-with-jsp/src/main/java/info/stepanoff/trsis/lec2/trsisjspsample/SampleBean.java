/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.lec2.trsisjspsample;

/**
 *
 * @author wildhost5
 */
public class SampleBean {
    
    String message = "Developer says \"hello\" to you";
    
    public String getMessage(){
        return message;
    };
    
    public void setMessage(String message){
        this.message= message;
    }
}
