package fop.w3box;

public class CoverTheTiles extends MiniJava {

  public static void outputTiles(boolean[] tiles) {
    StringBuilder sb = new StringBuilder("Open tiles: 1:");
    sb.append(tiles[0]);
    for (int i = 1; i < tiles.length; i++) {
    sb.append(" ").append(i + 1).append(":").append(tiles[i]);
    }
    write(sb.toString());
  }

  public static void main(String[] args) throws IllegalAccessException {
	 boolean[] tiles = new boolean[10];
        int k = 1;
        int firstPlayerCredit = 0, secondPlayerCredit = 0;
        int number1 = 0, number2 = 0;
        int totalOfOpenTiles = 55; //sum from 1 to 10;
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = true;
        }
        boolean firstPlayer = true, secondPlayer = true; // When first player's move is done I will make it false and secondPlayer true in order to jump in second players's loop and so on.
        // K <= 5, because, in nested while loop the variable is increased when both of the players have done theirs moves;
        while (k <= 5) {
            while (firstPlayer) {
                write("Player 1 numbers:");
                int input1 = dice();
                int input2 = dice();
                int sumOfDices = input1 + input2;
                write(input1);
                write(input2);
                outputTiles(tiles);
                write("Which tiles to cover by player 1? (0 for no valid combination)");
                number1 = readInt("Tile 1:");
                number2 = readInt("Tile 2:");
                if (number1 + number2 == 0) {
                    firstPlayerCredit += totalOfOpenTiles;
                    firstPlayer = false;
                    secondPlayer = true;
                } else if (number1 + number2 == sumOfDices && tiles[number1 - 1] && tiles[number2 - 1]) {
                    tiles[number1 - 1] = false;
                    tiles[number2 - 1] = false;
                    totalOfOpenTiles = totalOfOpenTiles - (number1  + number2);
                    firstPlayer = false;
                    secondPlayer = true;
                }else if(number1 + number2 != sumOfDices){
                    secondPlayer = false;
                }
            }
            if(totalOfOpenTiles == 0){
                write("Player 1 wins!");
                break;
            }
            while (secondPlayer) {
                write("Player 2 numbers:");
                int input1 = dice();
                int input2 = dice();
                int sumOfDices = input1 + input2;
                write(input1);
                write(input2);
                outputTiles(tiles);
                write("Which tiles to cover by player 1? (0 for no valid combination)");
                number1 = readInt("Tile 1:");
                number2 = readInt("Tile 2:");
                if (number1 + number2 == 0) {
                    secondPlayerCredit += totalOfOpenTiles;
                    secondPlayer = false;
                    firstPlayer  = true;
                } else if (number1 + number2 == sumOfDices && tiles[number1 - 1] && tiles[number2 - 1]) {
                    tiles[number1 - 1] = false;
                    tiles[number2 - 1] = false;
                    totalOfOpenTiles = totalOfOpenTiles - (number1  + number2);
                    secondPlayer = false;
                    firstPlayer = true;
                }
                // here I don't have another else if, because if number1 + number2 != sumOfDice, secondplayer is already true and it will ask  the player until input is correct.
            }
            if(totalOfOpenTiles == 0){
                write("Player 2 wins!");
                break;
            }
            k++;
        }
        if(secondPlayerCredit < firstPlayerCredit)
            write("Player 2 Wins!");
        else if(firstPlayerCredit < secondPlayerCredit)
            write("Player 1 Wins!");
        else
            write("Draw!");
  }
}
