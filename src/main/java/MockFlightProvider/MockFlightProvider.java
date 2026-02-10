package MockFlightProvider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.dto.FlightStateDto;

@Component
public class MockFlightProvider {

    private final List<FlightStateDto> flights = new ArrayList<>();

    public MockFlightProvider() {
        flights.add(new FlightStateDto("AB123", 25.0777, 121.2328, 90, 32000, 450));
        flights.add(new FlightStateDto("CD456", 25.12,   121.18,   270, 28000, 430));
    }

    public List<FlightStateDto> fetch() {
        return flights;
    }
}

