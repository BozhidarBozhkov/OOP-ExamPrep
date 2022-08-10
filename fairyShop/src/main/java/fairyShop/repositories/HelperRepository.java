package fairyShop.repositories;

import fairyShop.models.Helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HelperRepository<T> implements Repository<Helper> {

    private Collection<Helper> helpers = new ArrayList<>();


    public Collection<Helper> getModels() {
        return Collections.unmodifiableCollection(this.helpers);
    }

    public void add(Helper helper) {
        helpers.add(helper);
    }

    public boolean remove(Helper helper) {
        return helpers.remove(helper);
    }

    public Helper findByName(String name) {
        return helpers.stream().filter(h -> h.getName().equals(name)).findFirst().orElse(null);
    }

}
