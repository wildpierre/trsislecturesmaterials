/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) throws Exception {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("web.xml");

        HelloMessage helloBeanInst = applicationContext.getBean("helloBean", HelloMessage.class);

        helloBeanInst.publishMessage();
        
        HelloMessage1 helloBeanInst1 = applicationContext.getBean("helloBean1", HelloMessage1.class);

        helloBeanInst1.publishMessage();
    }

}
