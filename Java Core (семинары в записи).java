import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BackupUtility {

    public static void createBackup(String sourceDir) {
        File sourceDirectory = new File(sourceDir);
        File backupDirectory = new File("./backup");

        // Создание директории для резервных копий
        if (!backupDirectory.exists()) {
            backupDirectory.mkdir();
        }

        // Получение всех файлов в директории
        File[] files = sourceDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try {
                        Files.copy(file.toPath(), Path.of(backupDirectory.getPath(), file.getName()));
                        System.out.println("Скопирован: " + file.getName());
                    } catch (IOException e) {
                        System.err.println("Ошибка при копировании файла: " + file.getName() + " - " + e.getMessage());
                    }
                }
            }
        } else {
            System.out.println("Директория пуста или не найдена.");
        }
    }
}
import java.io.FileOutputStream;
import java.io.IOException;

public class TicTacToeUtility {

    public static void saveGameState(int[] gameState, String fileName) {
        if (gameState.length != 9) {
            throw new IllegalArgumentException("Должно быть ровно 9 значений для поля 3x3.");
        }

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            // Сжимаем 9 значений в 3 байта
            for (int i = 0; i < 3; i++) {
                int byteValue = (gameState[i * 3] << 6) | (gameState[i * 3 + 1] << 3) | (gameState[i * 3 + 2]);
                fos.write(byteValue);
            }
            System.out.println("Состояние игры сохранено в файл: " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
public class Main {

    public static void main(String[] args) {
        // Создание резервной копии файлов
        BackupUtility.createBackup("path/to/your/source/directory");

        // Сохранение состояния игры
        int[] gameState = {0, 1, 2, 1, 0, 0, 2, 3, 0}; // Пример состояния
        TicTacToeUtility.saveGameState(gameState, "game_state.dat");
    }
}