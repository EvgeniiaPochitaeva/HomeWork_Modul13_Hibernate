package general.planet;

public interface IPlanetDaoService {
    String createNewPlanet(String planet_id, String name);
    String getByIdPlanet(String id);
    void setNamePlanet(String id, String name);
    void deleteByIdPlanet(String id);
}