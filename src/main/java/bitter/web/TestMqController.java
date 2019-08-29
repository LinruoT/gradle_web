package bitter.web;

import bitter.alerts.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/test/mq")
public class TestMqController {
    private AlertService alertService;
    @Autowired
    public TestMqController(AlertService alertService) {
        this.alertService=alertService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String testMq(
            @RequestParam(value = "count",defaultValue = "10") int count) {
        System.out.println("test MQ start, count="+count);
        alertService.sendTestAlert(count);

        return "home";
    }
}
