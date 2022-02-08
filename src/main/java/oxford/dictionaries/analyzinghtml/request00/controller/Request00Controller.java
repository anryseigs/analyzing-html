package oxford.dictionaries.analyzinghtml.request00.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import oxford.dictionaries.analyzinghtml.common.AbstractController;
import oxford.dictionaries.analyzinghtml.request00.dto.request.TranslateRequestRequestDto;
import oxford.dictionaries.analyzinghtml.request00.dto.response.TranslateResponseDto;
import oxford.dictionaries.analyzinghtml.common.UrlPaths;

import java.net.URI;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value = UrlPaths.ApiVersion1)
public class Request00Controller extends AbstractController {
  @Autowired
  RestTemplate restTemplate;

  private final String[] listLang = {"en-gb", "en-us", "es", "fr", "gu", "hi", "lv", "ro", "sw", "ta"};
  private final String[] listStrictMatch = {"false", "true"};
  private final String[] listField = { "definitions", "domains", "etymologies", "examples", "pronunciations", "regions", "registers", "variantForms"};

  /**
   * API count all character, word, sentence
   */
  @PostMapping(value = UrlPaths.REQUEST_00.TRANSLATE)
  public Object translate(
    @RequestBody TranslateRequestRequestDto translateRequestRequestDto
  ) {
    // check input
    String language = translateRequestRequestDto.getLanguage();
    if (Objects.isNull(language)) {
      language = "en-gb";
    } else if (!Arrays.asList(listLang).contains(language)) {
      return new TranslateResponseDto(null, String.format("Not support language: %s", language));
    }

    String strictMatch = translateRequestRequestDto.getStrictMatch();
    if (Objects.isNull(strictMatch)) {
      strictMatch = "false";
    } else if (!Arrays.asList(listStrictMatch).contains(strictMatch)) {
      return new TranslateResponseDto(null, String.format("Not support strictMatch: %s", strictMatch));
    }

    String fields = translateRequestRequestDto.getFields();
    if (Objects.isNull(fields)) {
      fields = "pronunciations";
    } else if (!Arrays.asList(listField).contains(fields)) {
      return new TranslateResponseDto(null, String.format("Not support fields: %s", fields));
    }

    // build URL to call
    String uRL = this.buildUrl(language, translateRequestRequestDto.getWord(), fields, strictMatch);

    String response = this.callEntriesApi(uRL);

    if (Objects.isNull(response)) {
      return new TranslateResponseDto(null, "Error when call API");
    } else {
      Gson g = new Gson();
      return g.fromJson(response, Object.class);
    }
  }

  private String buildUrl(String language, String word, String fields, String strictMatch) {
    String word_id = word.toLowerCase();
    return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
  }

  public String callEntriesApi(String url) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("app_id", "76e4f61d");
    headers.set("app_key", "8d179a4f58ddf109d5c386c5898211d3");
    RequestEntity<Object> entity = new RequestEntity(headers, HttpMethod.GET, URI.create(url));
    try {
      return restTemplate.exchange(entity, String.class).getBody();
    } catch (Exception exception) {
      log.error("exception: {}", exception.toString());
      return null;
    }
  }
}
