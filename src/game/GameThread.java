package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

class GameThread extends JFrame implements KeyListener, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Snake_Game_Pad game;
	posision pos;
	JPanel mainp;

	public GameThread() {
		static_pos.setpos(200, 200);
		game = new Snake_Game_Pad();
		game.init(200, 200, 20);
		mainp = new JPanel();
		mainp.addKeyListener(this);
		mainp.setFocusable(true);
		getContentPane().setLayout(null);
		getContentPane().add(mainp);
		mainp.setBounds(0, 0, 650, 650);
		setSize(650, 650);
		setResizable(false);
		setVisible(true);
		new Thread(this).start();
	}

	public void prepaint() {
		Graphics g = mainp.getGraphics();
		this.setBackground(Color.white);
		int i, j;
		for (i = 0; i < 600; i += 3)
			for (j = 0; j < 600; j += 3) {
				g.setColor(Color.green);
				g.fillRect(i, j, 3, 3);
			}
		g.setColor(Color.blue);
	}

	public void paint(Graphics g) {
		g = mainp.getGraphics();
		Iterator<body> snake_iter = game.snake_body.iterator();
		while (snake_iter.hasNext()) {
			body sb = snake_iter.next();
			g.setColor(Color.blue);
			g.fillRect(sb.pos.x * 3, sb.pos.y * 3, 3, 3);
		}
		Iterator<body> food_iter = game.food_body.iterator();
		while (food_iter.hasNext()) {
			body fb = food_iter.next();
			g.setColor(Color.red);
			g.fillRect(fb.pos.x * 3, fb.pos.y * 3, 3, 3);
		}
	}

	public void paint_replace(LinkedList<posision> pos_body) {
		Graphics g = mainp.getGraphics();
		g.setColor(Color.green);
		Iterator<posision> pos_iter = pos_body.iterator();
		while (pos_iter.hasNext()) {
			posision pos = pos_iter.next();
			g.fillRect(pos.x * 3, pos.y * 3, 3, 3);
		}// 打印引起的悲哀
	}

	public void run() {
		LinkedList<posision> clist = null;
		prepaint();
		while (true) {
			try {
				clist = game.game_handler();
				repaint();
				paint_replace(clist);

				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();// System.exit(1);
			}
		}
	}

	public void keyPressed(KeyEvent ke) {
	}

	public void keyReleased(KeyEvent ke) {
	}

	public void keyTyped(KeyEvent key) {
		String kv = "" + key.getKeyChar();
		if (kv.equals("w"))
			game.key_handler(static_pos.UP);
		else if (kv.equals("s"))
			game.key_handler(static_pos.DOWN);
		else if (kv.equals("a"))
			game.key_handler(static_pos.LEFT);
		else if (kv.equals("d"))
			game.key_handler(static_pos.RIGHT);
	}

}