package cz.muni.fi.pv168.project;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class MainTest {


    @Test
    public void testPrintMessage() {
        var message = "New Word";
        assertThat(message)
                .isEqualToIgnoringNewLines(message);
    }
}
