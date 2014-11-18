/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.util.logging.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 *
 * @author jim
 */
@Component
public class InstantiationTracingBeanPostProcessor  implements BeanPostProcessor {

    Logger logger = Logger.getLogger(TaskExecutorExample.class.getName());
    
   @Override
   
   public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
      return bean;
   }

   @Override
   public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
      logger.info("Bean '" + beanName + "' created : " + bean.toString());
      return bean;
   }
}
