package util;

import java.util.Stack;

public class Routes {

    private static Stack<String> History = new Stack();
    private static String actual_page = "";

    public static void addToHistory(String route) {
        History.push(route);
        actual_page = route;
    }

    public static Stack<String> getHistory() {
        return History;
    }

    public static String getActual_page() {
        return actual_page;
    }

    public static class Main {
        public static String MAIN = "/views/fxml/main.fxml";
        public static String HOME = "/views/fxml/home.fxml";
    }
    public static class Attendance {
        public static String MAIN = "/views/fxml/attendance.fxml";
    }

}
