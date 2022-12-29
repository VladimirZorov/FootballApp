package football.repositories;

import football.entities.supplement.Supplement;

import java.util.Collection;

public class SupplementRepositoryImpl implements SupplementRepository{

    private Collection<Supplement> supplements;

    @Override
    public void add(Supplement supplement) {

    }

    @Override
    public boolean remove(Supplement supplement) {
        return false;
    }

    @Override
    public Supplement findByType(String type) {
        return null;
    }
}
