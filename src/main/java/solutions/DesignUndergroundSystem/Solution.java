package solutions.DesignUndergroundSystem;

import java.util.*;

class Solution {
    public static void main(String[] args) {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);  // Customer 45 "Leyton" -> "Waterloo" in 15-3 = 12
        undergroundSystem.checkOut(27, "Waterloo", 20);  // Customer 27 "Leyton" -> "Waterloo" in 20-10 = 10
        undergroundSystem.checkOut(32, "Cambridge", 22); // Customer 32 "Paradise" -> "Cambridge" in 22-8 = 14
        System.out.println(undergroundSystem.getAverageTime("Paradise", "Cambridge")); // return 14.00000. One trip "Paradise" -> "Cambridge", (14) / 1 = 14
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));    // return 11.00000. Two trips "Leyton" -> "Waterloo", (10 + 12) / 2 = 11
        undergroundSystem.checkIn(10, "Leyton", 24);
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));   // return 11.00000
        undergroundSystem.checkOut(10, "Waterloo", 38);  // Customer 10 "Leyton" -> "Waterloo" in 38-24 = 14
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));   // return 12.00000. Three trips "Leyton" -> "Waterloo", (10 + 12 + 14) / 3 = 12
    }
}

class UndergroundSystem {

    Map<Integer, StartInfo> checkIns;

    Map<String, JourneyTimeInfo> travels;

    public UndergroundSystem() {
        checkIns = new HashMap<>();
        travels = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkIns.put(id, new StartInfo(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        StartInfo startInfo = checkIns.remove(id);
        String journeyName = startInfo.startCity + ":" + stationName;

        if (!travels.containsKey(journeyName)) {
            travels.put(journeyName, new JourneyTimeInfo(t - startInfo.startTime, 1));
        } else {
            JourneyTimeInfo journeyTimeInfo = travels.get(journeyName);
            journeyTimeInfo.timeTaken += t - startInfo.startTime;
            journeyTimeInfo.noOfTimes++;
        }
    }

    public double getAverageTime(String startStation, String endStation) {
        JourneyTimeInfo journeyTimeInfo = travels.get(startStation + ":" + endStation);
        return (double) journeyTimeInfo.timeTaken / journeyTimeInfo.noOfTimes;
    }

    private class StartInfo {
        String startCity;
        Integer startTime;

        StartInfo(String startCity, Integer startTime) {
            this.startCity = startCity;
            this.startTime = startTime;
        }
    }

    private class JourneyTimeInfo {
        Integer timeTaken;
        Integer noOfTimes;

        JourneyTimeInfo(Integer timeTaken, Integer noOfTimes) {
            this.timeTaken = timeTaken;
            this.noOfTimes = noOfTimes;
        }
    }

}