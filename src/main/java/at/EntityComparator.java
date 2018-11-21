package at;

import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.diff.custom.CustomPropertyComparator;
import org.javers.core.metamodel.object.GlobalId;
import org.javers.core.metamodel.property.Property;

public class EntityComparator implements CustomPropertyComparator<Person, ValueChange> {

    public ValueChange compare(Person left, Person right, GlobalId affectedId, Property property) {
        if (left.getName().equals(right.getName()))
            return null;
        return new ValueChange(affectedId, "entity/name", left.getName(), right.getName());
    }

}