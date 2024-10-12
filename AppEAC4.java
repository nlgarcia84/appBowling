
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
    public static final int MAX_POINTS = 10;
    public static final String INTRO_NOM = "Introdueixi el nom del jugador";
    public static final String INTRO_COGNOM = "Introdueixi el cognom del jugador";
    public static final String INTRO_NOM_ERROR = "No s'ha introduït cap informació";
    public static final String INTRO_EDAT = "Introdueixi l'edat del jugador";
    public static final String INTRO_EDAT_ERROR = "El valor introduït no es un nombre sencer";
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
        System.out.println("Quants jugadors hi haurà?");
        /* SETEGEM A L'INICI DEL PROGRAMA TANT L'ARRAY DE JUGADORS COM LA DE PUNTS */
        int playersNumber = entrada.nextInt();
        int pointsMatrix[][] = initializePoints(playersNumber);
        String playersData[][] = initializePlayers(playersNumber);
        entrada.nextLine();
        /*
         * DEMANEM L'USUARI EL SEU NOM, COGNOMS, EDAT I INICIALITZEM ELS PARAMETRES AMB
         * LES DADES RECOLLIDES
         */
        for (int i = 0; i < playersNumber; i++) {
            String name = askForString(INTRO_NOM, INTRO_NOM_ERROR);
            String lastName = askForString(INTRO_COGNOM, INTRO_NOM_ERROR);
            int age = askForInteger(INTRO_EDAT, INTRO_EDAT_ERROR);
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

        if (playersNumber > 0) {
            /* INICIALITZACIO MATRIU DE PUNTUACIONS */
            int[][] pointsMatrix = new int[playersNumber][RONDES];
            for (int i = 0; i < pointsMatrix.length; i++) {
                for (int j = 0; j < pointsMatrix[i].length; j++) {
                    pointsMatrix[i][j] = -1;
                }
            }
            return pointsMatrix;
        } else {
            return null;
        }
    }

    public String[][] initializePlayers(int playersNumber) {

        if (playersNumber > 0) {
            /* INICIALITZACIO MATRIU D'USUARIS */
            String[][] playersData = new String[playersNumber][NOMBRE_DADES_PARTICIPANT];
            for (int i = 0; i < playersData.length; i++) {
                for (int j = 0; j < playersData[i].length; j++) {
                    playersData[i][j] = "";
                }
            }
            return playersData;
        } else {
            return null;
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
                case 3:

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
        String name = entrada.nextLine();
        while (name.isEmpty()) {
            System.out.println(errorMessage);
            System.out.println(message);
            name = entrada.nextLine();
        }
        return name;
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
            playersData[rowNumber][0] = name;
            playersData[rowNumber][1] = lastName;
            playersData[rowNumber][2] = String.valueOf(age);
        }
    }

    public void setRoundPoints(int[][] pointsMatrix, int rowIndex, int round, int points) {
        if (pointsMatrix == null ||
                rowIndex < 0 ||
                rowIndex > pointsMatrix.length ||
                round == 0 ||
                round < 0 ||
                round > MAX_POINTS) {
            return;
        } else {
            pointsMatrix[rowIndex][0] = round;
            pointsMatrix[rowIndex][1] = points;
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
