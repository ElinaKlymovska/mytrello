package org.spdu;

import org.junit.jupiter.api.Test;
import spd.trello.domain.Card;

import spd.trello.repository.CardDAO;
import spd.trello.service.CardService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest extends BaseTest {

    private final CardService service;

    public CardTest() {
        service = new CardService(new CardDAO(dataSource));
    }

    @Test
    public void successCreate() {
        Card card = service.create();
        assertNotNull(card);
        assertAll(
                () -> assertNotNull(card.getId()),
                () -> assertEquals("FirstCard", card.getName()),
                () -> assertEquals("My lovely project about with card", card.getDescription()),
                () -> assertEquals(false, card.getArchived()),
                () -> assertEquals("klymovska.elina@gmail.com", card.getCreatedBy()),
                () -> assertEquals("myfeatureknowlange@gmail.com", card.getUpdatedBy()),
                () -> assertNotNull(card.getUpdatedDate()),
                () -> assertNotNull(card.getCreatedDate())
        );
    }

    @Test
    public void createFailure() {
        Card card = new Card();
        card.setName(null);
        assertThrows(Exception.class, () -> new CardDAO(dataSource).save(card));
    }

    @Test
    public void testFindById() {
        UUID id = service.create().getId();
        assertEquals(id, service.readById(id).getId());
    }

    @Test
    public void testNoFindById() {
        UUID uuid = UUID.randomUUID();
        assertEquals(null, service.readById(uuid));
    }

    @Test
    public void testFindAll() {
        UUID id = service.create().getId();
        UUID id1 = service.create().getId();
        List<Card> all = service.getAll();
        assertNotNull(all);
        assertAll(
                () -> assertEquals(service.readById(id).getId(), all.stream().filter(w -> w.getId().equals(id)).findAny().get().getId()),
                () -> assertEquals(service.readById(id1).getId(), all.stream().filter(w -> w.getId().equals(id1)).findFirst().orElse(null).getId()),
                () -> assertEquals(null, all.stream().filter(w -> w.getId().equals(UUID.randomUUID())).findAny().orElse(null))
        );
    }

    @Test
    public void successUpdate() {
        Card initialCard = service.create();

        Card card = new Card();
        card.setName("TestCard");
        card.setDescription("Testing update");
        card.setArchived(true);
        card.setUpdatedBy("testing@gmail.com");
        card.setCreatedBy(initialCard.getCreatedBy());
        card.setCreatedDate(initialCard.getCreatedDate());
        card.setUpdatedDate(LocalDateTime.now());
        service.update(initialCard.getId(), card);
        assertNotNull(card);
        assertAll(
                () -> assertEquals("TestCard", card.getName()),
                () -> assertEquals("Testing update", card.getDescription()),
                () -> assertEquals(true, card.getArchived()),
                () -> assertEquals("klymovska.elina@gmail.com", card.getCreatedBy()),
                () -> assertEquals("testing@gmail.com", card.getUpdatedBy()),
                () -> assertNotNull(card.getUpdatedDate()),
                () -> assertNotNull(card.getCreatedDate())
        );
    }

    @Test
    public void updateFailure() {
        Card initialCard = service.create();
        Card card = new Card();
        card.setName(null);
        card.setDescription(null);
        assertThrows(Exception.class, () -> service.update(initialCard.getId(), card));
    }

    @Test
    public void successDelete() {
        UUID id = service.create().getId();
        assertEquals(id, service.getAll().stream().filter(w -> w.getId().equals(id)).findAny().orElse(null).getId());
        service.delete(id);
        assertEquals(null, service.getAll().stream().filter(w -> w.getId().equals(id)).findAny().orElse(null));
    }

}
