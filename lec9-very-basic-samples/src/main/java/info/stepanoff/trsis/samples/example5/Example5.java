/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
/*
 * пример 5.
 * Демонстрация применения среза - регулярного выражения.
 */
package info.stepanoff.trsis.samples.example5;

import info.stepanoff.trsis.samples.HelloInterface;
import info.stepanoff.trsis.samples.HelloMessage;
import info.stepanoff.trsis.samples.HelloMessage1;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Pavel
 */
public class Example5 {

    public static void main(String[] args) throws Exception {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("web.xml");
        HelloInterface helloBean = applicationContext.getBean("helloBean", HelloMessage.class);
        //HelloInterface helloBean = applicationContext.getBean("helloBean1", HelloMessage1.class);

        JdkRegexpMethodPointcut pc = new JdkRegexpMethodPointcut();
        pc.setPattern(".*get.*|.*set.*");
        pc.setClassFilter(new ClassFilter() {
            public boolean matches(Class<?> type) {
                if (type == HelloMessage.class) {
                    return true;
                }
                return false;
            }
        });

        ProxyFactory pf = new ProxyFactory();

        Advisor advisor = new DefaultPointcutAdvisor(
                pc, new MethodInterceptorExample());

        pf.setTarget(helloBean);
        pf.addAdvisor(advisor);

        HelloInterface proxy = (HelloInterface) pf.getProxy();

        helloBean.setMessage("Changed message1");
        System.out.println("Original invocation:" + helloBean.getMessage());

        try {
            helloBean.throwNPE();
        } catch (Exception e) {

        }

        proxy.setMessage("Changed message2");
        System.out.println("Proxy invocation:" + proxy.getMessage());

        try {
            proxy.throwNPE();
        } catch (Exception e) {

        }
    }
}
