import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerApp extends JFrame implements ActionListener {
    private JLabel timeLabel;
    private JTextField hourField;
    private JTextField minuteField;
    private JTextField secondField;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;
    private Timer timer;
    private int remainingTime;

    /**
     * TimerAppクラスのコンストラクタ
     * GUI要素を初期化し、イベントリスナーを設定
     */
    public TimerApp() {
        setTitle("タイマー");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        timeLabel = new JLabel("00:00:00");
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 31));
        add(timeLabel);

        hourField = new JTextField(2);
        add(hourField);
        add(new JLabel("時間"));

        minuteField = new JTextField(2);
        add(minuteField);
        add(new JLabel("分"));

        secondField = new JTextField(2);
        add(secondField);
        add(new JLabel("秒"));

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
            if (remainingTime > 0) {
                remainingTime -= 1000;
                updateTime(remainingTime);
            } else {
                timer.stop();
                JOptionPane.showMessageDialog(null, "時間終了！", "タイマー", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    /**
     * 残り時間を更新し、表示を更新
     */
    private void updateTime(int remainingTime) {
        int hours = remainingTime / 3600000;
        int minutes = (remainingTime / 60000) % 60;
        int seconds = (remainingTime / 1000) % 60;
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
            if (!timer.isRunning()) {
                int hours = Integer.parseInt(hourField.getText());
                int minutes = Integer.parseInt(minuteField.getText());
                int seconds = Integer.parseInt(secondField.getText());
                remainingTime = hours * 3600000 + minutes * 60000 + seconds * 1000;
                updateTime(remainingTime);
                timer.start();
            }
        } else if (e.getSource() == stopButton) {
            timer.stop();
        } else if (e.getSource() == resetButton) {
            timer.stop();
            hourField.setText("00");
            minuteField.setText("00");
            secondField.setText("00");
            remainingTime = 0;
            updateTime(remainingTime);
        }
    }
}
