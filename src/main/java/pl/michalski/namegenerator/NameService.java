package pl.michalski.namegenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class NameService {

    private NamesRepository namesRepository;
    private NamesModelAssembler namesModelAssembler;

    @Autowired
    public NameService(NamesRepository namesRepository, NamesModelAssembler namesModelAssembler) {
        this.namesRepository = namesRepository;
        this.namesModelAssembler = namesModelAssembler;
    }

    public EntityModel<Names> getRandomName() {
        return namesModelAssembler.toModel(oneRandomName());
    }

    public EntityModel<Names> getNameById(Long id) {
        return namesModelAssembler.toModel(namesRepository.findById(id).get());
    }

    public EntityModel<Names> getRandomNameByType(NameType nameType) {
        return namesModelAssembler.toModel(oneRandomNameByType(nameType));
    }

    public CollectionModel<EntityModel<Names>> getFiveRandomNames() {
        List<EntityModel<Names>> entityModels = fiveRandomNames().stream()
                .map(namesModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(entityModels,
                linkTo(methodOn(NameApiController.class).getFiveNames()).withRel("Five random names"));
    }

    public CollectionModel<EntityModel<Names>> getFiveRandomNamesByType(NameType nameType) {
        List<EntityModel<Names>> entityModels = fiveRandomNamesByType(nameType).stream()
                .map(namesModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(entityModels,
                linkTo(methodOn(NameApiController.class).getFiveNames()).withRel("Five random names"));
    }

    public CollectionModel<EntityModel<Names>> getAllNamesByType(NameType nameType) {
        List<EntityModel<Names>> entityModels = allNamesByType(nameType).stream()
                .map(namesModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(entityModels,
                linkTo(methodOn(NameApiController.class).getAllFemaleNames()).withRel("All Female Names"),
                linkTo(methodOn(NameApiController.class).getAllMaleNames()).withRel("All Male Names"));
    }

    private List<Names> allNamesByType(NameType nameType) {
        return namesRepository.findAllByType(nameType);
    }

    private Names oneRandomName() {
        List<Names> namesList = namesRepository.findAll();
        Random random = new Random();
        return namesList.get(random.nextInt(namesList.size()));
    }

    private Names oneRandomNameByType(NameType nameType) {
        List<Names> namesList = namesRepository.findAllByType(nameType);
        Random random = new Random();
        return namesList.get(random.nextInt(namesList.size()));
    }

    private List<Names> fiveRandomNames() {
        List<Names> namesList = namesRepository.findAll();
        Random random = new Random();
        List<Names> fiveNames = new ArrayList<>();
        for (int x = 0; x < 5; x++) {
            Names newName = namesList.get(random.nextInt(namesList.size()));
            if (!fiveNames.contains(newName))
                fiveNames.add(newName);
        }
        return fiveNames;
    }

    private List<Names> fiveRandomNamesByType(NameType nameType) {
        List<Names> namesList = namesRepository.findAllByType(nameType);
        Random random = new Random();
        List<Names> fiveNames = new ArrayList<>();
        for (int x = 0; x < 5; x++) {
            Names newName = namesList.get(random.nextInt(namesList.size()));
            if (!fiveNames.contains(newName))
                fiveNames.add(newName);
        }
        return fiveNames;
    }


}
