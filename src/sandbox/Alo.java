package sandbox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class Alo {
    public static void main(String[] args) {
        Set<Player> bestPlayers;
        int bestPlayersValue = MIN_VALUE;
        //tutaj trzeba by wrzucić graczy
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; i < players.size(); i++) {
                for (int k = 0; i < players.size(); i++) {
                    for (int l = 0; i < players.size(); i++) {
                        Set<Player> tempPlayers = new HashSet<>();
                        tempPlayers.add(players.get(i));
                        tempPlayers.add(players.get(j));
                        tempPlayers.add(players.get(k));
                        tempPlayers.add(players.get(l));
                        int tempPlayersValue = evaluate(tempPlayers);
                        if (tempPlayersValue > bestPlayersValue) {
                            bestPlayers = tempPlayers;
                            bestPlayersValue = tempPlayersValue;
                        }
                    }
                }
            }
        }
    }

    public static int evaluate(Set<Player> players) {
        //tutaj by musiał być kod który jakoś to ocenia
        //wstawiłem pierdółke zamiast tego
        return nextInt(0, MAX_VALUE);
    }

    private class Player {
        public String name;
        public String price;
    }
}



