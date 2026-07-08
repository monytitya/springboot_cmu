package Springboot_cmu.Usermanage_services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import Springboot_cmu.Usermanage_services.Dto.UserDto;

class UserDtoTest {

    @Test
    void shouldExposeNameAndEmailFields() {
        UserDto dto = new UserDto("Chanthy", "chanthy@example.com");

        assertThat(dto.getName()).isEqualTo("Chanthy");
        assertThat(dto.getEmail()).isEqualTo("chanthy@example.com");
    }
}
