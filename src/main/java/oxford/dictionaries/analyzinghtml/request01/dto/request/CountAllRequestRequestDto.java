package oxford.dictionaries.analyzinghtml.request01.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import oxford.dictionaries.analyzinghtml.common.AbstractRequestDto;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CountAllRequestRequestDto extends AbstractRequestDto {
  static final long serialVersionUID = -625142792403755182L;

  @JsonProperty("html_string")
  String htmlString;
}
