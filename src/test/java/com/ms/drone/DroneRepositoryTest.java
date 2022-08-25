package com.ms.drone;

import com.ms.drone.model.Drone;
import com.ms.drone.repository.DroneRepository;
import com.ms.drone.service.impl.DroneServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ms.drone.util.Constants.DroneState.IDLE;
import static com.ms.drone.util.Constants.DroneState.LOADING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DroneRepositoryTest {

    @Mock
    DroneRepository droneRepository;

    @InjectMocks
    DroneServiceImpl droneService;

    Drone drone;

    @BeforeEach
    void setUp() {

        drone = new Drone();
        drone.setSerialNumber("DRONE001");
    }

    @Test
    void findDroneBySerialNumber() throws Exception {

        when(droneRepository.findBySerialNumber("DRONE001")).thenReturn(Optional.ofNullable(drone));

        Drone opt = droneService.getDrone("DRONE001");
        assertEquals(opt.getSerialNumber(), "DRONE001");
    }

    @Test
    void findAllDrones() throws Exception{

        Optional<List<Drone>> opt = droneRepository.getAvailableDronesForLoading();
        Set<String> set = Collections.emptySet();
        if (opt.isPresent()) {
            set = opt.get().stream().map(Drone::getDroneState).map(state -> state.name()).collect(Collectors.toSet());
            for (String state : set) {
                boolean b = state.equalsIgnoreCase(IDLE.name()) || state.equals(LOADING.name());
                assertTrue(b);
            }
        }
        assertTrue(set.size() < 2);
    }
}
