/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples.example5;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 *
 * @author Pavel
 */
public class MethodInterceptorExample implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("MethodInterceptor Advice: " + invocation.getMethod().getName() + " is called");
        Object retValue = invocation.proceed();
        System.out.println("MethodInterceptor Advice:" + invocation.getMethod().getName() + " returned value " + retValue);
        return retValue;
    }
}
