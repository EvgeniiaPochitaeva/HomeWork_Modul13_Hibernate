package general.client;

import general.HibernateUtil;
import org.hibernate.Session;

public class ClientCrudService implements IClientDaoService{

    @Override
    public String getById(int id) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Client client = session.get(Client.class, id);
        session.close();
        return client.getName();
    }
    @Override
    public void setNameClient(int id, String name) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Client client = session.get(Client.class, id);
            client.setName(name);
            session.persist(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("An error occurred while setting name for client with id " + id + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    @Override
    public int createNewClient(String name)  {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        int id =-1;
        try { session.beginTransaction();
            Client client = new Client();
            client.setName(name);
            session.persist(client);
            session.getTransaction().commit();

            Client clientRead = session.createQuery("FROM Client c WHERE c.name = :name", Client.class)
                    .setParameter("name", name)
                    .uniqueResult();
            if (clientRead != null) {
                id = clientRead.getId();
            }

        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("An error occurred while creating a new client: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;

    }

    @Override
    public void deleteById(int id){
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Client client = session.get(Client.class, id);
            session.remove(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("An error occurred while deleting client with id " + id + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
