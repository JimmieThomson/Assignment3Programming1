import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        String WeekRoster[][] = new String[7][7];
        for (String[] row: WeekRoster){
            Arrays.fill(row, "---");
        }

        System.out.println(String.format("%20s%15s%15s%15s%15s%15s%15s","Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        String[] time = {"08:00", "10:00", "12:00", "14:00", "16:00", "18:00", "20:00"};

        int i = 0;
        for (final Object[] row : WeekRoster) {
            System.out.print(time[i]);
            System.out.format("%15s%15s%15s%15s%15s%15s%15s%n", row);
            i++;
        }

    }
}
