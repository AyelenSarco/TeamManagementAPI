package com.crudconJPAyHibernate.jpaDemo.Controller;

import com.crudconJPAyHibernate.jpaDemo.Dto.Profile.ProfileBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Dto.Profile.ProfileMapper;
import com.crudconJPAyHibernate.jpaDemo.Dto.Profile.ProfileViewDTO;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Profile;
import com.crudconJPAyHibernate.jpaDemo.Service.Profile.IProfileService;
import com.crudconJPAyHibernate.jpaDemo.Service.Profile.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProfileController {


    private final IProfileService profileService;

    @Autowired
    private ProfileMapper profileMapper;

    @GetMapping("/person/{id}/profile")
    public ProfileViewDTO getProfile(@PathVariable("id") Long id) {
        return profileMapper.toDto(profileService.getProfile(id));
    }

    @PostMapping("/person/{id}/profile/create")
    public ProfileViewDTO createProfile(@PathVariable("id") Long id,
                                        @Valid @RequestBody ProfileBaseDTO profileBaseDTO){


        Profile profile = profileMapper.toEntity(profileBaseDTO, id);
        return profileMapper.toDto(profileService.createProfile(profile));
    }

    @DeleteMapping("/person/{id}/profile/delete")
    public ResponseEntity<String> deleteProfile(@PathVariable("id") Long personId){
        profileService.deleteProfile(personId);
        if ( profileService.existsByPersonId(personId) ) {
            return ResponseEntity.ok("Profile deletion failed");
        }
        return ResponseEntity.ok("Profile deleted");
    }

    @PatchMapping("/person/{id}/profile/update")
    public ProfileViewDTO updateProfile(@PathVariable("id") Long id,
                                        @Valid @RequestBody ProfileBaseDTO profileBaseDTO){


        Profile profile = profileMapper.toEntity(profileBaseDTO, id);
        return profileMapper.toDto(profileService.updateProfile(profile));
    }
}
