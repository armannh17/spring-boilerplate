package com.example.demo.platform.shared.serializer;

import java.util.UUID;
import org.mapstruct.Named;
import org.springframework.security.core.userdetails.UserDetails;

public interface BaseSerializer {
  @Named("mapId")
  public static UUID mapId(String id) {
    return UUID.fromString(id);
  }

  @Named("mapId")
  public static UUID mapId(UserDetails user) {
    return UUID.fromString(user.getUsername());
  }
}
