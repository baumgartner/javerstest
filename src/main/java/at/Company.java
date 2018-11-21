package at;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Data
public class Company {

    private String id;
    private Person owner;
    private Collection<Person> clients;
    private List<Person> partners;
}