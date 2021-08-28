package ru.job4j.concurrent;

/**
 * 3. Прерывание нити [#1019]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.1. Threads
 * 1. Создайте класс ru.job4j.concurrent.ConsoleProgress. Этот класс будет использован
 * для вывода процесса загрузки в консоль.
 * 2. Этот класс должен реализовывать интерфейс java.lang.Runnable. *
 * 3. Внутри метода run нужно добавить цикл  с проверкой флага.
 * Внутри цикла - сделать задержку в 500 мс.
 * 4. В тело цикла добавьте вывод в консоль.
 * Loading ... |.
 * Последний символ должен меняться: - \ | /.
 * Эти символы создадут эффект крутящегося шара.
 * 5. Добавьте в этот класс метод main с демонстрацией работы этого класса.
 */
public class ConsoleProgress implements Runnable {

  /*public ConsoleProgress() {
        run();
    }*/

    @Override
    public void run() {
        String[] process = {"-", "\\", "|", "/"};
        //Внутри метода run нужно добавить цикл  с проверкой флага
        //Внутри цикла - сделать задержку в 500 мс.
        while (!Thread.currentThread().isInterrupted()) {
            try {
                for (int i = 0; i < process.length; i++) {
                    Thread.sleep(500);
                    System.out.print("\rLoading : " + process[i]);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000); /* симулируем выполнение параллельной задачи в течение 1 секунды. */
        progress.interrupt();
    }
}



