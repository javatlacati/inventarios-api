package inventarios.controller;

import inventarios.service.CompetitorService;
import inventarios.to.competition.Competitor;
import io.quarkus.cache.CacheResult;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import javax.inject.Inject;
import java.util.List;

@GraphQLApi
public class CompetitorController {
    @Inject
    CompetitorService competitorService;

    @CacheResult(cacheName = "all-competitors-cache")
    @Query
    @Description("Gets all competitors from database")
    public Uni<List<Competitor>> getCompetitors(){
        return competitorService.getAllCompetitors();
    }

    @Query
    public Uni<Competitor> getCompetitor(@Name("competitorId") long id){
        return competitorService.getCompetitor(id);
    }

    @Mutation
    public Uni<Competitor> addCompetitor(Competitor person){
        return competitorService.createCompetitor(person);
    }
}
