package football;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert; // this import!

import java.util.Collection;


public class FootballTeamTests {

    private Footballer player1;
    private Footballer player2;

    private Collection<Footballer> footballerCollection;
    private FootballTeam team;

    @Before
    public void setUp() {
        player1 = new Footballer("Gosho");
        player2 = new Footballer("Pesho");
        FootballTeam team = new FootballTeam("CSKA", 2);
        team.addFootballer(player1);
        team.addFootballer(player2);
    }

    @Test(expected = NullPointerException.class)
    public void test_PlayerWithNull_ShouldThrow() {
        Footballer player = new Footballer(null);
        team.addFootballer(player);
    }

    @Test
    public void test_FootballTeamCreate() {
        FootballTeam team = new FootballTeam("CSKA", 2);
        String expected = "CSKA";
        Assert.assertEquals(expected, team.getName());
        int expectedVacantPos = 2;
        Assert.assertEquals(expectedVacantPos, team.getVacantPositions());
    }

    @Test(expected = NullPointerException.class)
    public void test_NullNameShouldThrow() {
        FootballTeam team = new FootballTeam(null, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_NegativeVacantPositions_ShouldThrow() {
        FootballTeam team = new FootballTeam("CSKA", -2);
    }

    @Test
    public void test_TeamSize() {
        FootballTeam team = new FootballTeam("CSKA", 2);
        team.addFootballer(player1);
        team.addFootballer(player2);
        Assert.assertEquals(2, team.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddMorePlayersToTeam_ShouldThrow() {
        Footballer newPlayer = new Footballer("Bozho");
        FootballTeam team = new FootballTeam("CSKA", 2);
        team.addFootballer(player1);
        team.addFootballer(player2);
        team.addFootballer(newPlayer);

    }

    @Test
    public void test_RemovePlayerSuccess() {
        FootballTeam team = new FootballTeam("CSKA", 2);
        team.addFootballer(player1);
        team.addFootballer(player2);
        team.removeFootballer("Gosho");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveNullPlayer_ShouldThrow() {
        FootballTeam team = new FootballTeam("CSKA", 2);
        team.addFootballer(player1);
        team.addFootballer(player2);
        team.removeFootballer("Ivan");
    }

    @Test
    public void test_RemovePlayerForSaleByName() {
        FootballTeam team = new FootballTeam("CSKA", 2);
        team.addFootballer(player1);
        team.addFootballer(player2);
        team.footballerForSale("Pesho");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemovePlayerForSaleByName_WhenNull_ShouldThrow() {
        FootballTeam team = new FootballTeam("CSKA", 2);
        team.addFootballer(player1);
        team.addFootballer(player2);
        team.footballerForSale("Ivan");
    }

}
