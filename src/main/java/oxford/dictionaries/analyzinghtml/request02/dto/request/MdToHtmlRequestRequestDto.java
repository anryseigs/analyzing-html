package oxford.dictionaries.analyzinghtml.request02.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import oxford.dictionaries.analyzinghtml.commond.AbstractRequestDto;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MdToHtmlRequestRequestDto extends AbstractRequestDto {

  private static final long serialVersionUID = 3414411918303878153L;
  @JsonProperty("md_string")
  String mdString;
}
