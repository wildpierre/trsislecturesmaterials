/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples.example4;

import java.lang.reflect.Method;
import org.springframework.aop.ThrowsAdvice;

/**
 *
 * @author Pavel
 */
public class ThrowsAdviceExample implements ThrowsAdvice {

    public void afterThrowing(Exception ex) throws Throwable {
        System.out.println("afterThrowing is called on exception " + ex.toString());
    }

    public void afterThrowing(Method method, Object[] args, Object target,
            NullPointerException ex) throws Throwable {
        System.out.println("afterThrowing is called on exception " + ex.toString() + " for method " + method.getName());
    }
}
