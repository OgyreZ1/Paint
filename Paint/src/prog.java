import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class prog {
    public static void main(String[] args) {
        okno okn = new okno();
    }
}
class okno extends JFrame {
    public okno() {
        setBounds(100,100, 820, 690);
        setTitle("НЕДОПЭИНТ");
        panel pane = new panel();
        Container cont = getContentPane();
        cont.add(pane);
        setVisible(true);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class panel extends JPanel{
    int tCol = 0;
    int  kX, kY, nX, nY;
    int mX, mY, sX, sY;
    Color[] masColor = new Color[8];
    Boolean flag = true;
    Boolean rect = false;
    Boolean oval = false;
    Boolean rect2 = false;
    Boolean line = false;
    Boolean tr = false;
    Boolean flagP = false;
    Boolean rectP = false;
    Boolean ovalP = false;
    Boolean rect2P = false;
    Boolean lineP = false;
    Boolean trP = false;
    public panel(){
        addMouseListener(new myMouse1());
        addMouseMotionListener(new myMouse2());
    }
    public void paintComponent(Graphics gr){
        masColor[0] = Color.BLACK;
        masColor[1] = Color.BLUE;
        masColor[2] = Color.DARK_GRAY;
        masColor[3] = Color.MAGENTA;
        masColor[4] = Color.YELLOW;
        masColor[5] = Color.ORANGE;
        masColor[6] = Color.PINK;
        masColor[7] = new Color(238, 238, 238);
        gr.setColor(masColor[7]);
        gr.fillRect(700, 50, 120, 700);
        gr.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) gr;            //горизонтальная линия
        g2.setStroke(new BasicStroke(10.0f));
        g2.drawLine(0,55,800,55);
        gr.setColor(Color.BLACK);
        gr.fillRect(715, 80, 60, 50); //иконка прямоугольника
        gr.fillRoundRect(715, 280, 60, 50, 10,10); //иконка закруглённого прямоугольника
        gr.fillOval(715, 170, 60, 60); //иконка круга
        gr.fillRect(mX, mY, 3, 3);
        Graphics2D g5 = (Graphics2D) gr;            //иконка линии
        g2.setStroke(new BasicStroke(10.0f));
        g2.drawLine(725,630,780,500);
        gr.fillPolygon(new Polygon(new int[]{720, 750, 780}, new int[]{430,380,430}, 3) ); //иконка треугольника
        for (int i = 150; i < 550; i+=100) {             //горизонтальные маленькие линии
            gr.setColor(Color.BLACK);
            Graphics2D g4 = (Graphics2D) gr;
            g2.setStroke(new BasicStroke(10.0f));
            g2.drawLine(700, i, 800, i);
        }
        for (int i = 0; i <= 7; i++) {            //палитра + ластик
            gr.setColor(masColor[i]);
            gr.fillRect(100*i, 0, 100, 50);
        }
        if ((flag == true)){        //кисть
            gr.setColor(masColor[tCol]);
            gr.fillRect(mX, mY, 3, 3);
            if(tCol == 7){
                gr.fillRect(mX, mY, 50, 50); //ластик
            }
        }
        if(ovalP == true){    //рисует овал
            gr.setColor(masColor[tCol]);
            gr.fillOval(Math.min(sX, kX), Math.min(sY, kY), Math.abs(kX - sX), Math.abs(kY - sY));
            ovalP = false;
        }else
        if(rectP == true){    //рисует прямоугольник1
            gr.setColor(masColor[tCol]);
            gr.fillRect(Math.min(sX, kX), Math.min(sY, kY), Math.abs(kX - sX), Math.abs(kY - sY));
            rectP = false;
        }else
        if(rect2P == true){    //рисует прямоугольник2
            gr.setColor(masColor[tCol]);
            gr.fillRoundRect(Math.min(sX, kX), Math.min(sY, kY), Math.abs(kX - sX), Math.abs(kY - sY), 20, 20);
            rect2P = false;
        }else
        if(trP == true){    //рисует треугольник
            gr.setColor(masColor[tCol]);
            gr.fillPolygon(
                    new Polygon(new int[] { sX, kX - (kX - sX) / 2, kX }, new int[] { kY, sY, kY }, 3));
            trP = false;
        }else
        if(lineP == true){    //рисует линию
            gr.setColor(masColor[tCol]);
            Graphics2D g4 = (Graphics2D) gr;
            g4.setStroke(new BasicStroke(10.0f));
            g4.drawLine(sX, sY, kX, kY);
            trP = false;
        }
        gr.setColor(masColor[tCol]);
        gr.fillRect(715, 80, 60, 50); //иконка прямоугольника
        gr.fillRoundRect(715, 280, 60, 50, 10,10); //иконка закруглённого прямоугольника
        gr.fillOval(715, 170, 60, 60); //иконка круга

        Graphics2D g6 = (Graphics2D) gr;            //иконка линии
        g2.setStroke(new BasicStroke(10.0f));
        g2.drawLine(725,630,780,500);
        gr.fillPolygon(new Polygon(new int[]{720, 750, 780}, new int[]{430,380,430}, 3) ); //иконка треугольника
        gr.setColor(Color.BLACK);                         //вертикальная линия
        Graphics2D g1 = (Graphics2D) gr;
        g2.setStroke(new BasicStroke(10.0f));
        g2.drawLine(700,0,700,650);
    }
    public class myMouse1 implements MouseListener {
        public void mouseClicked(MouseEvent e) { //щелчок
            int tX = e.getX();
            int tY = e.getY();
        }
        public void mousePressed(MouseEvent e) { //нажатие
            int tX = e.getX();
            int tY = e.getY();
            sX = tX;
            sY = tY;
            int btn = e.getButton();
            if((tX > 0) && (tX < 800) && (tY > 0) && (tY < 50)){ //КИСТЬ
                if (btn == 1){
                    tCol = tX / 100;

                }
            }
            if((tX > 700) && (tX < 820) && (tY > 50) && (tY < 150)){ //ПРЯМОУГОЛЬНИК1
                if(btn == 1){
                    rect = true;
                    flag = false;
                    oval = false;
                    rect2 = false;
                    line = false;
                    tr = false;}
            }
            if((tX > 700) && (tX < 820) && (tY > 150) && (tY < 250)){ //ОВАЛ
                if(btn == 1){
                    rect = false;
                    flag = false;
                    rect2 = false;
                    line = false;
                    tr = false;
                    oval = true;
                }
            }if((tX > 700) && (tX < 820) && (tY > 250) && (tY < 350)){ //ПРЯМОУГОЛЬНИК2
                if(btn == 1){
                    rect = false;
                    flag = false;
                    line = false;
                    tr = false;
                    oval = false;
                    rect2 = true;
                }
            }if((tX > 700) && (tX < 820) && (tY > 350) && (tY < 450)){ //ТРЕУГОЛЬНИК
                if(btn == 1){
                    rect = false;
                    flag = false;
                    rect2 = false;
                    line = false;
                    oval = false;}
                tr = true;
            }
            if((tX > 700) && (tX < 820) && (tY > 450) && (tY < 700)){ //ЛИИНИЯ
                if(btn == 1){
                    rect = false;
                    flag = false;
                    tr = false;
                    oval = false;
                    rect2 = false;
                    line = true;
                }
            }
            if ((tX > 700) && (tX < 820) && (tY > 50) && (tY < 700)){ //СБРОС
                if(btn == 3){
                    rect2 = false;
                    rect = false;
                    oval = false;
                    tr = false;
                    line = false;
                    flag = true;
                    lineP = false;
                }
            }
        }
        public void mouseReleased(MouseEvent e) { //при опускании кноопки мыши
            nX = kX-(kX-sX)/2;
            nY = kY-(kY-sY)/2;
            int tX = e.getX();
            int tY = e.getY();
            if((tY > 50) && (tX < 700) && (line == true) ) {
                kX = tX;
                kY = tY;
                lineP = true;
                repaint();
            }else
            if((tY > 50) && (tX < 700) && (rect == true)) {
                kX = tX;
                kY = tY;
                rectP = true;
                repaint();
            }else
            if((tY > 50) && (tX < 700) && (oval == true) ) {
                kX = tX;
                kY = tY;
                ovalP = true;
                repaint();
            }else
            if((tY > 50) && (tX < 700) && (rect2 == true) ) {
                kX = tX;
                kY = tY;
                rect2P = true;
                repaint();
            }else
            if((tY > 50) && (tX < 700) && (tr == true) ) {
                kX = tX;
                kY = tY;
                trP = true;
                repaint();
            }
        }
        public void mouseEntered(MouseEvent e) { //появление мыши на панели окна
        }
        public void mouseExited(MouseEvent e) { //при выходе мыши за пределы панели окна
        }
    }
    public class myMouse2 implements MouseMotionListener {
        public void mouseDragged(MouseEvent e) { //при перемещении мыши с нажатой кнопокй
            int tX = e.getX();
            int tY = e.getY();
            if((tY > 50) && (tX < 700) && (flag == true)) {
                mX = tX;
                mY = tY;
                flagP = true;
                repaint();
            }
        }
        public void mouseMoved(MouseEvent e){
        }
    }
}