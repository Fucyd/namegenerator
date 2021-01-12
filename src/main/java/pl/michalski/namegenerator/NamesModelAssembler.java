package pl.michalski.namegenerator;

import org.hibernate.EntityMode;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class NamesModelAssembler implements RepresentationModelAssembler<Names, EntityModel<Names>> {
    @Override
    public EntityModel<Names> toModel(Names entity) {
        EntityModel<Names> namesModel = EntityModel.of(entity,

                linkTo(methodOn(NameApiController.class).getOneNameById(entity.getId())).withSelfRel(),
                linkTo(methodOn(NameApiController.class).getOneName()).withRel("One Random Name"));
        return namesModel;
    }
}
