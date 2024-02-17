package general.planet;

import org.hibernate.Session;
import general.HibernateUtil;

public class PlanetCrudService implements IPlanetDaoService {

    @Override
    public String createNewPlanet(String planet_id, String name) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        String planeta = "-1";
        try {
            session.beginTransaction();
            Planet planet = new Planet();
            planet.setId(planet_id);
            planet.setName(name);
            session.persist(planet);

            session.getTransaction().commit();

            Planet planetRead = session.createQuery("FROM Planet p WHERE p.name = :name", Planet.class)
                    .setParameter("name", name)
                    .uniqueResult();
            if (planetRead != null) {
                planeta = planetRead.getId() + ", " + planetRead.getName();
            }

        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("An error occurred while creating a new planet: " + e.getMessage());

        } finally {
            session.close();
        }
        return planeta;
    }

    @Override
    public String getByIdPlanet(String id) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Planet planet = session.get(Planet.class, id);
        session.close();
        return planet.getName();
    }

    @Override
    public void setNamePlanet(String id, String name) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            planet.setName(name);
            session.persist(planet);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("An error occurred while setting name for planet with id " + id + ": " + e.getMessage());

        } finally {
            session.close();
        }
    }
    @Override
    public void deleteByIdPlanet(String id) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            session.remove(planet);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("An error occurred while deleting planet with id " + id + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}