package at;

import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.diff.custom.CustomPropertyComparator;
import org.javers.core.metamodel.object.GlobalId;
import org.javers.core.metamodel.property.Property;

import java.util.Optional;

public class EntityComparator implements CustomPropertyComparator<Person, ValueChange> {

    public Optional<ValueChange> compare(Person left, Person right, GlobalId affectedId, Property property) {
        if (left.getName().equals(right.getName()))
            return Optional.empty();
        return Optional.ofNullable( new ValueChange(affectedId, "entity/name", left.getName(), right.getName()));
    }

    public boolean equals(Person left, Person right) {
        if (left.getName().equals(right.getName()))
            return true;
        return false;
    }
    
    

}