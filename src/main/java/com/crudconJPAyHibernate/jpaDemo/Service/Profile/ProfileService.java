package com.crudconJPAyHibernate.jpaDemo.Service.Profile;

import com.crudconJPAyHibernate.jpaDemo.Exceptions.ConflictException;
import com.crudconJPAyHibernate.jpaDemo.Exceptions.NotFoundException;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Person;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Profile;
import com.crudconJPAyHibernate.jpaDemo.Repository.IPersonRepository;
import com.crudconJPAyHibernate.jpaDemo.Repository.IProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@RequiredArgsConstructor
@Service
public class ProfileService implements IProfileService {

    private final IProfileRepository profileRepository;

    private final IPersonRepository personRepository;

    @Override
    public Profile getProfile(Long personId) {
        return profileRepository.findProfileByPersonId(personId).orElseThrow(() -> new NotFoundException("Person profile not found"));
    }

    @Override
    public boolean existsByPersonId(Long personId) {
        return profileRepository.existsByPersonId(personId);
    }

    @Override
    public Profile createProfile(Profile profile) {

        if(profileRepository.findProfileByPersonId(profile.getPerson().getId()).isPresent()){
            throw new ConflictException("Person profile already exists");
        }
        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(Profile profile) {
        Profile dbProfile = getProfile(profile.getPerson().getId());

        if(profile.getAvatarUrl() != null) {
            dbProfile.setAvatarUrl(profile.getAvatarUrl());
        }
        if(profile.getBiography() != null) {
            dbProfile.setBiography(profile.getBiography());
        }
        if(profile.getGender() != null) {
            dbProfile.setGender(profile.getGender());
        }
        if(profile.getNationality() != null) {
            dbProfile.setNationality(profile.getNationality());
        }
        if(profile.getLinkedinUrl() != null) {
            dbProfile.setLinkedinUrl(profile.getLinkedinUrl());
        }

        return profileRepository.save(dbProfile);
    }



    //The owning side of the relationship is the Person.
    @Transactional
    @Override
    public void deleteProfile(Long personId) {
        Person person = personRepository.findById(personId)
                            .orElseThrow(() -> new NotFoundException("Person not found"));

        Profile profile = getProfile(personId);
        if (profile == null) { throw new NotFoundException("Profile not found");}

        person.setProfile(null);
        profileRepository.save(profile);
    }
}
