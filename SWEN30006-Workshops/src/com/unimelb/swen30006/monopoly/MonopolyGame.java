package com.unimelb.swen30006.monopoly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * This class is created based on case study of Monopoly of "Applying UML and Patterns, 3rd edition by Craig Larman".
 * For demonstration on subject SWEN30006 at The University of Melbourne 
 * @author 	Yunzhe(Alvin) Jia
 * @version 1.0
 * @since 	2016-07-18
 *
 */
public class MonopolyGame {
	public static final int ROUNDS_TOTAL = 20;
	public static final int MIN_NUM_OF_PLAYER = 2;
	public static final int MAX_NUM_OF_PLAYER = 8;
	
	private List<Player> players;
	private Board board = new Board();
	private Die[] dice = {new Die(), new Die()};
	
	private int roundsSimulated = 0; // Records the amount of rounds that have been simulated
	
	/**
	 * initialize the system with number of Players
	 * @param numOfPlayers number of Players
	 */
	public MonopolyGame(int numOfPlayers){
		players = new ArrayList<Player>(numOfPlayers);
		
		for(int i = 0; i < numOfPlayers;i++){
			Player p;
			p = new Player("Player " + (i+1), dice, board);
			players.add(p);
		}
	}
	
	/**
	 * start the game simulation
	 */
	public void playGame(){
		for(int i = 0; i < ROUNDS_TOTAL; i++){
			playRound();
		}
		System.out.println(roundsSimulated); // Print number of rounds simulated after finished game
	}
	
	public List<Player> getPlayers(){
		return players;
	}
	
	private void playRound(){
		for(Iterator<Player> it = players.iterator(); it.hasNext(); ){
			Player player = (Player) it.next();
			player.takeTurn();
		}
		roundsSimulated++; // Increment after each round played
	}
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int numOfPlayer = 0;
		while (numOfPlayer > MAX_NUM_OF_PLAYER || numOfPlayer < MIN_NUM_OF_PLAYER){
			System.out.print("Please enter the number of players (between 2 - 8): ");
			numOfPlayer = scanner.nextInt();
		}
		scanner.close();
		
		MonopolyGame game = new MonopolyGame(numOfPlayer);
		game.playGame();
	}
}
