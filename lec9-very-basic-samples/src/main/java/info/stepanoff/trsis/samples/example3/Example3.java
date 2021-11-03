/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
/*
 * пример 3.
 * Демонстрация советов разных типов
 */
package info.stepanoff.trsis.samples.example3;

import info.stepanoff.trsis.samples.HelloInterface;
import info.stepanoff.trsis.samples.HelloMessage;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Pavel
 */
public class Example3 {

    public static void main(String[] args) throws Exception {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("web.xml");
        HelloInterface helloBean = applicationContext.getBean("helloBean", HelloMessage.class);
        ProxyFactory pf = new ProxyFactory();
        
        pf.addAdvice(new MethodInterceptorExample("Interceptor1"));       
        pf.addAdvice(new MethodВeforeAdviceExample());
        pf.addAdvice(new MethodInterceptorExample("Interceptor2"));               
        pf.addAdvice(new AfterReturningAdviceExample());
        pf.addAdvice(new MethodInterceptorExample("Interceptor3"));        
        pf.addAdvice(new ThrowsAdviceExample());

        pf.setTarget(helloBean);
        HelloInterface proxy = (HelloInterface) pf.getProxy();

        System.out.println("Original invocation:" + helloBean.getMessage());
        System.out.println("Proxy invocation:" + proxy.getMessage());

//        System.out.println("Executing throwNPE");
//
//        try {
//            System.out.println("calling proxy.throwNPE()");
//            proxy.throwNPE();
//            System.out.println("should never be executed");
//        } catch (Exception e) {
//            System.out.println("catch clause for " + e.toString());
//        } finally {
//            System.out.println("finally clause ");
//        }

    }

}
