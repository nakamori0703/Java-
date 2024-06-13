public class Main extends Object{
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            // ストップウォッチを起動
            Stopwatch stopwatch = new Stopwatch();
            stopwatch.setVisible(true);

            // タイマーを起動
            TimerApp timerApp = new TimerApp();
            timerApp.setVisible(true);
        });
    }
}