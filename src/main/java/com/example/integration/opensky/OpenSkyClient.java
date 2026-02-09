package com.example.integration.opensky;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Map;

@Component
public class OpenSkyClient {

    @Value("${opensky.tokenUrl}")
    private String tokenUrl;

    @Value("${opensky.apiBase}")
    private String apiBase;

    @Value("${opensky.clientId}")
    private String clientId;

    @Value("${opensky.clientSecret}")
    private String clientSecret;

    private final RestTemplate rest = new RestTemplate();

    private String cachedToken;
    private Instant expireAt = Instant.EPOCH;

    /** 取得 Bearer token（自動快取） */
    private synchronized String getToken() {
        if (cachedToken != null && Instant.now().isBefore(expireAt.minusSeconds(60))) {
            return cachedToken;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "client_credentials");
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> req =
                new HttpEntity<>(form, headers);

        @SuppressWarnings("unchecked")
        Map<String, Object> res =
                rest.postForObject(tokenUrl, req, Map.class);

        cachedToken = String.valueOf(res.get("access_token"));
        long expiresIn = ((Number) res.getOrDefault("expires_in", 1800)).longValue();
        expireAt = Instant.now().plusSeconds(expiresIn);

        return cachedToken;
    }

    /** 呼叫 OpenSky states/all */
    public Map<String, Object> fetchStates(double lamin, double lamax,
                                           double lomin, double lomax) {

        String url = String.format(
            "%s/states/all?lamin=%s&lamax=%s&lomin=%s&lomax=%s",
            apiBase, lamin, lamax, lomin, lomax
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getToken());

        ResponseEntity<Map> resp = rest.exchange(
            url,
            HttpMethod.GET,
            new HttpEntity<>(headers),
            Map.class
        );

        return resp.getBody();
    }
}
