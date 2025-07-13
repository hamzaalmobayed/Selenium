package hamza.example;
import org.testng.TestNG;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        List<String> suites = new ArrayList<>();
        suites.add("C:\\Users\\Hamza\\IdeaProjects\\MadfuMerchant\\src\\testing.xml");  // Replace with your actual suite file path
        testng.setTestSuites(suites);
        testng.run();
    }
}