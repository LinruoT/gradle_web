package bitter.web;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import bitter.alerts.AlertService;
import bitter.data.BitterRepository;
import bitter.data.PictureRepository;
import bitter.domain.Bitter;
import bitter.domain.Bittle;
import bitter.data.BittleRepository;
import bitter.service.BittleService;
import bitter.web.bittle.BittleController;
import bitter.web.bittle.BittleFeedService;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BittleControllerTest {
    @Test
    public void shouldShowRecentBittles() throws Exception {
        List<Bittle> expectedBittles = createBittleList(20);

        BittleRepository bittleRepository = mock(BittleRepository.class);
        BitterRepository bitterRepository = mock(BitterRepository.class);
        PictureRepository pictureRepository =mock(PictureRepository.class);
        BittleService bittleService = mock(BittleService.class);
        AlertService alertService = mock(AlertService.class);
        BittleFeedService bittleFeedService = mock(BittleFeedService.class);

        when(bittleRepository.findBittles(Long.MAX_VALUE,20)).thenReturn(expectedBittles); //expectedbittles代替返回值

        BittleController controller = new BittleController(
                bittleRepository,bitterRepository,pictureRepository,bittleService,alertService,bittleFeedService);

        MockMvc mockMvc = standaloneSetup(controller).setSingleView(new InternalResourceView("/WEB-INF/views/bittles.jsp")).build();//mock mvc build
        mockMvc.perform(get("/bittles")).andExpect(view().name("bittles")) // 页面/bittles http方法GET
                .andExpect(model().attributeExists("bittleList")).andExpect(model() //view为bittles，attribute为bittleList
                .attribute("bittleList", hasItems(expectedBittles.toArray()))); //bittleList属性的值和expectedBittles转数组一样
        for(Bittle bittle:expectedBittles) {
            System.out.println(bittle.getId()+bittle.getMessage()+bittle.getTime());
        }
    }
    private List<Bittle> createBittleList(int count) {
        List<Bittle> bittles = new ArrayList<>();
        for (int i=0;i<count;i++) {
            //Bittle(Long id,Bitter bitter,String message,Date time,Double longitude,Double latitude)
            bittles.add(new Bittle(161,new Bitter(),"一幅名画"));
        }
        return bittles;
    }
}
