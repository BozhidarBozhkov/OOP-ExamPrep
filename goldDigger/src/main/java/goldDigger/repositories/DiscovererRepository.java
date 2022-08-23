package goldDigger.repositories;

import goldDigger.models.discoverer.Discoverer;

import java.util.*;

public class DiscovererRepository implements Repository<Discoverer>{

  //  private Map<String, Discoverer> discoverers;
    private Collection<Discoverer> discoverers;

    public DiscovererRepository() {
       // this.discoverers = new LinkedHashMap<>();
        this.discoverers = new ArrayList<>();
    }


    @Override
    public Collection<Discoverer> getCollection() {
        //return Collections.unmodifiableCollection(discoverers.values());
        return Collections.unmodifiableCollection(discoverers);
    }

    @Override
    public void add(Discoverer discoverer) {
        //  discoverers.put(discoverer.getName(), discoverer);
        discoverers.add(discoverer);

    }

    @Override
    public boolean remove(Discoverer discoverer) {
      //  return discoverers.remove(discoverer.getName()) != null;
        return discoverers.remove(discoverer);
    }

    @Override
    public Discoverer byName(String name) {
       return discoverers.stream().filter(d -> d.getName().equals(name)).findFirst().orElse(null);
     //   return discoverers.get(name);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
      //  discoverers.values().forEach(discoverer -> sb.append(discoverer).append(System.lineSeparator()));

        discoverers.stream().forEach(discoverer -> sb.append(discoverer).append(System.lineSeparator()));
        return sb.toString();
    }
}


