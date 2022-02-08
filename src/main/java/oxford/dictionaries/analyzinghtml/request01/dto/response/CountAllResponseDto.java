package oxford.dictionaries.analyzinghtml.request01.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import oxford.dictionaries.analyzinghtml.commond.AbstractResponseDto;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CountAllResponseDto extends AbstractResponseDto {
  private static final long serialVersionUID = -6380835397334590036L;

  @JsonProperty("count_character")
  Long countCharacter;
  @JsonProperty("count_word")
  Long countWord;
  @JsonProperty("count_sentence")
  Long countSentence;

  public CountAllResponseDto(long countCharacter, long countWord, long countSentence) {
    this.countCharacter = countCharacter;
    this.countWord = countWord;
    this.countSentence = countSentence;
  }

  public CountAllResponseDto(String msgFail) {
    this.msg = msgFail;
    this.countCharacter = null;
    this.countWord = null;
    this.countSentence = null;
  }
}
