package oxford.dictionaries.analyzinghtml.request00.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import oxford.dictionaries.analyzinghtml.common.AbstractResponseDto;

import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TranslateResponseDto extends AbstractResponseDto {
  private static final long serialVersionUID = -6380835397334590036L;

  @JsonProperty("translate")
  String translate;

  public TranslateResponseDto(String translate, String msgFail) {
    if (Objects.isNull(msgFail)) {
      this.translate = translate;
    } else {
      this.translate = null;
      this.msg = msgFail;
    }
  }

}
