package main.database;

public interface IDatabaseConnection<T> {
    T getConnection() throws Exception;
}
