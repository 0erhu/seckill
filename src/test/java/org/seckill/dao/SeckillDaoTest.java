package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * 配置spring和junit整合,junit启动时加载spingIOC
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {

    //注入Dao
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void reduceNumber() {
        Date killtime = new Date();
        int updateCount = seckillDao.reduceNumber(1000L, killtime);
        System.out.println("updateCount"+updateCount);
    }

    @Test
    public void queryByIdqueryById() {
        long id= 1000;
        Seckill seckill = seckillDao.queryByIdqueryById(id);
        System.out.println(seckill.getName());
    }

    @Test
    public void queyAll() {
        List<Seckill> seckills = seckillDao.queyAll(0, 10);
        for(Seckill seckill : seckills){
            System.out.println(seckill);
        }
    }
}