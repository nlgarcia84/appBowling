
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
    public static final String MENU_INTRO_ENTER = "Introdueixi un valor enter per l'opció";
    public static final String MENU_OPTIONS = "1) Puntuar ronda\n2) Mostrar tauler\n0) Sortir\n";
    public static final String MENU_ERROR = "No s'ha introduit un número correcte de jugadors";
    public static final String MENU_INVALID_OPTION = "No s'ha introduït una opció vàlida";
    public static final String NUMERO_DE_RONDA = "Quina ronda vol puntuar?";
    public static final String INTRODUEIXI_PUNTS_DE = "Introdueixi els punts per ";
    public static final String RONDA_NO_EXISTEIX = "La ronda introduïda no existeix. Introdueixi un valor entre 1 i 10";
    public static final String NUMERO_PUNTS_NO_EXISTEIX = "Els punts han de ser un valor entre 0 i 10";

    Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        /* INSTANCIEM LA CLASSE APPEAC4 */
        AppEAC4 bowlingApp = new AppEAC4();
        bowlingApp.start();
    }

    private int playersNumber;
    private String[][] playersData;
    private int[][] pointsMatrix;

    public void start() {
        playersNumber = askForInteger(INTRO_NUMERO_JUGADORS, ENTER_ERROR);
        String playersData[][] = initializePlayers(playersNumber);
        for (int i = 0; i < playersNumber; i++) {
            System.out.print(String.valueOf(i + 1) + "/" + String.valueOf(playersNumber) + " ");
            String name = askForString(INTRO_NOM, STRING_ERROR);
            System.out.print(String.valueOf(i + 1) + "/" + String.valueOf(playersNumber) + " ");
            String lastName = askForString(INTRO_COGNOM, STRING_ERROR);
            System.out.print(String.valueOf(i + 1) + "/" + String.valueOf(playersNumber) + " ");
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

        if (menuText == null || menuText == "") {
            return;
        } else {
            System.out.println(GUIO_STRING + "\n" + TITOL_MENU_STRING + "\n" + GUIO_STRING);
            System.out.println(menuText);
            System.out.println(MENU_INTRO_ENTER);
            int opcioEscollida = entrada.nextInt();
            switch (opcioEscollida) {
                case 1:
                    int round = askForInteger(NUMERO_DE_RONDA, ENTER_ERROR);
                    while (round > RONDES || round < 0) {
                        System.out.println(GUIO_STRING + "\n" + TITOL_ERROR_STRING + "\n" + GUIO_STRING);
                        System.out.println(RONDA_NO_EXISTEIX);
                        round = askForInteger(NUMERO_DE_RONDA, ENTER_ERROR);
                    }
                    int points = askForInteger(INTRODUEIXI_PUNTS_DE, ENTER_ERROR);
                    while (points > MAX_POINTS || points < 0) {
                        System.out.println(GUIO_STRING + "\n" + TITOL_ERROR_STRING + "\n" + GUIO_STRING);
                        System.out.println(NUMERO_PUNTS_NO_EXISTEIX);
                        points = askForInteger(INTRODUEIXI_PUNTS_DE, ENTER_ERROR);
                    }

                    break;

                case 2:
                    showRounds(playersData, pointsMatrix);
                    break;
                case 0:
                    break;
                default:
                    showError(MENU_INVALID_OPTION);
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

    /* FUNCIO DEMANA UN STRING(nom y cognomms) A L'USUARI */
    public String askForString(String message, String errorMessage) {
        System.out.println(message);
        String inputString = entrada.nextLine();
        while (true) {
            if (inputString.isEmpty() || isInteger(inputString)) {
                System.out.println(errorMessage);
                System.out.println(message);
            } else {
                break;
            }
            inputString = entrada.nextLine();
        }
        return inputString;
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true; // Si se puede convertir a entero, retorna true
        } catch (NumberFormatException e) {
            return false; // Si lanza una excepción, no es un entero
        }
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
