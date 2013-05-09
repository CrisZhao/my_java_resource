package game;

import game.Snake.extension;

/**
 * 食物的属性
 * @author marywangran
 *
 */
class food_extension implements extension {
    long time_out;
    int score;
    long start;
    public void set_timeout(int timeout) {
        this.time_out = timeout*1000;
        this.start = System.currentTimeMillis();
    }
    public long get_timeout() {
        return this.time_out;
    }
    public long star_time(){
        return this.start;
    }
    public void set_score(int score) {
        this.score = score;
    }
    public int get_score() {
        return this.score;
    }
}