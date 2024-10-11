
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
/**
 *
 * @author IOC
 */
public class AppEAC4 {

    public static final int RONDES = 10;
    public static final int NOMBRE_DADES_PARTICIPANT = 3;
    public static final String GUIO_STRING = "------------------------------";
    public static final String TITOL_MENU_STRING = "GESTIO IOC BOWLING";
    public static final String TITOL_ERROR_STRING = "ERROR";

    Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        AppEAC4 bowlingApp = new AppEAC4();
        bowlingApp.start();
    }

    public void start() {
        System.out.println("Quants jugadors hi haurà?");
        int playersNumber = entrada.nextInt();
        int points[][] = initializePoints(playersNumber);
        for (int i = 0; i < points.length; i++) {
            System.out.print("{{");
            for (int j = 0; j < points[i].length; j++) {
                if (j < points[i].length - 1) {
                    System.out.print(points[i][j] + ",");
                } else {
                    System.out.print(points[i][j]);
                }
            }
            System.out.print("}}");
            System.out.println();
        }
        String players[][] = initializePlayers(playersNumber);
        System.out.println("{");
        for (int i = 0; i < players.length; i++) {
            System.out.print("{");
            for (int j = 0; j < players[i].length - 1; j++) {
                System.out.print(players[i][j] + ",");
            }
            if (i < points.length - 1) {
                System.out.println("},");
            } else {
                System.out.println("}");
            }
        }
        System.out.println("}");
        showMenu("1 - Primera opció\n2 - Segona opció\n3 - Tercera opció");
        showError("Error molt greu en l’aplicació");
        System.out.println();
        askForString("Introdueixi el seu nom", "No s'ha introduït cap informació");
        askForInteger("Introdueixi la seva edat", "El valor introduït no es un nombre sencer");
        entrada.close();
    }

    public int[][] initializePoints(int playersNumber) {

        if (playersNumber > 0) {
            /* INICIALITZACIO MATRIU DE PUNTUACIONS */
            int[][] points = new int[playersNumber][RONDES];
            for (int i = 0; i < points.length; i++) {
                for (int j = 0; j < points[i].length; j++) {
                    points[i][j] = -1;
                }
            }
            return points;
        } else {
            return null;
        }
    }

    public String[][] initializePlayers(int playersNumber) {

        if (playersNumber > 0) {
            /* INICIALITZACIO MATRIU D'USUARIS */
            String[][] players = new String[playersNumber][NOMBRE_DADES_PARTICIPANT];
            for (int i = 0; i < players.length; i++) {
                for (int j = 0; j < players[i].length; j++) {
                    players[i][j] = "";
                }
            }
            return players;
        } else {
            return null;
        }
    }

    /* FUNCIO MOSTRA MISSATGE INFORMACIO */
    public void showMenu(String menuText) {

        if (menuText != null && menuText != "") {
            System.out.println(GUIO_STRING + "\n" + TITOL_MENU_STRING + "\n" + GUIO_STRING);
            System.out.println(menuText);
        } else {
            return;
        }

    }

    /* FUNCIO MOSTRA MISSATGE ERROR */
    public void showError(String textError) {

        if (textError != null && textError != "") {
            System.out.println(GUIO_STRING + "\n" + TITOL_ERROR_STRING + "\n" + GUIO_STRING);
            System.out.println(textError);
        } else {
            return;
        }

    }

    /* FUNCIO DEMANA UN STRING A L'USUARI */
    public String askForString(String message, String errorMessage) {
        System.out.println(message);
        String nomUsuari = entrada.nextLine();
        while (nomUsuari.isEmpty()) {
            System.out.println(errorMessage);
            System.out.println(message);
            nomUsuari = entrada.nextLine();
        }
        System.out.println(nomUsuari);
        return nomUsuari;
    }

    /* FUNCIO DEMANA UN ENTER A L'USUARI */
    public int askForInteger(String message, String errorMessage) {
        System.out.println(message);
        while (!entrada.hasNextInt()) {
            System.out.println(errorMessage);
            System.out.println(message);
            entrada.nextLine();
        }
        int edatUsuari = entrada.nextInt();
        entrada.nextLine();
        System.out.println(edatUsuari);
        return edatUsuari;
    }

    public void insertPlayerNames(String[][] playersData, int rowNumber, String name, String lastName, int age) {
        if (playersData == null ||
                rowNumber < 0 ||
                rowNumber > playersData.length ||
                name == null ||
                lastName == null ||
                age < 0) {
            return;
        } else {

        }
    }

    public void setRoundPoints(int[][] pointsMatrix, int rowIndex, int round, int points) {

    }

    public void showRounds(String[][] playersData, int[][] pointsMatrix) {

    }

}
