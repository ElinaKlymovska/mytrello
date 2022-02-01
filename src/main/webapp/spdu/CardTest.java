package org.spdu;

import org.junit.jupiter.api.Test;
import spd.trello.domain.Card;

import spd.trello.domain.CardList;
import spd.trello.domain.Reminder;
import spd.trello.repository.CardDAO;
import spd.trello.repository.CardListDAO;
import spd.trello.repository.ReminderDAO;
import spd.trello.service.CardListService;
import spd.trello.service.CardService;
import spd.trello.service.ReminderService;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest extends BaseTest {

    private final CardService service;
    private Reminder reminder;
    private CardList cardList;

    public CardTest() {
        service = new CardService(new CardDAO());
        reminder=new Reminder();
        reminder.setStart(LocalDateTime.of(2022, Month.JANUARY, 16, 15, 30, 25, 845));
        reminder.setEnd(LocalDateTime.of(2022, Month.JANUARY, 26, 15, 30, 25, 845));
        reminder.setRemindOn(LocalDateTime.of(2022, Month.JANUARY, 17, 15, 30, 25, 845));
        reminder.setCreatedBy("klymovska.elina@gmail.com");
        reminder.setUpdatedBy("myfeatureknowlange@gmail.com");
        reminder.setCreatedDate(LocalDateTime.now());
        reminder.setUpdatedDate(LocalDateTime.now());
        reminder = new ReminderService(new ReminderDAO()).create(reminder);
        cardList=new CardList();
        cardList.setName("cardList");
        cardList.setArchived(false);
        cardList.setCreatedBy("klymovska.elina@gmail.com");
        cardList.setUpdatedBy("myfeatureknowlange@gmail.com");
        cardList.setCreatedDate(LocalDateTime.now());
        cardList.setUpdatedDate(LocalDateTime.now());
        cardList = new CardListService(new CardListDAO()).create(cardList);
    }

    @Test
    public void successCreate() {
        Card testCard = new Card();
        testCard.setName("FirstCard");
        testCard.setDescription("My lovely project about with card");
        testCard.setArchived(false);
        testCard.setReminder(reminder);
        testCard.setCardList(cardList);
        testCard.setCreatedBy("klymovska.elina@gmail.com");
        testCard.setUpdatedBy("myfeatureknowlange@gmail.com");
        testCard.setCreatedDate(LocalDateTime.now());
        testCard.setUpdatedDate(LocalDateTime.now());
        Card card = service.create(testCard);
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
        assertThrows(Exception.class, () -> new CardDAO().save(card));
    }

    @Test
    public void testFindById() {
        Card testCard = new Card();
        testCard.setName("FirstCard");
        testCard.setDescription("My lovely project about with card");
        testCard.setArchived(false);
        testCard.setReminder(reminder);
        testCard.setCardList(cardList);
        testCard.setCreatedBy("klymovska.elina@gmail.com");
        testCard.setUpdatedBy("myfeatureknowlange@gmail.com");
        testCard.setCreatedDate(LocalDateTime.now());
        testCard.setUpdatedDate(LocalDateTime.now());
        UUID id = service.create(testCard).getId();
        assertEquals(id, service.readById(id).getId());
    }

    @Test
    public void testNoFindById() {
        UUID uuid = UUID.randomUUID();
        assertEquals(null, service.readById(uuid));
    }

    @Test
    public void testFindAll() {
        Card testCard = new Card();
        testCard.setName("FirstCard");
        testCard.setDescription("My lovely project about with card");
        testCard.setArchived(false);
        testCard.setReminder(reminder);
        testCard.setCardList(cardList);
        testCard.setCreatedBy("klymovska.elina@gmail.com");
        testCard.setUpdatedBy("myfeatureknowlange@gmail.com");
        testCard.setCreatedDate(LocalDateTime.now());
        testCard.setUpdatedDate(LocalDateTime.now());
        UUID id = service.create(testCard).getId();
        Card testCard2 = new Card();
        testCard2.setName("FirstCard");
        testCard2.setDescription("My lovely project about with card");
        testCard2.setArchived(false);
        testCard2.setReminder(reminder);
        testCard2.setCardList(cardList);
        testCard2.setCreatedBy("klymovska.elina@gmail.com");
        testCard2.setUpdatedBy("myfeatureknowlange@gmail.com");
        testCard2.setCreatedDate(LocalDateTime.now());
        testCard2.setUpdatedDate(LocalDateTime.now());
        UUID id1 = service.create(testCard2).getId();
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
        Card testCard = new Card();
        testCard.setName("FirstCard");
        testCard.setDescription("My lovely project about with card");
        testCard.setArchived(false);
        testCard.setReminder(reminder);
        testCard.setCardList(cardList);
        testCard.setCreatedBy("klymovska.elina@gmail.com");
        testCard.setUpdatedBy("myfeatureknowlange@gmail.com");
        testCard.setCreatedDate(LocalDateTime.now());
        testCard.setUpdatedDate(LocalDateTime.now());
        Card initialCard = service.create(testCard);

        Card card = new Card();
        card.setName("TestCard");
        card.setDescription("Testing update");
        card.setArchived(true);
        card.setReminder(reminder);
        card.setCardList(cardList);
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
        Card testCard = new Card();
        testCard.setName("FirstCard");
        testCard.setDescription("My lovely project about with card");
        testCard.setArchived(false);
        testCard.setReminder(reminder);
        testCard.setCardList(cardList);
        testCard.setCreatedBy("klymovska.elina@gmail.com");
        testCard.setUpdatedBy("myfeatureknowlange@gmail.com");
        testCard.setCreatedDate(LocalDateTime.now());
        testCard.setUpdatedDate(LocalDateTime.now());
        Card initialCard = service.create(testCard);
        Card card = new Card();
        card.setName(null);
        card.setDescription(null);
        assertThrows(Exception.class, () -> service.update(initialCard.getId(), card));
    }

    @Test
    public void successDelete() {
        Card testCard = new Card();
        testCard.setName("FirstCard");
        testCard.setDescription("My lovely project about with card");
        testCard.setArchived(false);
        testCard.setReminder(reminder);
        testCard.setCardList(cardList);
        testCard.setCreatedBy("klymovska.elina@gmail.com");
        testCard.setUpdatedBy("myfeatureknowlange@gmail.com");
        testCard.setCreatedDate(LocalDateTime.now());
        testCard.setUpdatedDate(LocalDateTime.now());
        UUID id = service.create(testCard).getId();
        assertEquals(id, service.getAll().stream().filter(w -> w.getId().equals(id)).findAny().orElse(null).getId());
        service.delete(id);
        assertEquals(null, service.getAll().stream().filter(w -> w.getId().equals(id)).findAny().orElse(null));
    }

}
