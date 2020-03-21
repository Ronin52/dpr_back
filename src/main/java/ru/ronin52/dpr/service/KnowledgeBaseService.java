package ru.ronin52.dpr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ronin52.dpr.dto.KnowledgeBaseDto;
import ru.ronin52.dpr.model.KnowledgeBaseEntity;
import ru.ronin52.dpr.repo.KnowledgeBaseRepository;

import javax.annotation.PostConstruct;

import static ru.ronin52.dpr.model.KnowledgeBaseItemType.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class KnowledgeBaseService {
    private final KnowledgeBaseRepository repository;

    private static final String START = "START";

    @PostConstruct
    public void init() {
        log.info("Создание базы знаний для задачи");

        repository.save(new KnowledgeBaseEntity("START", Q, "Q2", "R0", "Двигатель не заводится?"));
        repository.save(new KnowledgeBaseEntity("Q2", Q, "RQ2", "RQ1", "Искра есть?"));
        repository.save(new KnowledgeBaseEntity("Q3", Q, "R1", "Q5", "Цепь низкого напряжения функционирует?"));
        repository.save(new KnowledgeBaseEntity("Q4", Q, "Q6", "R2", "Бензин есть?"));
        repository.save(new KnowledgeBaseEntity("Q5", Q, "R3", "R5", "Замена коммутатора устраняет неисправность?"));
        repository.save(new KnowledgeBaseEntity("Q6", Q, "R6", "R4", "Бензин в карбюратор поступает?"));
        repository.save(new KnowledgeBaseEntity("RQ1", RQ, "Q3", "Q3", "Неисправна система зажигания"));
        repository.save(new KnowledgeBaseEntity("RQ2", RQ, "Q4", "Q4", "Неисправна система питания"));
        repository.save(new KnowledgeBaseEntity("R0", R, null, null, "Машина исправна можно ехать"));
        repository.save(new KnowledgeBaseEntity("R1", R, null, null, "Неисправность в катушке зажигания"));
        repository.save(new KnowledgeBaseEntity("R2", R, null, null, "Необходимо заправиться"));
        repository.save(new KnowledgeBaseEntity("R3", R, null, null, "Неисправность в коммутаторе"));
        repository.save(new KnowledgeBaseEntity("R4", R, null, null, "Неисправность в бензонасосе"));
        repository.save(new KnowledgeBaseEntity("R5", R, null, null, "Неисправность в датчике-распределителе"));
        repository.save(new KnowledgeBaseEntity("R6", R, null, null, "Неисправность в карбюраторе"));

    }

    public KnowledgeBaseDto answer(String point, boolean answer) {

        log.info("Пришел запрос на получение следующего звена цепи для {}, с ответом {}", point, answer);

        KnowledgeBaseEntity current = repository.findById(point).get();
        KnowledgeBaseEntity next = repository.findById(answer ? current.getPositive() : current.getNegative()).get();

        return new KnowledgeBaseDto(next.getPoint(), next.getText(), next.getType());
    }

    public KnowledgeBaseDto start() {

        log.info("Пришел запрос на начало консультации");

        KnowledgeBaseEntity start = repository.findById(START).get();
        return new KnowledgeBaseDto(start.getPoint(), start.getText(), start.getType());
    }
}
