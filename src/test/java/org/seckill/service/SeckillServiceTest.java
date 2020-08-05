package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> seckillList = seckillService.getSeckillList();
        logger.info("list={}",seckillList);
    }

    @Test
    public void getById() {
        Seckill seckill = seckillService.getById(1000L);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void exportSeckillLogic() {
        long id = 1000L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if(exposer.isExposed()){
            logger.info("exposer={}",exposer);
            long phone = 18392998649L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
            }catch (RepeatKillException e){
                logger.error(e.getMessage(),e);
            }catch (SeckillCloseException e){
                logger.error(e.getMessage(),e);
            }
        }else{
            logger.warn("exposer={}",exposer);
        }

        /**
         * Exposer{exposed=true, md5='4d188102fe2f4bb8714f50fdbad5d5a0', seckillId=1000, now=0, start=0, end=0}
         */
    }

    @Test
    public void executeSeckill() {
        long id = 1000L;
        long phone = 18392998649L;
        String md5 = "4d188102fe2f4bb8714f50fdbad5d5a0";
        try {
            SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
        }catch (RepeatKillException e){
            logger.error(e.getMessage(),e);
        }catch (SeckillCloseException e){
            logger.error(e.getMessage(),e);
        }


    }

    @Test
    public void executeSeckillByProceduce() {

        long id = 1000L;
        long phone = 18392998649L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if(exposer.isExposed()){
            String md5 = exposer.getMd5();
            SeckillExecution execution = seckillService.executeSeckillByProceduce(id, phone, md5);
            logger.info(execution.getStateInfo());
        }

    }
}