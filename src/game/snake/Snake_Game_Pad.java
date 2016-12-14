package game.snake;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import com.google.common.collect.Lists;

/**
 * 完全由链表实现的贪吃蛇游戏类，其本质就是处理body链表
 * 
 * @author marywangran
 */
class Snake_Game_Pad {
	LinkedList<body> snake_body;
	LinkedList<body> change_body;
	LinkedList<body> kill_body;
	LinkedList<body> food_body;
	LinkedList<body> free_body;
	LinkedList<posision> replace_body;
	int curr_status;
	int curr_level;
	int curr_score;
	int width, height;

	public Snake_Game_Pad() {
		snake_body = Lists.newLinkedList();
		change_body = Lists.newLinkedList();
		kill_body = Lists.newLinkedList();
		food_body = Lists.newLinkedList();
		free_body = Lists.newLinkedList();
		replace_body = Lists.newLinkedList();
	}

	/**
	 * 贪吃蛇游戏初始化
	 * 
	 * @param width
	 *            横向元素数量
	 * @param height
	 *            纵向元素数量
	 * @param init_length
	 *            初始蛇长度
	 */
	public void init(int width, int height, int init_length) {
		this.width = width;
		this.height = height;
		int i = 0, j = 0;
		for (i = 0; i < this.width; i++) {
			for (j = 0; j < this.height; j++) {

				free_body.add(new body(static_pos.pos[j][i], static_pos.HALT));
			}
		}
		for (i = 0; i < this.width; i++) {
			body kb1 = free_body.get(0);
			body kb2 = free_body.get(free_body.size() - 1);
			free_body.remove(kb1);
			kill_body.add(kb1);
			free_body.remove(kb2);
			kill_body.add(kb2);
		}
		for (i = 0; i < this.height - 2; i++) {
			body kb1 = free_body.get(i * (this.width - 2));
			body kb2 = free_body.get(i * (this.width - 2) + this.width - 1);
			free_body.remove(kb1);
			kill_body.add(kb1);
			free_body.remove(kb2);
			kill_body.add(kb2);
		}
		for (i = 0; i < init_length - 1; i++) {
			body sb = free_body.get(6);
			sb.direct = static_pos.RIGHT;
			free_body.remove(sb);
			snake_body.add(0, sb);
			kill_body.add(0, sb);
		}
		body sb = free_body.get(6);
		sb.direct = static_pos.RIGHT;
		free_body.remove(sb);
		snake_body.add(0, sb);
		random_gen_food();
	}

	public void reset() {
		snake_body.clear();
		change_body.clear();
		kill_body.clear();
		food_body.clear();
		free_body.clear();
		replace_body.clear();
	}

	int get_curr_score() {
		return this.curr_score;
	}

	int get_curr_level() {
		return this.curr_level;
	}

	/**
	 * 贪吃蛇主处理函数
	 * 
	 * @return 需要重绘的free_body元素的链表
	 */
	LinkedList<posision> game_handler() {
		body head = snake_body.get(0);
		body tail = snake_body.get(snake_body.size() - 1);
		posision tail_pos = tail.pos;
		body replace_free;
		boolean need_gen = false;
		Iterator<body> snake_iter = snake_body.iterator();
		replace_body.clear();
		Iterator<body> food_iter = food_body.iterator();
		while (food_iter.hasNext()) {
			body fb = food_iter.next();
			Snake.extension fext = fb.get_extension();
			// 食物超时时间到了，食物消失。
			if ((System.currentTimeMillis() - fext.star_time()) > fext
					.get_timeout()) {
				food_iter.remove();
				free_body.add(fb);
				replace_body.add(fb.pos);
				need_gen = true;
				continue;
			}
			// 贪吃蛇吃食物
			if (head.go_on(false, fb.pos)) {
				food_iter.remove();
				fb.direct = head.direct;
				snake_body.add(0, fb);
				kill_body.add(head);
				eat_food();
				head = snake_body.get(0);
				random_gen_food();
			}
		}
		if (need_gen) { // 食物由于超时消失了，重新生成一个
			this.random_gen_food();
		}
		while (snake_iter.hasNext()) {
			body sb = snake_iter.next();
			Iterator<body> change_iter = change_body.iterator();
			while (change_iter.hasNext()) {
				body cb = change_iter.next();
				if ((sb.pos.x == cb.pos.x) && (sb.pos.y == cb.pos.y)) {
					sb.direct = cb.direct;
					if (!snake_iter.hasNext()) {
						change_body.remove(cb);
					}
					break;
				}
			}
			sb.go_on(true, null);
		}
		Iterator<body> kill_iter = kill_body.iterator();
		while (kill_iter.hasNext()) {
			body kb = kill_iter.next();
			if ((kb.pos.x == head.pos.x) && (kb.pos.y == head.pos.y)) {
				this.curr_status = 1;
				/**
				 * 死了的逻辑是什么，这是一个问题。
				 */
				// System.exit(1);
			}
		}
		replace_free = free_body.get(head.pos.y * this.width + head.pos.x);
		replace_free.pos = tail_pos;
		replace_body.add(tail_pos);
		return replace_body;
	}

	void key_handler(posision direct) {
		body pb = new body(snake_body.get(0).pos, direct);
		snake_body.get(0).direct = pb.direct;
		change_body.add(pb);
	}

	/**
	 * 该方法没有完全实现，现有的实现很丑陋。TODO 1.将分值设定参数化，策略化，和当前级别联动 2.将超时时间和当前级别以及最近距离联动
	 */
	void random_gen_food() {
		body fb;
		Random random = new java.util.Random();
//		Random big_random = new java.util.Random();
//		int big_score = 0;
		int size = free_body.size();
		fb = free_body.get(random.nextInt(size));
		free_body.remove(fb);
		food_extension fext = new food_extension();
		fext.set_timeout(2000);
		fext.set_score(10);
		fb.set_extension(fext);
		food_body.add(fb);
	}

	/**
	 * 该方法TODO 实现设置障碍物，贪吃蛇除了不能触动自身以及边墙之外，也不能触动这些障碍 实现很简单，只需要将障碍添加到kill_body链表中即可
	 */
	void random_gen_fence() {
	}

	void eat_food() {
		// 实现加分等操作
	}
}