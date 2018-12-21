package wzl.com.gouwuche.core;

import wzl.com.gouwuche.bean.Result;

public interface CallBacks<T> {
    void success(T t);
    void fail(Result result);
}
