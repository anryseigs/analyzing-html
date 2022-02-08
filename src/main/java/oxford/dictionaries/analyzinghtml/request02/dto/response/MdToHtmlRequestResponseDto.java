package oxford.dictionaries.analyzinghtml.request02.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import oxford.dictionaries.analyzinghtml.commond.AbstractResponseDto;

import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MdToHtmlRequestResponseDto extends AbstractResponseDto {
  private static final long serialVersionUID = -6380835397334590036L;

  @JsonProperty("html_string")
  String htmlString;

  public MdToHtmlRequestResponseDto(String htmlString, String failMsg) {
    if (Objects.isNull(failMsg)) {
      this.htmlString = htmlString;
    } else {
      this.htmlString = null;
      this.msg = failMsg;
    }
  }
}
