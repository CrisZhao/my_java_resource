package multithread;

/**
 * multithread calculator 单条数据处理过程
 * Created by zhaoqc on 2015/05/19.
 */
public interface IHandler<T> {
    void handle(T data);
}
