package com.uni.mapsapitest.repository;

import java.util.ArrayList;
import java.util.List;

import com.uni.mapsapitest.models.Sponsor;

public class SponsorRepository {
    private List<Sponsor> sponsors = new ArrayList<>();

    public void save(Sponsor sponsor) {
        sponsors.add(sponsor);
    }

    public List<Sponsor> findAll() {
        return sponsors;
    }
}
