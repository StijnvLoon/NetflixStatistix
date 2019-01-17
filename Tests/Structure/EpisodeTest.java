package Structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EpisodeTest {

    @Test
    void testGetAverageWatchedDurationPercentageWithEmptyWatchedDurationReturns0() {
        //Arrange
        Episode episode = new Episode(2, "Test", 20, 2);
        double expectedResult = 0.0;

        //Act
        double result = episode.getAverageWatchedDurationPercentage();

        //Assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void testGetAverageWatchedDurationPercentageWithWatchedDurations5And10And15Returns10() {
        //Arrange
        Episode episode = new Episode(2, "Test", 20, 2);
        double expectedResult = 50.0;

        episode.addWatchedDuration(5);
        episode.addWatchedDuration(10);
        episode.addWatchedDuration(15);

        //Act
        double result = episode.getAverageWatchedDurationPercentage();

        //Assert
        Assertions.assertEquals(expectedResult, result);
    }
}
