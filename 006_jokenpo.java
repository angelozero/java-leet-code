/**
 * JO - KEN - PO
 */

// Main class
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();
        var firstMove = random.nextInt(3 - 1 + 1) + 1;
        var secondMove = random.nextInt(3 - 1 + 1) + 1;

        var firstHand = JoKenPo.getMoveByValue(firstMove);
        var secondHand = JoKenPo.getMoveByValue(secondMove);

        System.out.println("\n" + firstHand + " X " + secondHand);

        var result = MatchService.execute(firstHand, secondHand);

        if (result == null) {
            System.out.println("EMPATE!");
        } else {
            System.out.println(result.name() + " VENCEU!");
        }

    }
}

// MatchService
public class MatchService {

    public static JoKenPo execute(JoKenPo firstHand, JoKenPo secondHand) {

        if (firstHand.getValue() == secondHand.getValue()){
            return null;
        }

        return !JoKenPo.getLoseMovement(firstHand, secondHand.getValue()) ? firstHand : secondHand;
    }
}

// JoKenPo Enum
import java.util.List;

public enum JoKenPo {

    PAPEL(1, List.of(2)),
    TESOURA(2, List.of(3)),
    PEDRA(3, List.of(1));

    private final int value;

    private final List<Integer> loseMovements;

    JoKenPo(int value, List<Integer> loseMovements) {
        this.loseMovements = loseMovements;
        if (value <= 0 || value > 3) {
            throw new RuntimeException("Invalid JoKenPon !");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Boolean getLoseMovement(JoKenPo joKenPo, int value){
        return joKenPo.loseMovements.contains(value);
    }

    public static JoKenPo getMoveByValue(int value) {
        for (JoKenPo joKenPo : JoKenPo.values()) {
            if (joKenPo.value == value) {
                return joKenPo;
            }
        }
        throw new RuntimeException("Invalid JoKenPon !");
    }
}