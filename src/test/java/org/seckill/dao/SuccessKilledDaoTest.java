package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() {

        long id = 1000L;
        long phone = 1835515153L;
        int insertCount = successKilledDao.insertSuccessKilled(id, phone);
        System.out.println("insertCount"+insertCount);

    }

    /**
     * SuccessKilled{seckillId=1000,
     * userPhone=1835515153,
     * state=-1,
     * createTime=Sun Aug 02 21:42:51 CST 2020,
     * seckill=Seckill{seckillId=1000,
     * name='1000元秒杀iphone6',
     * number=100,
     * startTime=Sun Nov 01 00:00:00 CST 2015,
     * endTime=Mon Nov 02 00:00:00 CST 2015,
     * createTime=Sun Aug 02 17:36:32 CST 2020}}
     * Seckill{seckillId=1000,
     * name='1000元秒杀iphone6',
     * number=100,
     * startTime=Sun Nov 01 00:00:00 CST 2015,
     * endTime=Mon Nov 02 00:00:00 CST 2015,
     * createTime=Sun Aug 02 17:36:32 CST 2020}
     */
    @Test
    public void queryByIdWithSeckill() {
        long id = 1000L;
        long phone = 1835515153L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id, phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}