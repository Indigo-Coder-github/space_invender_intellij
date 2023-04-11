package org.newdawn.spaceinvaders;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {

    private FirebaseTool firebaseTool;

    private GlobalStorage globalStorage;

    private JPanel panel;
    private JButton shopbtn;
    private JButton rulebtn;
    private JButton level1btn;
    private JButton level2btn;
    private JButton level3btn;
    private JButton level4btn;
    private JButton level5btn;

    public MainWindow() {
        // 메인 윈도우 설정
        setTitle("SPACE INVANDERS");

        //현재 프레임 창 가운데 정렬 setSize를 먼저 해주어야 정상적으로 프레임이 가운데 정렬이 됨!
        setSize(800, 600);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 패널 생성 및 시작 버튼 추가
        createPanel();

        // 메인 윈도우에 패널 추가
        getContentPane().add(panel);

        // 메인 윈도우 크기 조정 및 표시
        pack();
        setVisible(true);

        firebaseTool = FirebaseTool.getInstance();
        globalStorage = GlobalStorage.getInstance();

    }

    private JButton drawButton(JButton button, String ref, int width, int height, int x, int y){
        ImageIcon buttonIcon = new ImageIcon(ref);
        Image buttonimg = buttonIcon.getImage();
        Image buttonimgchange = buttonimg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon buttonchange = new ImageIcon(buttonimgchange);
        button = new JButton(buttonchange);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setSize(width, height);
        button.setBounds(x, y, width, height);

        return button;
    }

    public void btnMouseListener(JButton button){
        button.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (button.getName() == "shopbtn"){
//                    Thread gameThread = new Thread(new Runnable(){
//
//                    });
                }
                else if (button.getName() == "rulebtn"){

                }
                else{
                    // 상점이 아닌 버튼 및 레이아웃 관련 처리
                    button.setVisible(true);
                    setLayout(null);
                    firebaseTool.GetUserBestScore(globalStorage.getUserID());
                    JOptionPane.showMessageDialog(null, globalStorage.getUserID() + " 님 최고점수 : " + globalStorage.getUserBestScore());
                    // 게임 루프를 실행하는 스레드 생성
                    Thread gameThread = new Thread(new Runnable() {
                        public void run() {
                            Game g = new Game();
                            g.gameLoop();
                        }
                    });
                    gameThread.start();
                }
            }
        });
    }

    private void createPanel() {
        // 패널 생성
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("src/image/background.png"); // 이미지 파일
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null); // 레이아웃 매니저를 사용하지 않음
        panel.setPreferredSize(new Dimension(800, 600));

        // 시작 버튼 생성
        shopbtn = drawButton(shopbtn, "src/image/start.png", 100, 50, 270, 200);
        this.btnMouseListener(shopbtn);
        //설명 버튼 생성
        rulebtn = drawButton(rulebtn, "src/image/rule.png", 100, 50, 400, 200);
        this.btnMouseListener(rulebtn);
        //level1 버튼 생성
        level1btn = drawButton(level1btn, "src/image/level1.png", 100, 50, 120, 400);
        this.btnMouseListener(level1btn);
        //level2 버튼 생성
        level2btn = drawButton(level2btn, "src/image/level2.png", 100, 50, 230, 400);
        this.btnMouseListener(level2btn);
        //level3 버튼 생성
        level3btn = drawButton(level3btn, "src/image/level3.png", 100, 50, 340, 400);
        this.btnMouseListener(level3btn);
        //level4 버튼 생성
        level4btn = drawButton(level4btn, "src/image/level4.png", 100, 50, 450, 400);
        this.btnMouseListener(level4btn);
        //level5 버튼 생성
        level5btn = drawButton(level5btn, "src/image/level5.png", 100, 50, 560, 400);
        this.btnMouseListener(level5btn);

        // 패널에 시작 버튼 추가
        panel.add(shopbtn);
        panel.add(rulebtn);
        panel.add(level1btn);
        panel.add(level2btn);
        panel.add(level3btn);
        panel.add(level4btn);
        panel.add(level5btn);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login login = new Login();
            }
        });
    }
}
