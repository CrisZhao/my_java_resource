package game;

/**
 * 静态数据，保存所有的位置和方向信息 这原本是可以定义到各个使用类内部以内部类实现的
 * 
 * @author marywangran
 */
class static_pos {
	public static posision LEFT = new posision(-1, 0);
	public static posision RIGHT = new posision(1, 0);
	public static posision UP = new posision(0, -1);
	public static posision DOWN = new posision(0, 1);
	public static posision HALT = new posision(0, 0);
	public static posision pos[][];

	public static void setpos(int width, int height) {
		int i = 0, j = 0;
//		int num = width * height;
		pos = new posision[width][height];
		for (i = 0; i < width; i++) {
			for (j = 0; j < height; j++) {
				pos[i][j] = new posision(i, j);
			}
		}
	}

	public static posision go_on(posision curr, posision direct) {
		return pos[curr.x + direct.x][curr.y + direct.y];
	}
}
