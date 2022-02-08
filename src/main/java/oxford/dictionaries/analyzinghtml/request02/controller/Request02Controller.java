package oxford.dictionaries.analyzinghtml.request02.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oxford.dictionaries.analyzinghtml.commond.AbstractController;
import oxford.dictionaries.analyzinghtml.commond.UrlPaths;
import oxford.dictionaries.analyzinghtml.request02.dto.request.MdToHtmlRequestRequestDto;
import oxford.dictionaries.analyzinghtml.request02.dto.response.MdToHtmlRequestResponseDto;
import oxford.dictionaries.analyzinghtml.request02.service.ConvertService;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value = UrlPaths.ApiVersion1)
public class Request02Controller extends AbstractController {

  @Autowired
  private ConvertService convertService;

  /**
   * API convert markdown to html
   */
  @PostMapping(value = UrlPaths.REQUEST_02.MD_TO_HTML)
  public MdToHtmlRequestResponseDto convert(
    @RequestBody MdToHtmlRequestRequestDto mdToHtmlRequestRequestDto
  ) {
    if (Objects.isNull(mdToHtmlRequestRequestDto.getMdString())) {
      return new MdToHtmlRequestResponseDto(null, "Fail to request, html_md is null");
    } else {
      String htmlString = convertService.markdownToHtml(mdToHtmlRequestRequestDto.getMdString());
      return new MdToHtmlRequestResponseDto(htmlString, null);
    }
  }
}
