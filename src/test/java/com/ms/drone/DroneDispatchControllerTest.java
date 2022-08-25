package com.ms.drone;

import com.ms.drone.controller.DispatchController;
import com.ms.drone.model.Drone;
import com.ms.drone.service.DroneService;
import com.ms.drone.service.MedicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class DroneDispatchControllerTest {

    @Mock
    DroneService droneService;

    @InjectMocks
    DispatchController dispatchController;

    MockMvc mockMvc;

    Drone drone;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(dispatchController).build();
        drone = new Drone();
        drone.setSerialNumber("DRONE001");
    }

    @Test
    void findDrone() throws Exception{
        when(droneService.getDrone("DRONE001")).thenReturn(drone);
        mockMvc.perform(get("/api/v1/drones/DRONE001")).andExpect(status().isOk());
    }
}
