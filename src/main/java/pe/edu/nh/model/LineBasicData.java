package pe.edu.nh.model;

import java.util.LinkedHashMap;
import java.util.Map;


public class LineBasicData {
    private static Map<String, Integer[]> data;
    static {
        data = new LinkedHashMap<>();
        data.put("Installation & Developers", new Integer[] {
                43934, 48656, 65165, 81827, 112143, 142383,
                171533, 165174, 155157, 161454, 154610, 168960, 171558
        });
        data.put("Manufacturing", new Integer[] {
                24916, 37941, 29742, 29851, 32490, 30282,
                38121, 36885, 33726, 34243, 31050, 33099, 33473
        });
        data.put("Sales & Distribution", new Integer[] {
                11744, 30000, 16005, 19771, 20185, 24377,
                32147, 30912, 29243, 29213, 25663, 28978, 30618
        });
        data.put("Operations & Maintenance", new Integer[] {
                null, null, null, null, null, null, null,
                null, 11164, 11218, 10077, 12530, 16585
        });
        data.put("Other", new Integer[] {
                21908, 5548, 8105, 11248, 8989, 11816, 18274,
                17300, 13053, 11906, 10073, 11471, 11648
        });
    }

    public static Map<String, Integer[]> getData() {
        return data;
    }
} 