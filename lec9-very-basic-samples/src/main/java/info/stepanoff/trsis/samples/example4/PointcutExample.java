/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples.example4;

import info.stepanoff.trsis.samples.HelloMessage;
import java.lang.reflect.Method;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

/**
 *
 * @author Pavel
 */
public class PointcutExample implements Pointcut {

    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            public boolean matches(Class<?> type) {
                if (type == HelloMessage.class) {
                    return true;
                }
                return false;
            }
        };
    }

    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            public boolean matches(Method method, Class<?> type) {
                if (method.getName().startsWith("get")) {
                    return true;
                }
                if (method.getName().startsWith("set")) {
                    return true;
                }
                return false;
            }

            public boolean isRuntime() {
                return false;
            }

            public boolean matches(Method method, Class<?> type, Object... os) {
                return true;
            }
        };
    }

}
