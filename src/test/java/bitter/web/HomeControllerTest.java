package bitter.web;

import bitter.data.BitterRepository;
import bitter.data.BittleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
public class HomeControllerTest {
    @Test
    public void testHomePage() throws Exception {
        BitterRepository bitterRepository = mock(BitterRepository.class);
        BittleRepository bittleRepository = mock(BittleRepository.class);
        HomeController controller = new HomeController(bitterRepository,bittleRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/")).andExpect(view().name("home"));
        mockMvc.perform(get("/homepage")).andExpect(view().name("home"));
    }
}