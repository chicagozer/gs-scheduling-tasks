package hello;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class ScheduledTasks {

    Logger logger = Logger.getLogger(TaskExecutorExample.class.getName());
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    @Qualifier("asyncExecutor")
    ThreadPoolTaskExecutor asyncExecutor;

    @Autowired
    Service service;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws InterruptedException, ExecutionException {
        logger.info("The time is now " + dateFormat.format(new Date()));

        //launch a few threads

        /*
        
         logger.info("max:" + taskExecutor.getMaxPoolSize() + " active:"
                + taskExecutor.getActiveCount() + " queue depth:" + taskExecutor.getThreadPoolExecutor().getQueue().size());
        int allowed = taskExecutor.getMaxPoolSize() - taskExecutor.getActiveCount();
       
         CompletionService completionService = new ExecutorCompletionService(taskExecutor);

         for (int i = 0; i < allowed; i++) {
         logger.info("starting thread:" + i);
         TaskExecutorExample task = new TaskExecutorExample();
         task.setName(UUID.randomUUID().toString());
         task.setService(service);
         //taskExecutor.execute(task);
         completionService.submit(task, Boolean.TRUE);
         }

         //taskExecutor.getThreadPoolExecutor().shutdown();
         //taskExecutor.getThreadPoolExecutor().awaitTermination(1, TimeUnit.DAYS);
         for (int i = 0; i < allowed; i++) {
         completionService.take();
         }
         */

        /*
         Collection<Future<Void>> futures = new ArrayList<Future<Void>>();
         for (int i = 0; i < 5; i++) {
         futures.add(service.doSomethingAsync(UUID.randomUUID().toString()));
         }
         for (Future<Void> future : futures) {

         future.get();
         } */
        
        
         logger.info("max:" + asyncExecutor.getMaxPoolSize() + " active:"
                + asyncExecutor.getActiveCount() + " queue depth:" + asyncExecutor.getThreadPoolExecutor().getQueue().size());
       
        
        int allowed = asyncExecutor.getMaxPoolSize() - asyncExecutor.getActiveCount();
        for (int i = 0; i < allowed; i++) {
            service.doSomethingAsync(UUID.randomUUID().toString());
        }
    }
}
