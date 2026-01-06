package com.tm.teamManagement.Controller;

import com.tm.teamManagement.Dto.Profile.ProfileBaseDTO;
import com.tm.teamManagement.Dto.Profile.ProfileMapper;
import com.tm.teamManagement.Dto.Profile.ProfileViewDTO;
import com.tm.teamManagement.Dto.Response.ApiResponse;
import com.tm.teamManagement.Service.Profile.IProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProfileController {


    private final IProfileService profileService;

    @Autowired
    private ProfileMapper profileMapper;

    @GetMapping("/person/{id}/profile")
    public ResponseEntity<Object> getProfile(@PathVariable("id") Long id) {

        ProfileViewDTO profileDTO = profileMapper.toDto(profileService.getProfile(id));

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Profile from person with ID: " + id + ".",profileDTO));

    }

    @PostMapping("/person/{id}/profile/create")
    public ResponseEntity<Object> createProfile(@PathVariable("id") Long personId,
                                        @Valid @RequestBody ProfileBaseDTO profileBaseDTO){


        ProfileViewDTO profileDTO =  profileMapper.toDto(profileService.createProfile(profileBaseDTO,personId));

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Profile created for person with ID: " + personId + ".",profileDTO));
    }

    @DeleteMapping("/person/{id}/profile/delete")
    public ResponseEntity<Object> deleteProfile(@PathVariable("id") Long personId){
        profileService.deleteProfile(personId);
        if ( profileService.existsByPersonId(personId) ) {
            return ResponseEntity.status(HttpStatus.OK)
                            .body(ApiResponse.success("Profile deletion failed",null));

        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Profile deleted successfully",null));
    }

    @PatchMapping("/person/{id}/profile/update")
    public ResponseEntity<Object> updateProfile(@PathVariable("id") Long id,
                                        @Valid @RequestBody ProfileBaseDTO profileBaseDTO){


        ProfileViewDTO updatedProfileDTO =  profileMapper.toDto(profileService.updateProfile(profileBaseDTO,id));

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Profile updated successfully",updatedProfileDTO));
    }
}
