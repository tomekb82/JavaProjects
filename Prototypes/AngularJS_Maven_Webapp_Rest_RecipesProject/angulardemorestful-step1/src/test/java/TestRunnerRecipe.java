import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 11/30/13
 * Time: 10:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestRunnerRecipe {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(JunitTestSuiteRecipe.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
