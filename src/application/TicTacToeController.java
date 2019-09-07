package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Random;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class TicTacToeController implements Initializable{
	    @FXML
	    private Circle CircleOne;

	    @FXML
	    private Circle CircleTwo;

	    @FXML
	    private Circle CircleThree;

	    @FXML
	    private Circle CircleFour;

	    @FXML
	    private Circle CircleFive;

	    @FXML
	    private Circle CircleSix;

	    @FXML
	    private Circle CircleSeven;

	    @FXML
	    private Circle CircleEight;

	    @FXML
	    private Circle CircleNine;

	    @FXML
	    private Label XOne;

	    @FXML
	    private Label XTwo;

	    @FXML
	    private Label XThree;

	    @FXML
	    private Label XFour;

	    @FXML
	    private Label XFive;

	    @FXML
	    private Label XSix;

	    @FXML
	    private Label XSeven;

	    @FXML
	    private Label XEight;

	    @FXML
	    private Label XNine;

	    @FXML
	    private Label lblMessages;
	    
	    TicTacToeModel tttm = new TicTacToeModel();

	    @FXML
	    public void handleSquareOneClick(MouseEvent event) {
	        this.handleSquareClick(1);
	    }

	    @FXML
	    public void handleSquareTwoClick(MouseEvent event) {
	        this.handleSquareClick(2);
	    }

	    @FXML
	    public void handleSquareThreeClick(MouseEvent event) {
	        this.handleSquareClick(3);
	    }

	    @FXML
	    public void handleSquareFourClick(MouseEvent event) {
	        this.handleSquareClick(4);
	    }

	    @FXML
	    public void handleSquareFiveClick(MouseEvent event) {
	        this.handleSquareClick(5);
	    }

	    @FXML
	    public void handleSquareSixClick(MouseEvent event) {
	        this.handleSquareClick(6);
	    }

	    @FXML
	    public void handleSquareSevenClick(MouseEvent event) {
	        this.handleSquareClick(7);
	    }

	    @FXML
	    public void handleSquareEightClick(MouseEvent event) {
	        this.handleSquareClick(8);
	    }

	    @FXML
	    public void handleSquareNineClick(MouseEvent event) {
	        this.handleSquareClick(9);
	    }

	    public void handleSquareClick(int squareNumber) {
	        if(!isAlreadySelectedBox(squareNumber) && tttm.allowMoves == true) {
	            switch(squareNumber) {
	                case 1:
	                    this.showCircleOne();
	                    break;
	                case 2:
	                    this.showCircleTwo();
	                    break;
	                case 3:
	                    this.showCircleThree();
	                    break;
	                case 4:
	                    this.showCircleFour();
	                    break;
	                case 5:
	                    this.showCircleFive();
	                    break;
	                case 6:
	                    this.showCircleSix();
	                    break;
	                case 7:
	                    this.showCircleSeven();
	                    break;    
	                case 8:
	                    this.showCircleEight();
	                    break;
	                case 9:
	                    this.showCircleNine();
	                    break;
	                default:
	                    System.out.println("Das ist nicht möglich");
	                    break;
	            }

	            tttm.filledSquares[tttm.filledSquaresCounter] = squareNumber;
	            tttm.filledCircles[tttm.filledCirclesCounter] = squareNumber;
	            tttm.filledSquaresCounter++;
	            tttm.filledCirclesCounter++;

	            if(this.checkVictory()) {
	                this.endGame();
	            } else {
	                this.playRandomMove();

	                if(this.checkVictory()) {
	                    this.endGame();
	                }
	            }
	        } else if(tttm.filledSquaresCounter >= 9) {
	        	tttm.tie = true;
	            this.endGame();
	        }
	    }

	    public boolean isAlreadySelectedBox(int squareNumber) {
	        boolean found = false;

	        for(int filledSquare : tttm.filledSquares) {
	            if(squareNumber == filledSquare) {
	                found = true;
	            }
	        }

	        return found == true;
	    }

	    public boolean checkVictory() {
	        if(tttm.filledCirclesCounter < 3 && tttm.filledXCounter < 3) {
	            return false;
	        }

	        for(int[] filled : tttm.winningPositions) {
	            int slotCounter = 0;

	            for(int singleFilled : filled) {
	                if(this.isOccupiedByCircle(singleFilled)) {
	                    slotCounter++;
	                }
	            }

	            if(slotCounter == 3) {
	            	tttm.winningTeam = 'O';
	            	tttm.allowMoves = false;
	                return true;
	            }

	            slotCounter = 0;

	            for(int singleFilled : filled) {
	                if(this.isOccupiedByX(singleFilled)) {
	                    slotCounter++;
	                }
	            }

	            if(slotCounter == 3) {
	            	tttm.winningTeam = 'X';
	            	tttm.allowMoves = false;
	                return true;
	            }
	        }

	        return false;
	    }

	    public void showCircleOne() {
	        this.CircleOne.setVisible(true);
	    }

	    public void showCircleTwo() {
	        this.CircleTwo.setVisible(true);
	    }

	    public void showCircleThree() {
	        this.CircleThree.setVisible(true);
	    }

	    public void showCircleFour() {
	        this.CircleFour.setVisible(true);
	    }

	    public void showCircleFive() {
	        this.CircleFive.setVisible(true);
	    }

	    public void showCircleSix() {
	        this.CircleSix.setVisible(true);
	    }

	    public void showCircleSeven() {
	        this.CircleSeven.setVisible(true);
	    }

	    public void showCircleEight() {
	        this.CircleEight.setVisible(true);
	    }

	    public void showCircleNine() {
	        this.CircleNine.setVisible(true);
	    }

	    public void playRandomMove() {
	        Random random = new Random();
	        int result = random.nextInt(9 - 1 + 1) + 1;;

	        if(tttm.filledSquaresCounter < 9) {
	            while(this.isAlreadySelectedBox(result)) {
	                result = random.nextInt(9 - 1 + 1) + 1;
	            }

	            switch(result) {
	                case 1:
	                    this.showXOne();
	                    break;
	                case 2:
	                    this.showXTwo();
	                    break;
	                case 3:
	                    this.showXThree();
	                    break;
	                case 4:
	                    this.showXFour();
	                    break;
	                case 5:
	                    this.showXFive();
	                    break;
	                case 6:
	                    this.showXSix();
	                    break;
	                case 7:
	                    this.showXSeven();
	                    break;    
	                case 8:
	                    this.showXEight();
	                    break;
	                case 9:
	                    this.showXNine();
	                    break;
	                default:
	                    System.out.println("Das ist nicht möglich");
	                    break;
	            }

	            tttm.filledSquares[tttm.filledSquaresCounter] = result;
	            tttm.filledX[tttm.filledXCounter] = result;
	            tttm.filledSquaresCounter++;
	            tttm.filledXCounter++;
	        } else {
	        	tttm.tie = true;
	            this.endGame();
	        }


	    }

	    public void showXOne() {
	        this.XOne.setVisible(true);
	    }

	    public void showXTwo() {
	        this.XTwo.setVisible(true);
	    }

	    public void showXThree() {
	        this.XThree.setVisible(true);
	    }

	    public void showXFour() {
	        this.XFour.setVisible(true);
	    }

	    public void showXFive() {
	        this.XFive.setVisible(true);
	    }

	    public void showXSix() {
	        this.XSix.setVisible(true);
	    }

	    public void showXSeven() {
	        this.XSeven.setVisible(true);
	    }

	    public void showXEight() {
	        this.XEight.setVisible(true);
	    }

	    public void showXNine() {
	        this.XNine.setVisible(true);
	    }

	    public boolean isOccupiedByCircle(int circlePosition) {
	        boolean found = false;

	        for(int filledCircle : tttm.filledCircles) {
	            if(filledCircle == circlePosition) {
	                found = true;
	            }
	        }

	        return found == true;
	    }

	    public boolean isOccupiedByX(int xPosition) {
	        boolean found = false;

	        for(int filled : tttm.filledX) {
	            if(filled == xPosition) {
	                found = true;
	            }
	        }

	        return found == true;
	    }

	    public void endGame() {
	    	tttm.allowMoves = false;

	        if(tttm.tie == true) {
	            this.lblMessages.setText("Unentschieden!");
	        } else if(String.valueOf(tttm.winningTeam).equals("O")) {
	            this.lblMessages.setText("O hat gewonnen!");
	        } else if(String.valueOf(tttm.winningTeam).equals("X")) {
	            this.lblMessages.setText("X hat gewonnen!");
	        }
	    }

	    @FXML
	    public void handleResetButton(ActionEvent event) {
	        this.CircleOne.setVisible(false);
	        this.CircleTwo.setVisible(false);
	        this.CircleThree.setVisible(false);
	        this.CircleFour.setVisible(false);
	        this.CircleFive.setVisible(false);
	        this.CircleSix.setVisible(false);
	        this.CircleSeven.setVisible(false);
	        this.CircleEight.setVisible(false);
	        this.CircleNine.setVisible(false);

	        this.XOne.setVisible(false);
	        this.XTwo.setVisible(false);
	        this.XThree.setVisible(false);
	        this.XFour.setVisible(false);
	        this.XFive.setVisible(false);
	        this.XSix.setVisible(false);
	        this.XSeven.setVisible(false);
	        this.XEight.setVisible(false);
	        this.XNine.setVisible(false);

	        tttm.winningTeam = 0;

	        tttm.allowMoves = true;
	        tttm.tie = false;

	        this.lblMessages.setText("");

	        tttm.filledSquares = new int[9];
	        tttm.filledCircles = new int[5];
	        tttm.filledX = new int[5];

	        tttm.filledSquaresCounter = 0;
	        tttm.filledCirclesCounter = 0;
	        tttm.filledXCounter = 0;
	    }

	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	     
	    }    

}
