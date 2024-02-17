package general.ticket;

import general.HibernateUtil;
import general.InvalidOutputException;
import general.client.Client;
import general.planet.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.time.LocalDateTime;
import java.util.List;

public class TicketCrudService implements ITicketDaoService {

    @Override
    public void createTicket(LocalDateTime createdAt, int clientId, String planetFrom, String planetTo) throws InvalidOutputException {
        if (!planetFrom.matches("[A-Z0-9]*$") || !planetTo.matches("[A-Z0-9]*$")) {
            System.out.println("The planet is incorrect.");
        } else {
            try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                Client existingClient = session.get(Client.class, clientId);
                Planet planetFromID = session.get(Planet.class, planetFrom);
                Planet planetToID = session.get(Planet.class, planetTo);
                if (existingClient == null) {
                    System.out.println("The client with id " + clientId + " does not exists.");
                } else if (planetFromID == null) {
                    System.out.println("The planet of origin with id " + planetFromID + " does not exists.");
                } else if (planetToID == null) {
                    System.out.println("The destination planet with id " + planetToID + " does not exists.");
                } else {
                    Ticket newTicket = new Ticket();
                    newTicket.setCreatedAt(createdAt);
                    newTicket.setClient(existingClient);
                    newTicket.setPlanetFrom(planetFromID);
                    newTicket.setPlanetTo(planetToID);
                    session.persist(newTicket);
                    System.out.println("The new ticket has been created.\n" + newTicket);
                    transaction.commit();
                }
            } catch (Exception e) {
                throw new InvalidOutputException("Failed to create a new ticket: " + e.getMessage());
            }
        }
    }



    @Override
    public void updateTicket(int ticketId, String planetFrom, String planetTo) throws InvalidOutputException {
        if (!planetFrom.matches("[A-Z0-9]*$") || !planetTo.matches("[A-Z0-9]*$")) {
            System.out.println("The planet is incorrect.");
        } else {
            try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession();) {
                Transaction transaction = session.beginTransaction();
                Ticket existingTicket = session.get(Ticket.class, ticketId);
                Planet planetFromID = session.get(Planet.class, planetFrom);
                Planet planetToID = session.get(Planet.class, planetTo);
                if (existingTicket == null) {
                    System.out.println("The ticket with id " + ticketId + " does not exists.");
                } else if (planetFromID == null) {
                    System.out.println("The planet of origin with id " + planetFromID + " does not exists.");
                } else if (planetToID == null) {
                    System.out.println("The destination planet with id " + planetToID + " does not exists.");
                } else {
                    existingTicket.setPlanetFrom(planetFromID);
                    existingTicket.setPlanetTo(planetToID);
                    session.persist(existingTicket);
                    System.out.println("The ticket with id " + ticketId + " was updated.");
                    transaction.commit();
            }
            }
        }
    }

    @Override
    public void getTicketById(int ticketId) throws InvalidOutputException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Ticket ticket = session.get(Ticket.class, ticketId);
        if (ticket == null) {
            throw new InvalidOutputException("The ticket with id " + ticketId + " does not exist.");
        } else {
            System.out.println(ticket);
        }
    }

    @Override
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){
            Query<Ticket> ticketQuery = session.createQuery(
                    "from Ticket",
                    Ticket.class
            );
            tickets = ticketQuery.list();
            return tickets;
        }
    }

    @Override
    public void deleteTicketById(int ticketId) throws InvalidOutputException {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            session.beginTransaction();
            Ticket existingTicket = session.get(Ticket.class, ticketId);
            if (existingTicket == null) {
                throw new InvalidOutputException("Ticket with ID " + ticketId + " does not exist");
            }
            session.remove(existingTicket);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new InvalidOutputException("Failed to delete ticket with ID " + ticketId);
        }
    }
}
