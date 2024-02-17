package general.ticket;

import general.InvalidOutputException;

import java.time.LocalDateTime;
import java.util.List;

public interface ITicketDaoService  {
    void createTicket(LocalDateTime createdAt, int clientId, String planetFrom, String planetTo) throws InvalidOutputException;
    void updateTicket(int ticketId, String planetFrom, String planetTo) throws InvalidOutputException;
    void getTicketById(int ticketId) throws InvalidOutputException;
    List<Ticket> getAllTickets();
    void deleteTicketById(int ticketId) throws InvalidOutputException;
}
