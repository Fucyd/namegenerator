package pl.michalski.namegenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoadDatabase implements CommandLineRunner {


    private NamesRepository namesRepository;

    @Autowired
    public LoadDatabase(NamesRepository namesRepository) {
        this.namesRepository = namesRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        fileItems("femaleNames.txt")
                .forEach(name -> namesRepository.save(new Names(name, NameType.TYPE_FEMALE)));
        fileItems("maleNames.txt")
                .forEach(name -> namesRepository.save(new Names(name, NameType.TYPE_MALE)));
    }

    private List<String> fileItems(String fileName) throws IOException {
        List<String> list= new ArrayList<>();
        int x = 0;
        //        FileReader fileReader = new FileReader(new File("src/main/resources/femaleNames.txt"));
        FileReader fileReader = new FileReader("src/main/resources/" + fileName);
        BufferedReader reader = new BufferedReader(fileReader);
        while(reader.readLine() != null){
            list.add(reader.readLine());
        }
        reader.close();
        fileReader.close();

        return list;
    }
}
