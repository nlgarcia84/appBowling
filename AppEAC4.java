
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
/**
 *
 * @author IOC
 */
public class AppEAC4 {

    // DECLAREM LES CONSTANTS
    public static final int RONDES = 10;
    public static final int MAX_POINTS = 10;
    public static final int NOMBRE_DADES_PARTICIPANT = 3;
    public static final String FULL_NAME = "FULL NAME";
    public static final String AGE = "AGE";
    public static final String GUIO_STRING = "---------------------------------------------------------------------------";
    public static final String TITOL_MENU_STRING = "GESTIO IOC BOWLING";
    public static final String TITOL_ERROR_STRING = "ERROR";
    public static final String INTRO_NUMERO_JUGADORS = "Quants jugadors hi haurà?";
    public static final String INTRO_NOM = "Introdueixi el nom del jugador";
    public static final String INTRO_COGNOM = "Introdueixi el cognom del jugador";
    public static final String STRING_ERROR = "No s'ha introduït cap informació";
    public static final String INTRO_EDAT = "Introdueixi l'edat del jugador";
    public static final String ENTER_ERROR = "El valor introduït no es un nombre sencer";
    public static final String MENU_INTRO_ENTER = "Introdueixi un valor enter per l'opció";
    public static final String MENU_OPTIONS = "1) Puntuar ronda\n2) Mostrar tauler\n0) Sortir\n";
    public static final String MENU_ERROR = "No s'ha introduit un número correcte de jugadors";
    public static final String MENU_INVALID_OPTION = "No s'ha introduït una opció vàlida";
    public static final String INTRODUEIX_NUMERO_DE_RONDA = "Quina ronda vol puntuar?";
    public static final String RONDA_NO_EXISTEIX = "La ronda introduïda no existeix. Introdueixi un valor entre 1 i 10";
    public static final String NUMERO_PUNTS_NO_EXISTEIX = "Els punts han de ser un valor entre 0 i 10";
    public static final String GREEN_COLOR = "\033[32m";
    public static final String RESET_COLOR = "\u001B[0m";

    Scanner entrada = new Scanner(System.in);

    // MÉTODE PRINCIPAL
    public static void main(String[] args) {
        /* INSTANCIEM LA CLASSE APPEAC4 */
        AppEAC4 bowlingApp = new AppEAC4();
        bowlingApp.start();
    }

    // AQUEST MÉTODE TRUCARÁ A LA RESTA DE MÉTODES
    public void start() {
        int playersNumber = askForInteger(INTRO_NUMERO_JUGADORS, ENTER_ERROR);
        // DECLAREM E INICIALITZEM LA MATRIU DE JUGADORS
        int pointsMatrix[][] = initializePoints(playersNumber);
        String playersData[][] = initializePlayers(playersNumber);
        // LA OMPLIM AMB ELS MÉTODES
        for (int i = 0; i < playersNumber; i++) {
            System.out.print(String.valueOf(i + 1) + "/" + String.valueOf(playersNumber) + " ");
            String name = askForString(INTRO_NOM, STRING_ERROR);
            System.out.print(String.valueOf(i + 1) + "/" + String.valueOf(playersNumber) + " ");
            String lastName = askForString(INTRO_COGNOM, STRING_ERROR);
            System.out.print(String.valueOf(i + 1) + "/" + String.valueOf(playersNumber) + " ");
            int age = askForInteger(INTRO_EDAT, ENTER_ERROR);
            insertPlayerNames(playersData, i, name, lastName, age);
        }

        /* SI S'ENTREN 0 JUGADORS EL PROGRAMA ACABA */
        if (playersNumber == 0 || playersNumber < 0) {
            showError(MENU_ERROR);
            return;
            // SINÓ CONTINUA I MOSTRA LES OPCIONS DEL MENÚ
        } else {
            showMenu(MENU_OPTIONS);
        }
        scoreRound(pointsMatrix, playersData, playersNumber);

        System.out.println();

        entrada.close();
    }

    public int[][] initializePoints(int playersNumber) {

        if (playersNumber <= 0) {
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

        if (playersNumber <= 0) {
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

        if (menuText == null || menuText == "") {
            return;
        } else {
            System.out.println(GUIO_STRING + "\n" + TITOL_MENU_STRING + "\n" + GUIO_STRING);
            System.out.println(menuText);
            System.out.println(MENU_INTRO_ENTER);
        }
    }

    // AQUEST MÉTODE GESTIONA EL MENÚ EN FUNCIÓ DE LA OPCIÓ ESCOLLIDA
    public void scoreRound(int pointsMatrix[][], String playersData[][], int playersNumber) {

        int opcioEscollida = entrada.nextInt();

        while (opcioEscollida != 0) {
            switch (opcioEscollida) {
                case 1:
                    int round = askForInteger(INTRODUEIX_NUMERO_DE_RONDA, ENTER_ERROR);
                    if (round == 0) {
                        return;
                    }
                    for (int i = 0; i < playersNumber; i++) {
                        String puntsMsg = "Introdueix punts per " + playersData[i][0] + " " + playersData[i][1];
                        int points = askForInteger(puntsMsg, ENTER_ERROR);
                        setRoundPoints(pointsMatrix, i, round, points);
                    }
                    showMenu(MENU_OPTIONS);
                    opcioEscollida = entrada.nextInt();
                    break;
                case 2:
                    showRounds(playersData, pointsMatrix);
                    System.out.println();
                    showMenu(MENU_OPTIONS);
                    opcioEscollida = entrada.nextInt();
                    break;
                case 0:
                    break;
                default:
                    showMenu(MENU_OPTIONS);
                    opcioEscollida = entrada.nextInt();
                    break;
            }
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

    /*
     * FUNCIO DEMANA UN STRING(l'utilitzrem per atrapar nom y cognomms) A L'USUARI
     */
    public String askForString(String message, String errorMessage) {
        System.out.println(message);
        String inputString = entrada.nextLine();
        while (inputString.trim().isEmpty()) {
            System.out.println(errorMessage);
            System.out.println(message);
            inputString = entrada.nextLine();
        }

        return inputString;
    }

    /*
     * FUNCIO DEMANA UN ENTER A L'USUARI (l'utilitzarem per atrapar la edat i les
     * puntuacions a cada ronda
     */
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

    /* MÉTODE PER SETEJAR LA MATRIU DE JUGADORS */
    public void insertPlayerNames(String[][] playersData, int rowNumber, String name, String lastName, int age) {
        if (playersData == null ||
                rowNumber < 0 ||
                rowNumber > playersData.length ||
                name == null ||
                name == "" ||
                lastName == null ||
                lastName == "" ||
                age < 0) {
            return;
        } else {
            playersData[rowNumber][0] = name;
            playersData[rowNumber][1] = lastName;
            playersData[rowNumber][2] = String.valueOf(age);
        }
    }

    /* MÉTODE PER SETEJAR LA MATRIU DE PUNTS */
    public void setRoundPoints(int[][] pointsMatrix, int rowIndex, int round, int points) {
        if (pointsMatrix == null ||
                rowIndex < 0 ||
                rowIndex > pointsMatrix.length - 1 ||
                round == 0 ||
                round < 0 ||
                round > RONDES ||
                points > MAX_POINTS ||
                points < 0) {
            return;
        }

        pointsMatrix[rowIndex][round - 1] = points;

    }

    /* MÉTODE PER PARSEJAR LES DUES MATRIUS DE FORMA TABULADA */
    public void showRounds(String[][] playersData, int[][] pointsMatrix) {

        if (playersData == null || pointsMatrix == null || pointsMatrix.length == 0
                || pointsMatrix.length != playersData.length
                || playersData.length == 0) {
            return;
        }

        System.out.println(GUIO_STRING);
        System.out.printf("%-20s", FULL_NAME);
        System.out.printf("%10s", AGE);
        System.out.printf("%6s", "");
        for (int i = 1; i <= RONDES; i++) {
            System.out.printf("%4s", "R" + i + " ");
        }
        System.out.println();
        System.out.println(GUIO_STRING);

        for (int i = 0; i < playersData.length; i++) {
            for (int j = 0; j < playersData[i].length - 1; j++) {
                System.out.printf(GREEN_COLOR + "%-10s", playersData[i][j] + " " + RESET_COLOR);
                if (playersData[i][j] == "") {
                    return;
                }
            }
            System.out.printf(GREEN_COLOR + "\t" + "%18s", playersData[i][2] + RESET_COLOR);
            System.out.printf("%5s", "");
            for (int k = 0; k < pointsMatrix[0].length; k++) {

                if (pointsMatrix[0][k] == -1) {
                    System.out.printf("%4s", '-');
                } else {
                    System.out.printf("%4d", pointsMatrix[i][k]);
                }
            }
            System.out.println();
        }

    }
}
