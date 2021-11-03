/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples;

public class HelloMessage1 implements HelloInterface {
    private String message = null;
 
    @Override
    public String getMessage() {
        System.out.println("HelloMessage1: We are inside getMessage");
        return message;
    }
 
    @Override
    public void setMessage(String message) {
        this.message = message;
    }   
     
    @Override
    public void publishMessage() {
        System.out.println(getMessage());
    }
    
    @Override
    public void throwNPE() {
        System.out.println(((String)null).toString());
    }
}