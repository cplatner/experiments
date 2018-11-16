import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Analyzer implements IRetryAnalyzer
{
    private int count = 0;
    private int maxCount = 3;

    @Override
    public boolean retry(ITestResult result)
    {
        if (count < maxCount)
        {
            System.out.println("Error in " + result.getName() + " with status "
                + result.getStatus() + " Retrying " + count + " times");
            count += 1;
            return true;
        }
        return false;
    }
}
