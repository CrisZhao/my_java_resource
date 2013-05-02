package game;

import game.Snake.extension;

/**
 * 游戏中所有的元素都是链表，定义为body类
 * 
 * @author marywangran
 */
class body extends Object {
	posision pos;
	posision direct;
	extension ext;

	public body() {
	}

	public body(posision pos, posision direct) {
		this.pos = pos;
		this.direct = direct;
	}

	boolean go_on(boolean change, posision pos) {
		if (change)
			this.pos = static_pos.go_on(this.pos, this.direct);
		else {
			return (static_pos.go_on(this.pos, this.direct).x == pos.x)
					&& (static_pos.go_on(this.pos, this.direct).y == pos.y);
		}
		return true;
	}

	void set_extension(extension ext) {
		this.ext = ext;
	}

	extension get_extension() {
		return this.ext;
	}
	// void *private;
}