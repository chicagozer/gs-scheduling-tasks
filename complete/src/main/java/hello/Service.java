/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 *
 * @author jim
 */
@Component
public class Service {

    @Autowired
    SecondService secondService;

    Logger logger = Logger.getLogger(Service.class.getName());

    @Transactional
    public void doSomething() {
        logger.info("Doing something tx:" + TransactionSynchronizationManager.isActualTransactionActive());

    }

    @Transactional
    @Async("asyncExecutor")
    public void doSomethingAsync(String name) {
        try {
            logger.info("["
                    + TransactionSynchronizationManager.getCurrentTransactionName()
                    + "] tx:" + TransactionSynchronizationManager.isActualTransactionActive());
            Random rand = new Random();
            int sleep = rand.nextInt(10000) + 10000;
            logger.info(name + " is running. will sleep for " + sleep);

            Thread.sleep(sleep);
            secondService.doSomething();
            logger.info(name + " is ending.");

        } catch (InterruptedException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doSomethingElse() {
        logger.info("["
                + TransactionSynchronizationManager.getCurrentTransactionName()
                + "] tx:" + TransactionSynchronizationManager.isActualTransactionActive());
    }

}
