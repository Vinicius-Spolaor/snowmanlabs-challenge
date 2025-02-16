package com.snowmanlabs.challenge.service.interfaces;

import com.snowmanlabs.challenge.dto.AuthRequestDto;
import com.snowmanlabs.challenge.model.User;

public interface IAuthService {
    User authenticate(AuthRequestDto authRequestDto);
}
