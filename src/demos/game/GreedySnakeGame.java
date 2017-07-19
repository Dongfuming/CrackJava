package demos.game;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;
//import javax.swing.JButton;
//import javax.swing.JFrame;

/**
 * Java实现贪吃蛇
 *  @author Dongfuming
 *  @Date 2016-03-27
 */
public class GreedySnakeGame {

	private static final int WIDTH = 30;
	private static final int HEIGHT = 10;
	
	private static final char WALL = '=';
	private static final char SPACE = '.';
	private static final char SNAKE_HEAD = '$';
	private static final char SNAKE_BODY = '#';
	private static final char FOOD = '@';
	
	public static final int UP_DIRECTION = 1;  
	public static final int DOWN_DIRECTION = -1;  
	public static final int LEFT_DIRECTION = 2;  
	public static final int RIGHT_DIRECTION = -2;  

	
	private char[][] background = new char[HEIGHT][WIDTH];
	private LinkedList<Point> snake = new LinkedList<Point>();
	private Point food;
	private int currentDirection = RIGHT_DIRECTION; 
	
	public void initBackground() {
		for (int i = 0; i < background.length; i++) {
			for (int j = 0; j < background[i].length; j++) {
				if (i == 0 || i == background.length-1 ) { // || j == 0 || j == background[i].length-1
					background[i][j] = WALL;
				} else {
					background[i][j] = SPACE;
				}
			}
		}
	}
	
	public void showBackground() {
		for (int i = 0; i < background.length; i++) {
			for (int j = 0; j < background[i].length; j++) {
				System.out.print(background[i][j]);
			}
			System.out.println();
		}
	}
	
	public void initSnake() {
		int centerX = WIDTH / 2;
		int centerY = HEIGHT / 2;
		snake.add(new Point(centerX+1, centerY));
		snake.add(new Point(centerX, centerY));
		snake.add(new Point(centerX-1, centerY));
	}
	
	public Point headOfSnake() {
		return snake.getLast();
	}
	
	public Point tailOfSnake() {
		return snake.getFirst();
	}
	
	public void showSnake() {
		for (int i = 1; i < snake.size(); i++) {
			Point body = snake.get(i);
			background[body.y][body.x] = SNAKE_BODY;
		}
		Point head = snake.getFirst();
		background[head.y][head.x] = SNAKE_HEAD;
	}
	
	public void createFood() {
		Random random = new Random();
		while (true) {
			int x = random.nextInt(WIDTH);
			int y = random.nextInt(HEIGHT);
			if (background[y][x] != WALL) {
				food = new Point(x, y);
				break;
			}
		}
	}
	
	public void showFood() {
		background[food.y][food.x] = FOOD;
	}
	
	public void refreshUI() {
		initBackground();
		showSnake();
		showFood();
		showBackground();
	}
	
	public void changeSnakeDirection(int newDirection) {
		if (newDirection + currentDirection != 0) {
			currentDirection = newDirection;
		}
	}
	
	public void snakeMove() {
		Point head = snake.getFirst();
		switch (currentDirection) {
		case UP_DIRECTION:
			snake.addFirst(new Point(head.x, head.y-1));
			break;
		case DOWN_DIRECTION:
			snake.addFirst(new Point(head.x, head.y+1));
			break;
		case LEFT_DIRECTION:
			if (head.x == 0) { 
				snake.addFirst(new Point(WIDTH-1, head.y)); 
			} else {
				snake.addFirst(new Point(head.x-1, head.y));
			}
			break;
		case RIGHT_DIRECTION:
			if (head.x == WIDTH - 1) {
				snake.addFirst(new Point(0, head.y));
			} else {
				snake.addFirst(new Point(head.x+1, head.y));
			}
			break;
		default:
			break;
		}
		if (hasEatFood()) {
			createFood();
		} else {
			snake.removeLast();
		}
		boolean isGameOver = checkGameOver();
		refreshUI();
		if (isGameOver) {
			System.out.println("game over");
			System.exit(0);
		}
	}
	
	private boolean hasEatFood() {
		Point head = snake.getFirst();
		if (head.equals(food)) {
			return true;
		}
		return false;
	}
	
	private boolean checkGameOver() {
		Point head = snake.getFirst();
		// 撞墙了
		if (background[head.y][head.x] == WALL) {
			return true;
		}
		// 吃到自己尾巴了
		for (int i = 1; i < snake.size(); i++) {
			Point body = snake.get(i);
			if (head.equals(body)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		/*
		GreedySnakeGame game = new GreedySnakeGame();
		game.initBackground();
		game.initSnake();
		game.showSnake();
		game.createFood();
		game.showFood();
		game.showBackground();
		
		JFrame frame = new JFrame("请使用键盘操作");
		frame.add(new Button("向上"), BorderLayout.NORTH);
		frame.add(new Button("向下"), BorderLayout.SOUTH);
		frame.add(new Button("下左"), BorderLayout.WEST);
		frame.add(new Button("向右"), BorderLayout.EAST);
		
		JButton button = new JButton("点我开始");
		frame.add(button);
		Dimension size =  Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(size.width-100-300, size.height-100-300, 300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					game.changeSnakeDirection(UP_DIRECTION);
					break;
				case KeyEvent.VK_DOWN:
					game.changeSnakeDirection(DOWN_DIRECTION);
					break;
				case KeyEvent.VK_LEFT:
					game.changeSnakeDirection(LEFT_DIRECTION);
					break;
				case KeyEvent.VK_RIGHT:
					game.changeSnakeDirection(RIGHT_DIRECTION);
					break;
				default:
					break;
				}
				game.snakeMove();
			}
		});
		 */
	}
}
