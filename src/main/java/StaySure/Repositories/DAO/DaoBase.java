package StaySure.Repositories.DAO;

public interface DaoBase<T> {    
    T save(T t);
    boolean delete(Long id);
    T update(T t);
    T findById(Long id);
}