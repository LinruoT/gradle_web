package bitter.web;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bitter.data.BitterRepository;
import bitter.data.BittleRepository;

import bitter.mybatis.EmpMapper;
import bitter.mybatis.domain.Employee;

import mbg.dao.BitterMapper;
import mbg.dao.BittleMapper;

import mbg.model.BitterExample;
import mbg.model.BittleExample;

/**
 * 测试mybatis
 * 通过mybatis的mapper获取bitterCount、bittleCount
 *
 * @version        Enter version here..., 19/07/28
 * @author         Enter your name here...
 */
@Controller
@RequestMapping({"/test/mybatis"})
public class TestMybatisController {
    private final BitterMapper bitterMapper;
    private final BittleMapper bittleMapper;

    //因为有@MapperScan(value = "mbg.dao")
    @Autowired
    public TestMybatisController(BitterMapper bitterMapper,BittleMapper bittleMapper) {    // 构造器
        this.bitterMapper = bitterMapper;
        this.bittleMapper = bittleMapper;
    }

    /**
     * Method description
     *
     *
     * @param model
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)    // request mapping拆分后，http方法映射部分仍然在方法级别
    public String testMybatis(Model model) {       // 视图名home解析为/WEB-INF/views/home.jsp

        BitterExample example = new BitterExample();
        BitterExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        int bitterCount = bitterMapper.countByExample(example);

        BittleExample example2 = new BittleExample();
        BittleExample.Criteria criteria2 = example2.createCriteria();
        criteria2.andIdIsNotNull();
        int bittleCount = bittleMapper.countByExample(example2);

        model.addAttribute("time", new Date());
        model.addAttribute("bitterCount", "测试mybatis" + bitterCount);
        model.addAttribute("bittleCount", "测试mybatis" + bittleCount);

        return "home";
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
