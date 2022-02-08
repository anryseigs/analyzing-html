package oxford.dictionaries.analyzinghtml.request01.controller;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oxford.dictionaries.analyzinghtml.commond.AbstractController;
import oxford.dictionaries.analyzinghtml.request01.dto.request.CountAllRequestRequestDto;
import oxford.dictionaries.analyzinghtml.request01.dto.response.CountAllResponseDto;
import oxford.dictionaries.analyzinghtml.commond.UrlPaths;

import java.text.BreakIterator;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value = UrlPaths.ApiVersion1)
public class Request01Controller extends AbstractController {

  /**
   * API count all character, word, sentence
   */
  @PostMapping(value = UrlPaths.REQUEST_01.COUNT_ALL)
  public CountAllResponseDto countAll(
    @RequestBody CountAllRequestRequestDto countAllRequestDto
  ) {
    if (Objects.isNull(countAllRequestDto.getHtmlString())) {
      return new CountAllResponseDto("Fail to request, html_string is null");
    } else {
      // parse jsoup
      Document stringNode = Jsoup.parse(countAllRequestDto.getHtmlString());

      // count character and word
      String htmlString = stringNode.text();
      long totalCharacters = htmlString.chars().count();
      long totalWord = htmlString.split("\\s+").length;
      long totalSentence = this.countSentence(stringNode);

      // log report
      log.info("\n========================");
      log.info("======== REPORT ========");
      log.info("totalCharacters: {}", totalCharacters);
      log.info("totalWord: {}", totalWord);
      log.info("totalSentence: {}", totalSentence);
      log.info("========================");

      return new CountAllResponseDto(totalCharacters, totalWord, totalSentence);
    }
  }

  /**
   * Count number of sentence from jsoup node
   */
  private long countSentence(Document stringNode){
    long countSentence = 0;

    String htmlLine = stringNode.outerHtml();
    String[] lines = htmlLine.split("\\r?\\n");

    // get each element in html
    for (String x : lines) {
      String text = Jsoup.parse(x).text();
      if (!text.isBlank()) {
        Locale currentLocale = new Locale("vi", "VN");
        BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(currentLocale);
        long numberSentence = markBoundaries(text, sentenceIterator);
        countSentence += numberSentence;
      }
    }
    return countSentence;
  }

  /**
   * present logging and count sentence
   */
  private static long markBoundaries(String target, BreakIterator iterator) {
    // build marker for logging
    StringBuilder markers = new StringBuilder();
    markers.setLength(target.length() + 1);
    for (int k = 0; k < markers.length(); k++) {
      markers.setCharAt(k, ' ');
    }

    // count boundary
    int count = 0;
    iterator.setText(target);
    int boundary = iterator.first();
    while (boundary != BreakIterator.DONE) {
      markers.setCharAt(boundary, '^');
      ++count;
      boundary = iterator.next();
    }
    log.info("\n=======");
    log.info(target);
    log.info("{}", markers);
    log.info("====> {} sentences", count - 1);
    return count - 1;
  }
}
