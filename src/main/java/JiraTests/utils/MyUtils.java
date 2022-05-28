package JiraTests.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MyUtils {
    static Logger logger = LoggerFactory.getLogger("utils");

    public static File makeScreenshot(WebDriver driver, String filename) {
        File temp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destination = new File("./target/screens/" + filename);
        try {
            FileUtils.copyFile(temp, destination);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return destination;
    }

    public static void getLogs(WebDriver driver){
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> allLogRows = browserLogs.getAll();
       /* Assertions.assertEquals(0,allLogRows.size());
        Assertions.assertTrue(allLogRows.isEmpty());*/
        if (allLogRows.size() > 0 ) {
            allLogRows.forEach(logEntry -> {
                logger.info(logEntry.getMessage());
            });

        }
    }
}