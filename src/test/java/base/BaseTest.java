package base;

import base.listeners.Listener;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({Listener.class})
@Slf4j
public class BaseTest {
    @BeforeMethod
    public void beforeTestSetup() {
        log.info("\n Before test login start \n");
    }
}
