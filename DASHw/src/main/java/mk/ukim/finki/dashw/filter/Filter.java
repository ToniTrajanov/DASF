package mk.ukim.finki.dashw.filter;

public interface Filter <T>{
    T execute(T input);
}
