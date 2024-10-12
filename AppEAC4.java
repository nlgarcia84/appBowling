
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
    public static final int MAX_POINTS = 10;
    public static final int NOMBRE_DADES_PARTICIPANT = 3;
    public static final String GUIO_STRING = "------------------------------";
    public static final String TITOL_MENU_STRING = "GESTIO IOC BOWLING";
    public static final String TITOL_ERROR_STRING = "ERROR";
    public static final String INTRO_NUMERO_JUGADORS = "Quants jugadors hi haurà?";
    public static final String INTRO_NOM = "Introdueixi el nom del jugador";
    public static final String INTRO_COGNOM = "Introdueixi el cognom del jugador";
    public static final String STRING_ERROR = "No s'ha introduït cap informació";
    public static final String INTRO_EDAT = "Introdueixi l'edat del jugador";
    public static final String ENTER_ERROR = "El valor introduït no es un nombre sencer";
    public static final String MENU_OPTIONS = "1) Puntuar ronda\n2) Mostrar tauler\n0) Sortir";
    public static final String MENU_ERROR = "No s'ha introduit un número correcte de jugadors";
    public static final String MENU_INVALID_OPTION = "No s'ha introduït una opció vàlida";

    Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        /* INSTANCIEM LA CLASSE APPEAC4 */
        AppEAC4 bowlingApp = new AppEAC4();
        bowlingApp.start();
    }

    public void start() {
        int playersNumber = askForInteger(INTRO_NUMERO_JUGADORS, GUIO_STRING);
        String playersData[][] = initializePlayers(playersNumber);
        for (int i = 0; i < playersNumber; i++) {
            String name = askForString(INTRO_NOM, STRING_ERROR);
            String lastName = askForString(INTRO_COGNOM, STRING_ERROR);
            int age = askForInteger(INTRO_EDAT, ENTER_ERROR);
            insertPlayerNames(playersData, i, name, lastName, age);
        }
        /* MOSTREM MENU DÓPCIONS */
        if (playersNumber == 0 || playersNumber < 0) {
            showError(MENU_ERROR);
        } else {
            showMenu(MENU_OPTIONS);
        }
        System.out.println();
        /* MOSTRA TOTA LA INFO DELS JUGADORS I DE LES PUNTUACIONS */
        /* showRounds(playersData, pointsMatrix); */
        entrada.close();
    }

    public int[][] initializePoints(int playersNumber) {

        if (playersNumber < 0) {
            /* INICIALITZACIO MATRIU DE PUNTUACIONS */
            return null;
        } else {
            int[][] pointsMatrix = new int[playersNumber][RONDES];
            for (int i = 0; i < pointsMatrix.length; i++) {
                for (int j = 0; j < pointsMatrix[i].length; j++) {
                    pointsMatrix[i][j] = -1;
                }
            }
            return pointsMatrix;
        }
    }

    public String[][] initializePlayers(int playersNumber) {

        if (playersNumber < 0) {
            /* INICIALITZACIO MATRIU D'USUARIS */
            return null;
        } else {
            String[][] playersData = new String[playersNumber][NOMBRE_DADES_PARTICIPANT];
            for (int i = 0; i < playersData.length; i++) {
                for (int j = 0; j < playersData[i].length; j++) {
                    playersData[i][j] = "";
                }
            }
            return playersData;
        }
    }

    /* FUNCIO MOSTRA MISSATGE INFORMACIO */
    public void showMenu(String menuText) {

        if (menuText != null && menuText != "") {
            System.out.println(GUIO_STRING + "\n" + TITOL_MENU_STRING + "\n" + GUIO_STRING);
            System.out.println(menuText);
            int opcioEscollida = entrada.nextInt();
            switch (opcioEscollida) {
                case 1:

                    break;
                case 2:

                    break;
                case 0:

                    break;

                default:
                    System.out.println(GUIO_STRING + "\n" + MENU_INVALID_OPTION + "\n" + GUIO_STRING);
                    break;
            }
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

    /* FUNCIO DEMANA UN STRING(nom y cognomms) A L'USUARI */
    public String askForString(String message, String errorMessage) {
        System.out.println(message);
        String inputString = entrada.nextLine();
        while (inputString.isEmpty()) {
            System.out.println(errorMessage);
            System.out.println(message);
            inputString = entrada.nextLine();
        }
        return inputString;
    }

    /* FUNCIO DEMANA UN ENTER A L'USUARI */
    public int askForInteger(String message, String errorMessage) {
        System.out.println(message);
        while (!entrada.hasNextInt()) {
            System.out.println(errorMessage);
            System.out.println(message);
            entrada.nextLine();
        }
        int inputInteger = entrada.nextInt();
        entrada.nextLine();
        return inputInteger;
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
            playersData[rowNumber][0] = name;
            playersData[rowNumber][1] = lastName;
            playersData[rowNumber][2] = String.valueOf(age);
        }
    }

    public void setRoundPoints(int[][] pointsMatrix, int rowIndex, int round, int points) {
        if (pointsMatrix == null ||
                rowIndex < 0 ||
                rowIndex > RONDES ||
                round == 0 ||
                round < 0 ||
                points > MAX_POINTS) {
            return;
        } else {
            for (int i = 0; i < pointsMatrix.length; i++) {
                for (int j = 0; j < pointsMatrix[i].length; j++) {
                    pointsMatrix[rowIndex][round] = points;
                }
            }
        }
    }

    public void showRounds(String[][] playersData, int[][] pointsMatrix) {
        for (int i = 0; i < playersData.length; i++) {
            for (int j = 0; j < playersData[i].length; j++) {
                System.out.print(playersData[i][j] + " ");
            }
        }
        for (int i = 0; i < pointsMatrix.length; i++) {
            for (int j = 0; j < pointsMatrix[i].length; j++) {
                System.out.print(pointsMatrix[i][j]);
            }
            System.out.println();
        }
    }

}
