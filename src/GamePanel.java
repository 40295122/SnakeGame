import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 500, HEIGHT = 500;

	public Thread thread;
	private boolean running;
	private boolean right = true, left = false, up = false, down = false;
	private BodyPart b;
	private ArrayList<BodyPart> snake;
	
	private Apple apple;
	private ArrayList<Apple> apples;
	
	private Random r;
	
	private int xCoOr = 10, yCoOr = 10, size = 5;
	private int ticks = 0;

	public GamePanel() {

		setFocusable(true);

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);

		snake = new ArrayList<BodyPart>();
		apples = new ArrayList<Apple>();
		
		r = new Random();
		
		start();

	}

	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void tick() {
		if (snake.size() == 0) {
			b = new BodyPart(xCoOr, yCoOr, 10);
			snake.add(b);
		}
		ticks++;

		if (ticks > 550000) {
			if (right)
				xCoOr++;
			if (left)
				xCoOr--;
			if (up)
				yCoOr--;
			if (down)
				yCoOr++;

			ticks = 0;
			b = new BodyPart(xCoOr, yCoOr, 10);
			snake.add(b);

			if (snake.size() > size) {
				snake.remove(0);
			}

		}
		if (apples.size() == 0) {
			int xCoOr = r.nextInt(49);
			int yCoOr = r.nextInt(49);
			
			apple = new Apple(xCoOr, yCoOr, 10);
			apples.add(apple);
			
		}
		
		for (int i = 0; i < apples.size(); i++) {
			if(xCoOr == apples.get(i).getxCoOr() && yCoOr == apples.get(i).getyCoOr()) {
				size++;
				apples.remove(i);
				i++;
			}
		}
		
		//body part collision
		for (int i = 0; i < snake.size(); i++) {
			if(xCoOr == snake.get(i).getxCoOr() && yCoOr == snake.get(i).getyCoOr()) {
				if (i != snake.size() - 1) {
					System.out.println("Game Over");
					stop();
				}
			}
		}
		
		//board collision
		if (xCoOr < 0 || xCoOr >49 || yCoOr < 0 || yCoOr > 49 ) {
			System.out.println("Game Over");
			stop();
		}
		
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		for (int i = 0; i < WIDTH / 10; i++) {
			g.drawLine(i * 10, 0, i * 10, HEIGHT);
		}

		for (int i = 0; i < HEIGHT / 10; i++) {
			g.drawLine(0, i * 10, HEIGHT, i * 10);
		}

		for (int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g);
		}

		for (int i = 0; i < apples.size(); i++) {
			apples.get(i).draw(g);
		}
		
	}

	@Override
	public void run() {
		while (running) {
			tick();
			repaint();

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT && !left) {
			right = true;
			up = false;
			down = false;

		}
		if (key == KeyEvent.VK_LEFT && !right) {
			left = true;
			up = false;
			down = false;

		}

		if (key == KeyEvent.VK_UP && !down) {
			up = true;
			left = false;
			right = false;

		}
		if (key == KeyEvent.VK_DOWN && !up) {
			down = true;
			left = false;
			right = false;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
