import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch extends JFrame implements ActionListener {
    private JLabel timeLabel;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;
    private Timer timer;
    private int elapsedTime;
    private boolean isRunning;

    /**
     * Stopwatchクラスのコンストラクタ
     * GUI要素を初期化し、イベントリスナーを設定
     */

    public Stopwatch() {
        setTitle("ストップウォッチ");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        timeLabel = new JLabel("00:00:00");
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        add(timeLabel);

        startButton = new JButton("スタート");
        startButton.addActionListener(this);
        add(startButton);

        stopButton = new JButton("ストップ");
        stopButton.addActionListener(this);
        add(stopButton);

        resetButton = new JButton("リセット");
        resetButton.addActionListener(this);
        add(resetButton);

        timer = new Timer(1000, e -> {
            elapsedTime += 1000;
            updateTime(elapsedTime);
        });
    }

    /**
     * 経過時間を表示するメソッド
     * 経過時間（ミリ秒）
     */
    private void updateTime(int elapsedTime) {
        int hours = elapsedTime / 3600000;
        int minutes = (elapsedTime / 60000) % 60;
        int seconds = (elapsedTime / 1000) % 60;
        timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    /**
     * ActionListenerの実装
     * ボタンがクリックされたときの動作を定義
     * @param e ActionEventオブジェクト
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (!isRunning) {
                timer.start();
                isRunning = true;
            }
        } else if (e.getSource() == stopButton) {
            if (isRunning) {
                timer.stop();
                isRunning = false;
            }
        } else if (e.getSource() == resetButton) {
            timer.stop();
            isRunning = false;
            elapsedTime = 0;
            updateTime(elapsedTime);
        }
    }
}
