/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples;

/**
 *
 * @author Pavel
 */
public interface HelloInterface {

    String getMessage();

    void publishMessage();

    void setMessage(String message);
    
    void throwNPE();
    
}
