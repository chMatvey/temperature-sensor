package ru.chudakov.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.chudakov.SecurityConfig;
import ru.chudakov.domain.Coordinate;
import ru.chudakov.domain.Temperature;
import ru.chudakov.service.CoordinateService;
import ru.chudakov.service.TemperatureService;
import ru.chudakov.web.form.Success;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(SensorController.class)
@Import(SecurityConfig.class)
public class SensorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TemperatureService temperatureService;

    @MockBean
    CoordinateService coordinateService;


    private List<Temperature> createList() {
        return Collections.singletonList(new Temperature(1, new Coordinate(1, 1)));
    }

    private Temperature createTemperature() {
        return new Temperature(1, new Coordinate(1, 1));
    }

    private String mapToJSONasString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Test
    @WithMockUser
    public void getLastInputUser() throws Exception {
        List<Temperature> list = createList();
        given(temperatureService.getLast()).willReturn(list);
        this.mvc.perform(get("/list").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(mapToJSONasString(list)));
    }

    @Test
    public void getLastInputNotUser() throws Exception {
        List<Temperature> list = createList();
        given(temperatureService.getLast()).willReturn(list);
        this.mvc.perform(get("/list").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(302));
    }

    @Test
    @WithMockUser(roles = "SENSOR")
    public void sendDataSensor() throws Exception {
        Temperature temperature = createTemperature();
        given(temperatureService.saveTemperature(temperature)).willReturn(temperature);
        mvc.perform(post("/send").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapToJSONasString(temperature)))
                .andExpect(status().isOk())
                .andExpect(content().string(mapToJSONasString(new Success<>(temperature))));
    }

    @Test
    @WithMockUser
    public void sendDataUser() throws Exception {
        Temperature temperature = createTemperature();
        mvc.perform(post("/send").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapToJSONasString(temperature)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void sendDataNotUserOrSensor() throws Exception {
        Temperature temperature = createTemperature();
        mvc.perform(post("/send").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapToJSONasString(temperature)))
                .andExpect(status().is(302));
    }
}
