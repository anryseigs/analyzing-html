package oxford.dictionaries.analyzinghtml.request00.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import oxford.dictionaries.analyzinghtml.common.AbstractRequestDto;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TranslateRequestRequestDto extends AbstractRequestDto {

  private static final long serialVersionUID = -1731386968444510980L;

  @JsonProperty("language")
  String language;
  @JsonProperty("word")
  String word;
  @JsonProperty("fields")
  String fields;
  @JsonProperty("strictMatch")
  String strictMatch;
}
