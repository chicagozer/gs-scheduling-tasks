/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;


public class TaskExecutorExample implements Runnable {

    String name;
    Logger logger = Logger.getLogger(TaskExecutorExample.class.getName());

    public void setName(String name) {
        this.name = name;
    }
    
    Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        try {
             Random rand = new Random();
             int sleep = rand.nextInt(10000) + 10000;
            logger.info(name + " is running. will sleep for " + sleep );
            service.doSomething();
           
           
            Thread.sleep(sleep);
            logger.info(name + " is ending.");
        } catch (InterruptedException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
    }

}
