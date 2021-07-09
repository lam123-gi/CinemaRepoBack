package ma.esmi.cinemaprojet.web;

import lombok.Data;
import ma.esmi.cinemaprojet.dao.FilmRepository;
import ma.esmi.cinemaprojet.dao.TicketRepository;
import ma.esmi.cinemaprojet.entities.Film;
import ma.esmi.cinemaprojet.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CinemaRestController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping(path = "/imageFilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] films(@PathVariable(name = "id") Long id) throws Exception {
        Film film = filmRepository.findById(id).get();
        String photoName = film.getPhoto();
        File file = new File(System.getProperty("user.home") + "/cinema/images/" + photoName + ".jpg");
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

    @PostMapping("/payerTicket")
    public List<Ticket> payerTicket(@RequestBody TicketForm ticketForm) {
        List<Ticket> tickets = new ArrayList<>();
        ticketForm.getTickets().forEach(id -> {
            Ticket ticket = ticketRepository.findById(id).get();
            ticket.setNomClient(ticketForm.getNomClient());
            ticket.setReserve(true);
            ticket.setCodePayement(ticketForm.getCodePayement());
            ticketRepository.save(ticket);
            tickets.add(ticket);
        });
        return tickets;
    }


}

@Data
class TicketForm {
    private String nomClient;
    private int codePayement;
    private List<Long> tickets = new ArrayList<>();
}
