/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

/*
 * пример 1.
 * ƒемонстраци€ применени€ простого совета.
 */
package info.stepanoff.trsis.samples.example1;

import info.stepanoff.trsis.samples.HelloMessage;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Pavel
 */
public class Example1 {
    
    
   

    public static void main(String[] args) throws Exception {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("web.xml");

        HelloMessage helloBean = applicationContext.getBean("helloBean", HelloMessage.class);
                
        ProxyFactory pf = new ProxyFactory ();
        pf.addAdvice(new MethodInterceptorExample());
        pf.setTarget(helloBean);
        HelloMessage proxy = (HelloMessage) pf.getProxy();
        
        System.out.println("Original invocation");
        helloBean.publishMessage();
        System.out.println("Proxy invocation");
        proxy.publishMessage();
    }

    
}
