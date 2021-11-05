package aplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entities.Card;
import entities.Deck;
import entities.FiveCardDrawHand;
import entities.Hand;
import entities.Player;
import entities.Table;

public class Program {

	static Deck deck = new Deck();

	public static void main(String[] args) {

		deck.shuffle();
		inicialMenu();

	}

	public static void inicialMenu() {

		System.out.printf("============== Poker Scores ============== \n\n");
		System.out.printf("   Utilizacao: Digite um comando para uma\n");
		System.out.printf("            uma funcao abaixo:\n\n");
		System.out.printf("N - Novo Jogo \nS - Sair do Programa\n");

		try (Scanner entry = new Scanner(System.in)) {
			String response = entry.next().toUpperCase();

			if ("S".equals(response)) {

				System.out.println("Programa Encerrado");
				System.exit(0);
			}
			if ("N".equals(response)) {
				System.out.printf("============== Poker Scores ============== \n\n");
				System.out.printf("Selecione uma opcao de jogo: \n\n");
				System.out.println("T - Texas Holdem Poker");
				System.out.println("F - Five Card Draw Poker");
				System.out.println("L - 2-7 Low Ball Poker");

				Scanner sc = new Scanner(System.in);
				String gameType = sc.next().toUpperCase();

				System.out.printf("\n============== Poker Scores ============== \n\n");

				int playerNumber;
				while (true) {
					System.out.printf("Digite o numero de jogadores: ");
					playerNumber = sc.nextInt();
					sc.nextLine();
					if (playerNumber > 6) {
						System.out.println("\nERRO: Numero de jogadores muito alto. Tente 0 a 6 jogadores.");
					} else if (playerNumber <= 0) {
						System.out.println("\nERRO: Numero de jogadores invalido. Tente 0 a 6 jogadores.");
					} else {
						break;
					}
				}

				Player[] players = new Player[playerNumber];
				for (int i = 0; i < playerNumber; i++) {
					double initialCash;
					String name;
					System.out.printf("\n============== Player %d ============== \n\n", i + 1);
					players[i] = new Player(i);

					while (true) {
						System.out.printf("\nValor inicial: ");

						initialCash = sc.nextDouble();
						sc.nextLine();
						if (initialCash <= 0) {
							System.out.println("\nERRO: Valor inicial invalido. Tente novamente.");
						} else {
							break;
						}

					}
					while (true) {
						System.out.printf("\nNome do jogador %d: ", i + 1);
						name = sc.nextLine();
						if (name.length() > 100) {
							System.out.println("\nERRO: Nome muito longo. Tente novamente.");
						} else {
							break;
						}
					}
					players[i].setInitialCash(initialCash);
					players[i].setCash(initialCash);
					players[i].setName(name);
				}

				switch (gameType) {

				case "T":
					roundTexasHoldem(playerNumber, players);
					break;
				case "F":
					roundFiveCardDraw(playerNumber, players);
					break;
				}
			}

		}
	}

	public static void roundTexasHoldem(int playerNumber, Player[] players) {
		int roundCount = 1;
		while (true) {
			System.out.printf("\n============== Poker Scores ============== \n\n");

			Scanner sc = new Scanner(System.in);

			// adicionando cartas aleatorias para a mesa
			List<Card> tableCards = new ArrayList<Card>();
			for (int i = 0; i < 5; i++)
				tableCards.add(deck.removeCard());

			// declaracao de variaveis
			int playerCount = playerNumber;
			boolean foldedPlayers[] = new boolean[playerNumber];
			Boolean isRoundFinished = false;
			double totalBets = 0;

			for (int i = 0; i < playerNumber; i++) {
				// adicionando cartas aleatorias para a mao do player
				List<Card> playerHand = new ArrayList<Card>();
				for (int j = 0; j < 2; j++)
					playerHand.add(deck.removeCard());

				players[i].setPlayerHand(playerHand);
				players[i].setFullHand(new Hand(merge(playerHand, tableCards)));

				System.out.printf("\nMao do Jogador %d: %s\n", i + 1, playerHand.toString());
				while (true) {
					System.out.printf("Aposta do Jogador %d (digite N se ele saiu da mesa): $", i + 1);
					String response = sc.nextLine();
					if (response.charAt(0) == 'N') {
						if (playerCount != 1) {
							foldedPlayers[i] = true;
							playerCount--;
							break;
						} else {
							System.out.println("ERRO: Impossivel sair da mesa. Voce e o unico player.");
						}
					} else {
						try {
							double money = Double.parseDouble(response);
							players[i].addBet(money);
							totalBets += money;
							foldedPlayers[i] = false;
							break;
						} catch (Exception e) {
							System.out.println("ERRO: " + e);
							System.out.println("Tente novamente.");
						}
					}
				}

			}

			System.out.printf("\n----> Cartas da mesa: ");
			System.out.println(tableCards.subList(0, 3).toString());

			// checando se so ha um player sobrando
			if (playerCount == 1) {
				isRoundFinished = true;
			}

			// ---------------------------------Next round of
			// bets---------------------------------
			// se o round estiver terminado, nao ha rodada de apostas
			if (!isRoundFinished) {
				System.out.printf("\n\n============== Poker Scores ============== \n\n");
				System.out.printf("Segunda rodada de apostas\n");
				for (int i = 0; i < playerNumber; i++) {
					if (!foldedPlayers[i]) {
						while (true) {
							System.out.printf("Aposta do Jogador %d (digite N se ele saiu da mesa): $", i + 1);
							String response = sc.nextLine();
							if (response.charAt(0) == 'N') {
								foldedPlayers[i] = true;
								playerCount--;
								break;
							} else {
								try {
									double money = Double.parseDouble(response);
									players[i].addBet(money);
									totalBets += money;
									break;
								} catch (Exception e) {
									System.out.println("ERRO: " + e);
									System.out.println("Tente novamente.");
								}
							}
						}
					}
				}

				System.out.printf("\nCartas da mesa: ");
				System.out.println(tableCards.subList(0, 4).toString());
			}
			if (playerCount == 1) {
				isRoundFinished = true;
			}
			// ---------------------------------Next round of
			// bets---------------------------------
			if (!isRoundFinished) {
				System.out.printf("\n\n============== Poker Scores ============== \n\n");
				System.out.printf("Terceira rodada de apostas\n");
				for (int i = 0; i < playerNumber; i++) {
					if (!foldedPlayers[i]) {
						while (true) {
							System.out.printf("Aposta do Jogador %d (digite N se ele saiu da mesa): $", i + 1);
							String response = sc.nextLine();
							if (response.charAt(0) == 'N') {
								foldedPlayers[i] = true;
								playerCount--;
								break;
							} else {
								try {
									double money = Double.parseDouble(response);
									players[i].addBet(money);
									totalBets += money;
									break;
								} catch (Exception e) {
									System.out.println("ERRO: " + e);
									System.out.println("Tente novamente.");
								}
							}
						}
					}
				}

				System.out.printf("\nCartas da mesa: ");
				System.out.println(tableCards.subList(0, 5).toString());
			}
			if (playerCount == 1) {
				isRoundFinished = true;
			}

			// -------------------------------------End of
			// Round-----------------------------------
			int score = 0;
			List<Player> biggestScore = new ArrayList<Player>();
			if (playerCount == 1) {
				for (int i = 0; i < playerNumber; i++) {
					if (!foldedPlayers[i]) {
						biggestScore.add(players[i]);
					}
				}
			} else {
				for (Player player : players) {
					if (player.getFullHand().getPowerOfHand() > score) {
						biggestScore.clear();
						biggestScore.add(player);
						score = player.getFullHand().getPowerOfHand();
					} else if (player.getFullHand().getPowerOfHand() == score) {
						if (biggestScore.size() == 0) {
							biggestScore.add(player);
						} else {
							int response = player.getFullHand().tieBreak(biggestScore.get(0).getFullHand());
							switch (response) {
							case 0:
								biggestScore.add(player);
								break;
							case 1:
								biggestScore.clear();
								biggestScore.add(player);
								break;
							}
						}
					}
				}
			}
			StringBuilder sb = new StringBuilder();
			System.out.println("============== Poker Scores ==============");
			sb.append(String.format("\n================= ROUND %d ================\n\n", roundCount));
			sb.append("Cartas da mesa: ");
			sb.append(tableCards.subList(0, 5).toString());

			for (int i = 0; i < playerNumber; i++) {
				sb.append(String.format("\n\n---> Jogador %d: \n\n", i + 1));
				sb.append(String.format("Nome do jogador: %s\n", players[i].getName()));
				sb.append(
						String.format("Mao mais forte: %s\n", players[i].getFullHand().getStrongestHand().toString()));
				sb.append(String.format("Mao inicial: %s\n", players[i].getPlayerHand().toString()));
				sb.append(String.format("Jogador saiu da mesa: %s", foldedPlayers[i] ? "sim\n" : "nao\n"));
				sb.append(String.format("Ranking da mao: %s\n", players[i].getFullHand().getHandRanking()));
				if (biggestScore.size() == 1) {
					if (players[i].equals(biggestScore.get(0))) {
						sb.append(String.format("->Jogador %d venceu.\n", i + 1));
						sb.append(String.format("Ganhos na rodada: + $%.2f\n", totalBets - players[i].getBet()));
						players[i].wonGame(totalBets - players[i].getBet());
						sb.append(String.format("Saldo: $ %.2f", players[i].getCash()));

					} else {
						sb.append(String.format("->Jogador %d perdeu.\n", i + 1));
						sb.append(String.format("Ganhos na rodada: - $%.2f\n", players[i].getBet()));
						players[i].lostGame();
						sb.append(String.format("Saldo: $ %.2f", players[i].getCash()));
					}
				} else {
					int winnersCount = biggestScore.size();
					for (int j = 0; j < biggestScore.size(); j++) {
						if (players[i].equals(biggestScore.get(j))) {
							sb.append(String.format("->Jogador %d empatou.\n", i + 1));
							players[i].addWinCount();
							double amount = totalBets / winnersCount;
							if (amount > 2 * players[i].getBet()) {
								totalBets = amount - (2 * players[i].getBet());
								amount = 2 * players[i].getBet();
								winnersCount--;
							}
							sb.append(String.format("Ganhos na rodada: + $%.2f\n", amount - players[i].getBet()));
							players[i].wonGame(amount - players[i].getBet());
							sb.append(String.format("Saldo: $ %.2f", players[i].getCash()));
							break;

						}
					}
				}
			}
			sb.append("\n");
			System.out.println(sb.toString());
			saveResults(sb.toString());
			System.out.println("\n\nDeseja come�ar outro round?");
			System.out.printf("N - Novo Round \nS - Sair do Programa\n");
			String response = sc.next().toUpperCase();
			sc.nextLine();
			if ("S".equals(response)) {
				System.out.println("Programa Encerrado");

				// Final do relatorio
				sb.setLength(0);
				sb.append("================= RELATÓRIO ================\n");
				sb.append("Modalidade: Texas Hold'em\n\n");
				for (Player player : players) {
					sb.append(String.format("Jogador: %s\n", player.getNumber() + 1));
					sb.append(String.format("Porcentagem de vitória: %.2f\n",
							((double) player.getWinCount()) / roundCount * 100));
					sb.append(String.format("Dinheiro Inicial: %.2f\n", player.getInitialCash()));
					sb.append(String.format("Dinheiro Atual: %.2f\n", player.getCash()));
					sb.append(String.format("Lucro: %.2f\n", player.getCash() - player.getInitialCash()));
					sb.append(String.format("Número de Vitórias: %d\n\n", player.getWinCount()));
				}
				saveResults(sb.toString());
				System.out.println(sb.toString());

				System.exit(0);
			}
			deck.resetDeck();
			deck.shuffle();
			for (int i = 0; i < playerNumber; i++) {
				players[i].resetPlayer();
			}
			roundCount++;
		}
	}

	public static void roundFiveCardDraw(int playerNumber, Player[] players) {
		int roundCount = 1;
		while (true) {
			System.out.printf("\n============== Poker Scores ============== \n\n");
			// System.out.printf("Digite o numero de jogadores: ");
			Scanner sc = new Scanner(System.in);

			int playerCount = playerNumber;
			boolean foldedPlayers[] = new boolean[playerNumber];
			Boolean isRoundFinished = false;
			double totalBets = 0;
			double cardsToBeTraded = 0;
			List<Card> playerHand;
			for (int i = 0; i < playerNumber; i++) {
				playerHand = new ArrayList<Card>();

				for (int j = 0; j < 5; j++) {
					playerHand.add(deck.removeCard());
				}

				players[i].setPlayerHand(playerHand);
				FiveCardDrawHand fullHand = new FiveCardDrawHand(playerHand);
				players[i].setFullHand(fullHand);
				// ((FiveCardDrawHand) players[i].getFullHand()).swap();

				System.out.printf("\nMao do Jogador %d: %s\n", i + 1, players[i].getFullHand().toString());
				while (true) {
					System.out.printf("Aposta do Jogador %d (digite N se ele saiu da mesa): $", i + 1);
					String response = sc.nextLine();
					if (response.charAt(0) == 'N') {
						if (playerCount != 1) {
							foldedPlayers[i] = true;
							playerCount--;
							break;
						} else {
							System.out.println("ERRO: Impossivel sair da mesa. Voce e o unico player.");
						}
					} else {
						try {
							double money = Double.parseDouble(response);
							players[i].addBet(money);
							totalBets += money;
							foldedPlayers[i] = false;
							break;
						} catch (Exception e) {
							System.out.println("ERRO: " + e);
							System.out.println("Tente novamente.");
						}
					}
				}
				if (!foldedPlayers[i]) {
					while (true) {
						System.out.printf("\nCartas para trocar (digite 0 para manter): ");
						String response = sc.nextLine();
						try {
							cardsToBeTraded = Double.parseDouble(response);
						} catch (Exception e) {
							System.out.println("ERRO: " + e);
							System.out.println("Tente novamente.");
						}
						if (cardsToBeTraded < 0 || cardsToBeTraded > 5) {
							System.out.println("ERRO: Digite um numero de 0 a 5. Tente novamente");
						} else if (cardsToBeTraded == 0) {
							break;
						} else {
							System.out.printf("\nMao do Jogador %d: %s\n", i + 1, players[i].getFullHand().toString());
							List<Card> handToRemove = new ArrayList<Card>();
							List<Card> handToAdd = new ArrayList<Card>();
							if (cardsToBeTraded != 5) {
								for (int n = 0; n < cardsToBeTraded; n++) {
									int number;
									System.out.printf("\nDigite a posicao %d� da carta a ser trocada (1 a 5): ", n + 1);
									number = sc.nextInt();
									sc.nextLine();
									handToRemove.add(players[i].getFullHand().getHand().get(number - 1));
								}
							} else {
								for (int n = 0; n < 5; n++) {
									handToRemove.add(players[i].getFullHand().getHand().get(n));
								}
							}
							for (int n = 0; n < cardsToBeTraded; n++) {
								handToAdd.add(deck.removeCard());
							}

							// dynamic casting para chamar o metodo swap
							((FiveCardDrawHand) players[i].getFullHand()).swap(handToRemove, handToAdd);
							break;
						}
					}
					System.out.printf("\nMao Final do Jogador %d: %s\n", i + 1, players[i].getFullHand().toString());
				}
			}
			if (playerCount == 1) {
				isRoundFinished = true;
			}

			// ------------ Proxima rodada de apostas -------------
			if (!isRoundFinished) {
				System.out.printf("\n\n============== Poker Scores ============== \n\n");
				System.out.printf("Segunda rodada de apostas\n");
				for (int i = 0; i < playerNumber; i++) {
					System.out.printf("\nMao do Jogador %d: %s\n", i + 1, players[i].getFullHand().toString());
					if (!foldedPlayers[i]) {
						while (true) {
							System.out.printf("Aposta do Jogador %d (digite N se ele saiu da mesa): $", i + 1);
							String response = sc.nextLine();
							if (response.charAt(0) == 'N') {
								foldedPlayers[i] = true;
								playerCount--;
								break;
							} else {
								try {
									double money = Double.parseDouble(response);
									players[i].addBet(money);
									totalBets += money;
									break;
								} catch (Exception e) {
									System.out.println("ERRO: " + e);
									System.out.println("Tente novamente.");
								}
							}
						}
					}
				}
			}
			// ------------------------------ Fim das apostas -----------------------------
			int score = 0;
			List<Player> biggestScore = new ArrayList<Player>();
			if (playerCount == 1) {
				for (int i = 0; i < playerNumber; i++) {
					if (!foldedPlayers[i]) {
						biggestScore.add(players[i]);
					}
				}
			} else {
				for (Player player : players) {
					if (player.getFullHand().getPowerOfHand() > score) {
						biggestScore.clear();
						biggestScore.add(player);
						score = player.getFullHand().getPowerOfHand();
					} else if (player.getFullHand().getPowerOfHand() == score) {
						if (biggestScore.size() == 0) {
							biggestScore.add(player);
						} else {
							int response = player.getFullHand().tieBreak(biggestScore.get(0).getFullHand());
							switch (response) {
							case 0:
								biggestScore.add(player);
								break;
							case 1:
								biggestScore.clear();
								biggestScore.add(player);
								break;
							}
						}
					}
				}
			}

			StringBuilder sb = new StringBuilder();
			System.out.println("============== Poker Scores ==============");
			sb.append(String.format("\n================= ROUND %d ================\n\n", roundCount));

			for (int i = 0; i < playerNumber; i++) {
				sb.append(String.format("\n\n---> Jogador %d: \n\n", i + 1));
				sb.append(String.format("Nome do jogador: %s\n", players[i].getName()));
				sb.append(String.format("Mao do Jogador: %s\n", players[i].getPlayerHand().toString()));
				sb.append(String.format("Jogador saiu da mesa: %s", foldedPlayers[i] ? "sim\n" : "nao\n"));
				sb.append(String.format("Ranking da mao: %s\n", players[i].getFullHand().getHandRanking()));
				if (biggestScore.size() == 1) {
					if (players[i].equals(biggestScore.get(0))) {
						sb.append(String.format("->Jogador %d venceu.\n", i + 1));
						sb.append(String.format("Ganhos na rodada: + $%.2f\n", totalBets - players[i].getBet()));
						players[i].wonGame(totalBets - players[i].getBet());
						players[i].addWinCount();
						sb.append(String.format("Saldo: $ %.2f", players[i].getCash()));

					} else {
						sb.append(String.format("->Jogador %d perdeu.\n", i + 1));
						sb.append(String.format("Ganhos na rodada: - $%.2f\n", players[i].getBet()));
						players[i].lostGame();
						sb.append(String.format("Saldo: $ %.2f", players[i].getCash()));
					}
				} else {
					int initialSize = biggestScore.size();
					for (int j = 0; j < biggestScore.size(); j++) {
						if (players[i].equals(biggestScore.get(j))) {
							sb.append(String.format("->Jogador %d empatou.\n", i + 1));
							players[i].addWinCount();
							double amount = totalBets / initialSize;
							if (amount > 2 * players[i].getBet()) {
								totalBets = amount - (2 * players[i].getBet());
								amount = 2 * players[i].getBet();
								initialSize--;
							}
							sb.append(String.format("Ganhos na rodada: + $%.2f\n", amount - players[i].getBet()));
							players[i].wonGame(amount - players[i].getBet());
							sb.append(String.format("Saldo: $ %.2f", players[i].getCash()));
							break;

						}
					}
				}
			}
			sb.append("\n");
			System.out.println(sb.toString());
			saveResults(sb.toString());
			System.out.println("\n\nDeseja come�ar outro round?");
			System.out.printf("N - Novo Round \nS - Sair do Programa\n");
			String response = sc.next().toUpperCase();
			sc.nextLine();
			if ("S".equals(response)) {
				System.out.println("Programa Encerrado");

				// Final do relatorio
				sb.setLength(0);
				sb.append("================= RELATÓRIO ================\n");
				sb.append("Modalidade: Fiva Card Draw\n\n");
				for (Player player : players) {
					sb.append(String.format("Jogador: %s\n", player.getNumber() + 1));
					sb.append(String.format("Porcentagem de vitória: %.2f\n",
							((double) player.getWinCount()) / roundCount * 100));
					sb.append(String.format("Dinheiro Inicial: %.2f\n", player.getInitialCash()));
					sb.append(String.format("Dinheiro Atual: %.2f\n", player.getCash()));
					sb.append(String.format("Lucro: %.2f\n", player.getCash() - player.getInitialCash()));
					sb.append(String.format("Número de Vitórias: %d\n\n", player.getWinCount()));
				}
				saveResults(sb.toString());
				System.out.println(sb.toString());

				System.exit(0);
			}
			deck.resetDeck();
			deck.shuffle();
			for (int i = 0; i < playerNumber; i++) {
				players[i].resetPlayer();
			}
			roundCount++;
		}
	}

	public static <T> List<T> merge(List<T> list1, List<T> list2) {
		List<T> list = new ArrayList<>();

		list.addAll(list1);
		list.addAll(list2);

		return list;
	}

	public static void saveResults(String s) {

		File file = new File("Games", "game1.txt");
		try {
			if (file.getParentFile().exists()) {
				if (!file.exists()) {
					file.createNewFile();
				}
			} else {
				if (file.getParentFile().mkdir()) {
					file.createNewFile();
				} else {
					throw new IOException("Failed to create directory " + file.getParent());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			bw.write(s);
			bw.close();
		} catch (IOException e) {
			System.out.println("ERRO ao tentar escrever no arquivo");
			e.printStackTrace();
		}

	}

}
