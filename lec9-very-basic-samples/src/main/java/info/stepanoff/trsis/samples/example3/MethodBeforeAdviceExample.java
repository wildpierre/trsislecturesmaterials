/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples.example3;



import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

/**
 *
 * @author Pavel
 */
public class MethodBeforeAdviceExample implements MethodBeforeAdvice {


    @Override
    public void before(Method method, Object[] parameters, Object target) throws Throwable {
        System.out.println("MethodBefore Advice registered call of method "+ method.getName()+" with "+parameters.length+ " parameters on target "+target.getClass().getName());        
    }
}
