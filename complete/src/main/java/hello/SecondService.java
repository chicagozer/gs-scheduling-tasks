/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 *
 * @author jim
 */
@Component
public class SecondService {

    Logger logger = Logger.getLogger(SecondService.class.getName());

     
   @Transactional(propagation=Propagation.REQUIRES_NEW)
   public void doSomething()
   {
       logger.info("["  +
               TransactionSynchronizationManager.getCurrentTransactionName() + 
               "] tx:" + TransactionSynchronizationManager.isActualTransactionActive());
   }

}
