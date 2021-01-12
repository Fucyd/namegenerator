package pl.michalski.namegenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class NameApiController {

    private NameService nameService;

    @Autowired
    public NameApiController(NameService nameService) {
        this.nameService = nameService;
    }

    @GetMapping("/one")
    public EntityModel<Names> getOneName() {
        return nameService.getRandomName();
    }

    @GetMapping("/onemale")
    public EntityModel<Names> getOneMaleName() {
        return nameService.getRandomNameByType(NameType.TYPE_MALE);
    }

    @GetMapping("/onefemale")
    public EntityModel<Names> getOneFemaleName() {
        return nameService.getRandomNameByType(NameType.TYPE_FEMALE);
    }

    @GetMapping("/one/{id}")
    public EntityModel<Names> getOneNameById(@PathVariable Long id) {
        return nameService.getNameById(id);
    }

    @GetMapping("/five")
    public CollectionModel<EntityModel<Names>> getFiveNames() {
        return nameService.getFiveRandomNames();
    }

    @GetMapping("/fivemale")
    public CollectionModel<EntityModel<Names>> getFiveMaleNames() {
        return nameService.getFiveRandomNamesByType(NameType.TYPE_MALE);
    }

    @GetMapping("/fivefemale")
    public CollectionModel<EntityModel<Names>> getFiveFemaleNames() {
        return nameService.getFiveRandomNamesByType(NameType.TYPE_FEMALE);
    }

    @GetMapping("/allmale")
    public CollectionModel<EntityModel<Names>> getAllMaleNames() {
        return nameService.getAllNamesByType(NameType.TYPE_MALE);
    }

    @GetMapping("/allfemale")
    public CollectionModel<EntityModel<Names>> getAllFemaleNames() {
        return nameService.getAllNamesByType(NameType.TYPE_FEMALE);
    }

}
