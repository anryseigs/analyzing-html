package oxford.dictionaries.analyzinghtml.common;

import java.io.Serializable;


public class AbstractResponseDto implements Serializable {

  private static final long serialVersionUID = 3138925146910632807L;

  public String msg;

  public AbstractResponseDto() { this.msg = "SUCCESS"; }
}
