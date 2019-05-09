package dao;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;

public interface Dao<T> {
    T read();
    void write(T obj);
}
