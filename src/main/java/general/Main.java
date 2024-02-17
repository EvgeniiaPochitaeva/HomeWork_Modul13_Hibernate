package general;

import general.ticket.TicketCrudService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws InvalidOutputException {
        TicketCrudService ticketCrudService = new TicketCrudService();
        int clientId = 1;
        String planetFromId = "E10";
        String planetToId = "M20";

        LocalDateTime createdAt = LocalDateTime.now();
        ticketCrudService.createTicket(createdAt, clientId, planetFromId, planetToId);

    }
}

