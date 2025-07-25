package com.example.demo.platform.core.extractor;

import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.zalando.logbook.HttpRequest;
import org.zalando.logbook.attributes.AttributeExtractor;
import org.zalando.logbook.attributes.HttpAttributes;

public final class UserExtractor implements AttributeExtractor {

  @Override
  public HttpAttributes extract(final HttpRequest request) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null
        || !authentication.isAuthenticated()
        || !(authentication.getPrincipal() instanceof UserDetails)) {
      return HttpAttributes.EMPTY;
    }

    UserDetails principal = (UserDetails) authentication.getPrincipal();

    String username = principal.getUsername();
    String role = principal.getAuthorities().toArray()[0].toString().substring(5);

    Map<String, Object> attributes =
        Map.of(
            "username", username,
            "role", role);

    return new HttpAttributes(attributes);
  }
}
