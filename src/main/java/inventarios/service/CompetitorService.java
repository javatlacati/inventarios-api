package inventarios.service;

import inventarios.to.competition.Competitor;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CompetitorService {

    public Uni<List<Competitor>> getAllCompetitors() {
        return Competitor.listAll();
    }

    public Uni<Competitor> getCompetitor(long id) {
        return Competitor.findById(id);
    }

    public Uni<Competitor> createCompetitor(Competitor person) {
        return person.persistAndFlush();
    }
}
