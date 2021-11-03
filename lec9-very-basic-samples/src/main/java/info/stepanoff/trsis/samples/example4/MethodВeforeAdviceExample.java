/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples.example4;



import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

/**
 *
 * @author Pavel
 */
public class MethodÂeforeAdviceExample implements MethodBeforeAdvice {


    @Override
    public void before(Method method, Object[] parameters, Object target) throws Throwable {
        System.out.println("MethodÂefore Advice registered call of method "+ method.getName()+" with "+parameters.length+ " parameters on target "+target.getClass().getName());        
    }
}
