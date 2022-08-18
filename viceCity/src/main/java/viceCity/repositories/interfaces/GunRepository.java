package viceCity.repositories.interfaces;

import viceCity.models.guns.Gun;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GunRepository implements Repository<Gun>{

    private Collection<Gun> models;

    public GunRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection getModels() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Gun model) {

    }

    @Override
    public boolean remove(Gun model) {
        return false;
    }


    @Override
    public Gun find(String name) {
        return null;
    }
}
