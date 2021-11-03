/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples.config;

import info.stepanoff.trsis.samples.security.SecurityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *
 * @author Pavel
 */
@Configuration
@EnableAspectJAutoProxy
public class AspectJConfiguration {

    @Bean
    public SecurityInterceptor SecurityInterceptor() {
        return new SecurityInterceptor();
    }

}
