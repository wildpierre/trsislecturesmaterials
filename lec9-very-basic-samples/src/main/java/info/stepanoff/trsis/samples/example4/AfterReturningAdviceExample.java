/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.example4;



import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;


/**
 *
 * @author Pavel
 */
public class AfterReturningAdviceExample implements AfterReturningAdvice {


    @Override
    public void afterReturning(Object retValue, Method method, Object[] parameters, Object target) throws Throwable {
        System.out.println("AfterReturning Advice registered return of method "+ method.getName()+" with "+parameters.length+ " parameters on target "+target.getClass().getName()+ " with return value "+retValue);
    }

}
