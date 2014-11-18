package hello;

import java.util.concurrent.Executor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableTransactionManagement
@EnableAsync
public class Application {

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(4);
        pool.setMaxPoolSize(4);
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;
    }
    
    @Bean
     public ThreadPoolTaskExecutor asyncExecutor() {
         ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(4);
        pool.setMaxPoolSize(4);
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;
     }

    @Bean
    public PlatformTransactionManager platformTransactionManager() throws Throwable {
        return new MockedTransactionManager();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }
}
