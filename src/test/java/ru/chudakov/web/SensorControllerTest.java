package ru.chudakov.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.chudakov.domain.Coordinate;
import ru.chudakov.domain.Temperature;
import ru.chudakov.service.CoordinateService;
import ru.chudakov.service.TemperatureService;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(SensorController.class)
public class SensorControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CoordinateService coordinateService;

    @MockBean
    private TemperatureService temperatureService;

    @Test
    @WithMockUser(username="user")
    public void getLastInput() throws Exception {
        List<Temperature> list = Collections.singletonList(
                new Temperature(1, new Coordinate(1,1)));
        ObjectMapper mapper = new ObjectMapper();
        given(temperatureService.getLast()).willReturn(list);
        this.mvc.perform(get("/list").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(list))
        );
    }
}
