package com.tm.teamManagement.Service.Profile;

import com.tm.teamManagement.Dto.Profile.ProfileBaseDTO;
import com.tm.teamManagement.Dto.Profile.ProfileMapper;
import com.tm.teamManagement.Exceptions.ConflictException;
import com.tm.teamManagement.Exceptions.NotFoundException;
import com.tm.teamManagement.Model.Entity.Person;
import com.tm.teamManagement.Model.Entity.Profile;
import com.tm.teamManagement.Repository.IProfileRepository;
import com.tm.teamManagement.Service.Person.IPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProfileService implements IProfileService {

    private final IProfileRepository profileRepository;
    private final IPersonService personService;

    private final ProfileMapper profileMapper;

    @Override
    public Profile getProfile(Long personId) {
        return profileRepository.findProfileByPersonId(personId).orElseThrow(() -> new NotFoundException("Person profile not found"));
    }

    @Override
    public boolean existsByPersonId(Long personId) {
        return profileRepository.existsByPersonId(personId);
    }

    @Override
    public Profile createProfile(ProfileBaseDTO profileBaseDTO,Long personId) {

        Person person = personService.getPerson(personId);
        Profile profile = profileMapper.toEntity(profileBaseDTO,person);

        if(profileRepository.findProfileByPersonId(profile.getPerson().getId()).isPresent()){
            throw new ConflictException("Person profile already exists");
        }
        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(ProfileBaseDTO  profileBaseDTO,Long personId) {
        Person person = personService.getPerson(personId);
        Profile profile = profileMapper.toEntity(profileBaseDTO,person);

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
        Person person = personService.getPerson(personId);

        Profile profile = getProfile(personId);
        if (profile == null) { throw new NotFoundException("Profile not found");}

        person.setProfile(null);
        profileRepository.save(profile);
    }
}
