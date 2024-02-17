package general.client;

public interface IClientDaoService {
    int createNewClient(String name);
    String getById(int id);

    void setNameClient(int id, String name);
    void deleteById(int id);




}